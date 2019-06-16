package Gestione;

import InterfacingDB.CheckInternet;
import InterfacingDB.ManagerDB;


public class ThreadRemove extends Thread {
    private ManagerDB mdb;
    private ObserverGO go;
    private int id;
    private boolean accept;

    public ThreadRemove(ObserverGO go) {
        super("ThreadRemove");
        mdb = new ManagerDB();
        this.accept = true;
        this.go = go;
    }

    public synchronized void removeCompById(int id) {
        this.id = id;
        accept = false;
        notify();
    }

    @Override
    public void run() {
        try {
            while(true) {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    go.remove(false);
                else
                    go.remove(mdb.remove(id));
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
