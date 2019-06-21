package Gestione;

import Components.AbstractComponent;
import Components.PCParts;
import InterfacingDB.CheckInternet;

import java.util.ArrayList;

/**
 * Thread usato per ottenere la lista dei
 * componenti e visualizzarla su Update,
 * su Remove e su CompList
 *
 * @author Fabio Riganti
 *
 */

public class ThreadList extends Thread{
    private ManagerDB mdb;
    private ObserverGO go;
    private PCParts part;
    private boolean accept;

    public ThreadList(ObserverGO go) {
        super("ThreadList");
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
        try {
            while(true) {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    go.updateList(null);
                else {
                    ArrayList<AbstractComponent> arr = mdb.read(part);
                    go.updateList(arr);
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

    public void stopThread(){
        interrupt();
    }
}
