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
        super("ThreadInventory");
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
        try{
            while (true) {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    gs.updateList(null);
                else {
                    ArrayList<AbstractComponent> arr = AdaptabilityConstraint.check(mdb.read(part), ((GestoreScelte) gs).getScp());
                    gs.updateList(arr);
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
