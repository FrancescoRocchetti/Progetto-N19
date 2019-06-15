package Gestione;

import InterfacingDB.CheckInternet;
import InterfacingDB.ManagerDB;


public class ThreadRemove extends Thread {
    private ManagerDB mdb;
    private ObserverGO go;
    private int id;
    private boolean accept;

    public ThreadRemove(ObserverGO go) {
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
        while(true) {
            try {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    throw new NoInternetException("");
                mdb.remove(id);
                go.remove(true);
                accept = true;
            } catch (Exception e) {
                go.remove(false);
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
