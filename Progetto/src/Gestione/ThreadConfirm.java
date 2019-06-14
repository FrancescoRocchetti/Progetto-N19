package Gestione;

import InterfacingDB.CheckInternet;
import InterfacingDB.ManagerDB;


public class ThreadConfirm extends Thread {
    private ManagerDB mdb;
    private ObserverConfirm go;
    private int[] index;
    private boolean accept;

    public ThreadConfirm(ObserverConfirm go) {
        mdb = new ManagerDB();
        this.accept = true;
        this.go = go;
    }

    public synchronized void confirmOrder(int[] index) {
        accept = false;
        this.index = index;
        notify();
    }

    @Override
    public void run() {
        while(true) {
            try {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    throw new NoInternetException("");
                for(int c: index)
                    mdb.update(c, -1);
                go.orderSuccess();
                accept = true;
            } catch (Exception e) {
                go.orderFailure();
                accept = true;
            }
        }
    }

    private synchronized void checkAccept(){
        while(accept){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(10);
            }
        }
    }
}
