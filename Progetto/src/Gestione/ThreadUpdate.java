package Gestione;

import InterfacingDB.CheckInternet;
import InterfacingDB.ManagerDB;

public class ThreadUpdate extends Thread{
    private ManagerDB mdb;
    private ObserverGO go;
    private int id;
    private int quantity;
    private boolean accept;

    public ThreadUpdate(ObserverGO go) {
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
        while(true) {
            try {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    throw new NoInternetException("");
                mdb.update(id, quantity);
                go.update(true);
                accept = true;
            } catch (Exception e) {
                go.update(false);
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
