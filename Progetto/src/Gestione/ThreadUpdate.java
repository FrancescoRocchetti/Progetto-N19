package Gestione;

import InterfacingDB.CheckInternet;
import InterfacingDB.ManagerDB;

/**
 * Thread usato per aggiornare la quantità
 * di un componente
 *
 * @author Fabio Riganti
 *
 */

public class ThreadUpdate extends Thread{
    private ManagerDB mdb;
    private ObserverGO go;
    private int id;
    private int quantity;
    private boolean accept;

    public ThreadUpdate(ObserverGO go) {
        super("ThreadUpdate");
        mdb = new ManagerDB();
        this.accept = true;
        this.go = go;
    }

public synchronized void updateCompById(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
        accept = false;
        notify();
    }

    @Override
    public void run() {
        try{
            while(true) {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    go.update(false);
                else
                    go.update(mdb.update(id, quantity));
                accept = true;
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName()+" terminato");
        }
    }

    private synchronized void checkAccept() throws InterruptedException {
        while(accept){
            wait();
        }
    }

    public void stopThread(){
        interrupt();
    }
}
