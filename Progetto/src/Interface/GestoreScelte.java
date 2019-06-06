package Interface;

import Constraints.AdaptabilityConstraint;
import InterfacingDB.CheckInternet;
import InterfacingDB.PCParts;
import Components.AbstractComponent;
import Gestione.SelectedComponents;

import java.util.ArrayList;

public class GestoreScelte implements ObserverGS{
    private SelectedComponents scp;
    private ActiveComponents ac;
    private Piattaforma p;

    public GestoreScelte(Piattaforma p) {
        scp = new SelectedComponents();
        ac = new ActiveComponents();
        this.p = p;
    }

    public void obtainParts(PCParts comp) {
        ThreadInventory t = new ThreadInventory(this, comp);
        t.start();
    }

    public void addComp(int id) {
        AbstractComponent abs = ac.getCompByID(id);
        scp.addCList(abs);
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

    public SelectedComponents getScp() {
        return scp;
    }

    public boolean checkInternet() {
        return CheckInternet.check();
    }

    public Object[][] getCart() {
        ArrayList<AbstractComponent> comp = scp.getAR();
        if (comp == null) {
            return null;
        }
        Object[][] data = new Object[comp.size()][];
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
        return scp.getTotWatt();//
    }

    @Override
    public void update(ArrayList<AbstractComponent> arr) {
        if (arr != null) {
            ac.buildList(AdaptabilityConstraint.check(arr, scp));
            p.updateTable(ac.getAc());
        } else p.updateTable(null);
    }
}
