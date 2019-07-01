package Gestione;

import InterfacingDB.CheckInternet;

/**
 * Thread usato per aggiornare la quantità
 * di un componente
 *
 * @author Fabio Riganti
 */

public class ThreadUpdate extends Thread {
    private static final String QUANTITY = "QUANTITY";
    private static final String PRICE = "PRICE";

    private ManagerDB mdb;
    private ObserverGO go;
    private int[] id;
    private int quantity;
    private int price;
    private boolean accept;
    private String mode;

    public ThreadUpdate(ObserverGO go) {
        super("ThreadUpdate");
        mdb = new ManagerDB();
        this.accept = true;
        this.go = go;
    }

    public synchronized void updateQuantityById(int[] id, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.mode = QUANTITY;
        accept = false;
        notify();
    }

    public synchronized void updatePriceById(int[] id, int price) {
        this.id = id;
        this.price = price;
        this.mode = PRICE;
        accept = false;
        notify();
    }

    @Override
    public void run() {
        try {
            while (true) {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    go.update(false);
                else
                    update();
                accept = true;
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " terminato");
        }
    }

    private synchronized void checkAccept() throws InterruptedException {
        while (accept) {
            wait();
        }
    }

    private void update() {
        boolean status = true;
        if (mode.equals(QUANTITY)) {
            for (int i = 0; i < id.length && status; i++) {
                if (!mdb.updateQuantity(id[i], quantity)) {
                    status = false;
                }
            }
        } else {
            for (int i = 0; i < id.length && status; i++) {
                if (!mdb.updatePrice(id[i], price)) {
                    status = false;
                }
            }
        }
        go.update(status);
    }

    public void stopThread() {
        interrupt();
    }
}
