package Gestione;

import Constraints.AdaptabilityConstraint;
import Constraints.Warning;
import Interface.ActiveComponents;
import Interface.Piattaforma;
import InterfacingDB.PCParts;
import Components.AbstractComponent;

import java.util.ArrayList;

public class GestoreScelte implements ObserverGS{
    private SelectedComponents scp;
    private ActiveComponents ac;
    private Piattaforma p;
    private ThreadInventory t;

    public GestoreScelte(Piattaforma p) {
        scp = new SelectedComponents();
        ac = new ActiveComponents();
        t = new ThreadInventory(this);
        this.p = p;
        t.start();
    }

    public void obtainParts(PCParts comp) {
        t.getListOf(comp);
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

    public ArrayList<AbstractComponent> getSelectedComponents() {
        return scp.getAR();
    }

    public int getWatt(){

        return scp.getTotWatt();//
    }

    @Override
    public void update(ArrayList<AbstractComponent> arr) {
        if (arr != null) {
            ac.buildList(AdaptabilityConstraint.check(arr, scp));
            p.updateListTable(ac.getAc());
        } else p.updateListTable(null);
    }

    public String getWarning() {
        Warning w = Warning.getwInstance();
        w.check(scp);
        return w.getInfo();
    }
}
