package Automatic;

import Components.AbstractComponent;
import Components.PCParts;
import Constraints.AdaptabilityConstraint;
import Gestione.SelectedComponents;
import InterfacingDB.ManagerDB;

import java.util.ArrayList;
import java.util.Collections;

public class ABuild {
    private SelectedComponents sc;
    private int budget;
    private int usedMoney;
    private boolean allOk;
    private ManagerDB db;

    private double cCpu = 0.2;
    private double cGpu = 0.25;
    private double cMobo = 0.15;
    private double cRam = 0.075;
    private double cPsu = 0.075;
    private double cCooler = 0.05;
    private double cCase = 0.1;
    private double cStorage = 0.1;
    /**
     * assegno percentuali di prezzo arbitrarie ad ogi componente
     *
     * TODO:sistemare questo casino
     */
    private PCParts[] lista = {PCParts.MOBO,PCParts.CPU,PCParts.RAM,
                                 PCParts.PSU,PCParts.STORAGE,PCParts.CASE};
    private double[] costi = {0.15,0.2,0.08,0.08,0.1,0.1};


    public ABuild(int maxCost) {
        this.budget = maxCost;
        usedMoney = 0;
        sc = new SelectedComponents();
        allOk = true;
        start();
    }

    /**
     * tecnicamente per la build senza budget serve solo un secondo costruttore
     */

    public ABuild() {
        this.budget = 10000;
        usedMoney = 0;
        sc = new SelectedComponents();
        allOk = true;
        start();
    }


    /** Avvio della build automatica
     *  scelgo le componenti in ordine di importanza
     *  cooler e gpu sono scelte in un secondo momento in quanto sono "opzionali"
     */
    private void start(){
        db = new ManagerDB();
        int i = 0;
        for (PCParts p : lista) {
            AbstractComponent temp = find(p,(int)costi[i]* budget);
            if(temp != null){
                sc.addCList(temp);
            } else
                allOk = false;
            i++;
        }

        if (!sc.getTotRes().isOkCooler()) {
            AbstractComponent temp = find(PCParts.COOLER,(int)cCooler* budget);
            if(temp != null){
                sc.addCList(temp);
            } else
                allOk = false;
        }

        if (!sc.getTotRes().isOkGPU() || (budget >500 && usedMoney< budget *(1-cGpu))) {
            AbstractComponent temp = find(PCParts.GPU,(int)cGpu* budget);
            if(temp != null){
                sc.addCList(temp);
            } else
                allOk = false;
        }

    }


    /**scelgo la componente migliore
     *
     * NB: le prestazioni di un componente sono valutate come Valutazione fratto costo
     *
     * @param p tipo di componente
     * @param c costo
     * @return
     */
    private AbstractComponent find(PCParts p, int c){
        ArrayList<AbstractComponent> temp = segment(p,c);
        Collections.sort(temp);

        if (temp.size()>0){
            return temp.get(0);
        } else
        return null;
    }


    /**trovo le componenti(compatibili) nel range di prezzo giusto
     *
     * @param p tipo di componente
     * @param c costo (usato come centro dell'intorno di ricerca)
     * @return
     */
    private ArrayList<AbstractComponent> segment(PCParts p, int c){
        ArrayList<AbstractComponent> temp = AdaptabilityConstraint.check(db.read(p),sc);
        ArrayList<AbstractComponent> seg = new ArrayList<>();
        for(AbstractComponent ac : temp){
            if (ac.getPrice()>(int)0.75*c && ac.getPrice()<(int)1.25*c){
               seg.add(ac);
            }
        }
        return seg;
    }

    public SelectedComponents getSc() {
        return sc;
    }
}
