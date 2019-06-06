package Interface;

import Components.AbstractComponent;
import Constraints.AdaptabilityConstraint;
import InterfacingDB.ManagerDB;
import InterfacingDB.PCParts;

import java.util.ArrayList;

public class ThreadInventory extends Thread{
    private ManagerDB mdb;
    private ObserverGS gs;
    private PCParts part;

    public ThreadInventory(ObserverGS gs, PCParts part) {
        mdb = new ManagerDB();
        this.gs = gs;
        this.part = part;
    }

    @Override
    public void run() {
        try{
            ArrayList<AbstractComponent> arr = AdaptabilityConstraint.check(mdb.read(part), ((GestoreScelte)gs).getScp());
            gs.update(arr);
        } catch(Exception e){
            gs.update(null);
        }
    }
}
