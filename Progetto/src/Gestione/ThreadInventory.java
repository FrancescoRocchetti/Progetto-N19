package Gestione;

import Components.AbstractComponent;
import Constraints.AdaptabilityConstraint;
import InterfacingDB.CheckInternet;
import InterfacingDB.ManagerDB;
import Components.PCParts;

import java.util.ArrayList;

public class ThreadInventory extends Thread{
    private ManagerDB mdb;
    private ObserverGS gs;
    private PCParts part;
    private boolean accept;

    public ThreadInventory(ObserverGS gs) {
        mdb = new ManagerDB();
        this.accept = true;
        this.gs = gs;
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
                ArrayList<AbstractComponent> arr = AdaptabilityConstraint.check(mdb.read(part), ((GestoreScelte) gs).getScp());
                gs.updateList(arr);
                accept = true;
            } catch (Exception e) {
                gs.updateList(null);
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
