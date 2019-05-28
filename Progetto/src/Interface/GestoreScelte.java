package Interface;

import Constraints.OtherConstraint;
import InterfacingDB.Reading;
import InterfacingDB.PCParts;
import Components.AbstractComponent;
import Gestione.SelectedComponents;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestoreScelte{
    private SelectedComponents scp;

    public GestoreScelte() {
        scp = new SelectedComponents();
    }

    public ArrayList<AbstractComponent> obtainParts(PCParts comp){
        InterfacingDB.Reading dati = new Reading();
        try {
            return dati.read(comp);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            dati.forceClose();
            return null;
        }
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
