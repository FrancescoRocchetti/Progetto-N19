package Gestione;

import InterfacingDB.CheckInternet;

/**
 * Thread usato per confermare l'ordine
 *
 * @author Fabio Riganti
 *
 */

public class ThreadConfirm extends Thread {
    private ManagerDB mdb;
    private ObserverGS gs;
    private int[] index;
    private boolean accept;

    public ThreadConfirm(ObserverGS gs) {
        super("ThreadConfirm");
        mdb = new ManagerDB();
        this.accept = true;
        this.gs = gs;
    }

    public synchronized void confirmOrder(int[] index) {
        accept = false;
        this.index = index;
        notify();
    }

    @Override
    public void run() {
        try{
            while(true) {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    gs.orderFailure();
                else {
                    for(int c: index)
                        mdb.buy(c);
                    gs.orderSuccess();
                }
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
}
