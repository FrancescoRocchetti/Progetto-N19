package Logica;

import java.util.ArrayList;

public class Gestione {

    private ComponentiMostrati cm;
    private ComponentiSelezionati cs;
    private BComponente bc;

    public Gestione(){
        bc = new BComponente();
        resetC();
        resetS();
    }

    public void leggi(String type){
        cm.change(bc.compByType(type));
    }

    public void aggiungi(int i){
        cs.put(cm.getCompbyId(i));
    }

    public void rimuovi(int i){
        cs.remove(i);
    }

    public ArrayList<String> dettagli(int id){
        return cm.getDetails(id);
    }

    public ArrayList<String> getSelected(){
        return cs.getLista();
    }

    public ArrayList<String> getMostrati(){
        return cm.getLista();
    }

    public int printCost(){
        return cs.getTotCost();
    }

    public int printPower(){
        return cs.getPower();
    }

    public boolean checkFinale(){
        return cs.checkFinale();
    }

    public void resetS(){
        cm = new ComponentiMostrati();
    }

    public void resetC(){
        cs = new ComponentiSelezionati();
    }

}
