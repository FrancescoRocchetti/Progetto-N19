package Gestione;

import Components.AbstractComponent;
import Components.PCParts;
import InterfacingDB.CheckInternet;
import InterfacingDB.ManagerDB;

import java.util.ArrayList;

public class ThreadList extends Thread{
    private ManagerDB mdb;
    private ObserverGO go;
    private PCParts part;
    private boolean accept;

    public ThreadList(ObserverGO go) {
        mdb = new ManagerDB();
        this.accept = true;
        this.go = go;
    }

    public synchronized void getListOf(PCParts part) {
        this.part = part;
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
                ArrayList<AbstractComponent> arr = mdb.read(part);
                go.updateList(arr);
                accept = true;
            } catch (Exception e) {
                go.updateList(null);
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
