package Interface;

import InterfacingDB.ManagerDB;
import InterfacingDB.Reading;
import InterfacingDB.PCParts;
import Components.AbstractComponent;
import Gestione.SelectedComponents;
import java.util.ArrayList;

public class GestoreScelte{
    private SelectedComponents scp;
    private ManagerDB mdb;

    public GestoreScelte() {
        scp = new SelectedComponents();
        mdb = new ManagerDB();
    }

    public ArrayList<AbstractComponent> obtainParts(PCParts comp){
            return mdb.read(comp);
    }

    public void addComp(AbstractComponent comp){
        scp.addCList(comp);
    }

    public void newScp() {
        scp = new SelectedComponents();
    }

    public int getPrice(){
        return scp.getTotPrice();
    }

    public ArrayList<AbstractComponent> getComps(){
        return scp.getAR();
    }

    public String getListAbs(){
        return scp.toString();
    }


}
