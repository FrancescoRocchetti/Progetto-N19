package Interface;

import Constraints.AdaptabilityConstraint;
import InterfacingDB.ManagerDB;
import InterfacingDB.PCParts;
import Components.AbstractComponent;
import Gestione.SelectedComponents;

import java.util.ArrayList;

public class GestoreScelte {
    private SelectedComponents scp;
    private ManagerDB mdb;

    public GestoreScelte() {
        scp = new SelectedComponents();
        mdb = new ManagerDB();
    }

    public ArrayList<AbstractComponent> obtainParts(PCParts comp) {
        return mdb.read(comp);
    }

    public void addComp(int id) {
        scp.addCList(mdb.getCompByID(id));
    }

    public void rmvComp(int id) {
        scp.rmvCList(id);
    }

    public void newScp() {
        scp = new SelectedComponents();
    }

    public int getPrice() {
        return scp.getTotPrice();
    }

    public ArrayList<AbstractComponent> getComps() {
        return scp.getAR();
    }

    public boolean checkInternet() {
        return mdb.checkInternet();
    }

    public Object[][] getString() {
        ArrayList<AbstractComponent> comp = scp.getAR();
        if (comp == null) {
            return null;
        }
        Object data[][] = new Object[comp.size()][];
        AbstractComponent abs;
        for (int i = 0; i < comp.size(); i++) {
            data[i] = new Object[5];
            abs = comp.get(i);
            data[i][0] = abs.getID();
            data[i][1] = abs.getType();
            data[i][2] = abs.getName();
            data[i][3] = abs.getQuantity();
            data[i][4] = abs.getPrice() + " €";
        }
        return data;
    }

    public int getWatt(){
        return scp.getTotWatt();
    }
}
