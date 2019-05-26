package Interface;

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

    public SelectedComponents getScp() {
        return scp;
    }

    public ArrayList<AbstractComponent> obtainParts(PCParts comp) throws SQLException {
        InterfacingDB.Reading dati = new Reading();
        return dati.read(comp);
    }

    public boolean isAlreadyIn(AbstractComponent comp){
        return scp.isAlreadyIn(comp);
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
