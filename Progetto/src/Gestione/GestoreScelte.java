package Gestione;

import Constraints.AdaptabilityConstraint;
import Constraints.ConsistencyConstraint;
import Constraints.Warning;
import Interface.ConfirmList;
import Interface.Piattaforma;
import Components.PCParts;
import Components.AbstractComponent;

import java.util.ArrayList;

/**
 * Manager che gestisce le scelte effettuate tramite
 * l'interfaccia Piattaforma
 *
 * @author Fabio Riganti
 *
 */

public class GestoreScelte implements ObserverGS{
    private SelectedComponents scp;
    private ActiveComponents ac;
    private Piattaforma p;
    private ConfirmList cfl;
    private ThreadInventory t;
    private ThreadConfirm tc;

    public GestoreScelte(Piattaforma p) {
        scp = new SelectedComponents();
        ac = new ActiveComponents();
        t = new ThreadInventory(this);
        tc = new ThreadConfirm(this);
        this.p = p;
        t.start();
        tc.start();
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
    public void updateList(ArrayList<AbstractComponent> arr) {
        if (arr != null) {
            ac.buildList(AdaptabilityConstraint.check(arr, scp));
            p.updateListTable(ac.getAc());
        } else p.updateListTable(null);
    }

    public String getWarningTxt() {
        Warning w = Warning.getwInstance();
        w.check(scp);
        return w.getInfo();
    }

    public boolean canOrder(){
        Warning w = Warning.getwInstance();
        return w.check(scp) && ConsistencyConstraint.checkRes(scp);
    }

    public void confirmOrder(ConfirmList cl) {
        cfl = cl;
        tc.confirmOrder(cl.getCodesOfComps());
    }

    @Override
    public void orderSuccess() {
        cfl.success();
    }

    @Override
    public void orderFailure() {
        cfl.failure();
    }
}
