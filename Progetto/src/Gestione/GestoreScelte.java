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
 * Gestione.Manager che gestisce le scelte effettuate tramite
 * l'interfaccia Piattaforma
 *
 * @author Fabio Riganti
 *
 */

public class GestoreScelte implements ObserverGS{
    private SelectedComponents scp;
    private ActiveComponents ac;
    private ConfirmList cfl;
    private Piattaforma p;
    private Manager m;

    public GestoreScelte(Manager m) {
        this.m = m;
        scp = new SelectedComponents();
        ac = new ActiveComponents();
    }

    /**
     * Permette di ottenere la lista dei componenti
     *
     * @param comp
     */
    public void obtainParts(PCParts comp) {
        m.getInventoryOf(comp);
    }//t

    public void setPiattaforma(Piattaforma p){
        this.p = p;
    }

    /**
     * Permette di aggiungere un componente al carrello
     *
     * @param id
     */
    public void addComp(int id) {
        AbstractComponent abs = ac.getCompByID(id);
        scp.addCList(abs);
    }

    /**
     * Permette di rimuovere uno o più componenti dal carrello
     *
     * @param id
     */
    public void rmvComp(int[] id) {
        for(int i: id)
            scp.rmvCList(i);
    }

    /**
     * Resetta la configurazione attuale, liberando il carrello
     */
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
        return scp.getTotWatt();
    }

    public AbstractComponent getCompByID(int index){
        return ac.getCompByID(index);
    }

    @Override
    public void updateList(ArrayList<AbstractComponent> arr) {
        if (arr != null) {
            ac.buildList(AdaptabilityConstraint.check(arr, scp));
            p.updateListTable(ac.getAc());
        } else p.updateListTable(null);
    }

    /**
     * Permette di ottenere la lista dei Warning
     *
     * @return String
     */
    public String getWarningTxt() {
        Warning w = Warning.getwInstance();
        w.check(scp);
        return w.getInfo();
    }

    /**
     * Permette verificare se è possibile effettuare l'ordine
     *
     * @return boolean
     */
    public boolean canOrder(){
        Warning w = Warning.getwInstance();
        return w.check(scp) && ConsistencyConstraint.checkRes(scp);
    }

    /**
     * Permette di confermare l'ordine
     *
     * @param cl
     */
    public void confirmOrder(ConfirmList cl) {
        cfl = cl;
        m.confirmOrder(cl.getCodesOfComps());//tc
    }

    @Override
    public void orderSuccess() {
        cfl.success();
    }

    @Override
    public void orderFailure() {
        cfl.failure();
    }

    public void createLogin(Piattaforma p){
        m.createLogin(p);
    }

    /**
     * Permette di ottenere una matrice di Object
     * a partire da una lista di AbstractComponent
     *
     * @param comp
     *
     * @return Object[][]
     */
    public Object[][] getObjectFromComps(ArrayList<AbstractComponent> comp){
        return m.getObjectFromComps(comp);
    }
}
