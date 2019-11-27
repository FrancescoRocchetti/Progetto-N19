package Logica;

import Database.Facade;
import Logica.Automatic.SelezioneAutomatica;

import java.util.ArrayList;

public class Gestione {

    private ComponentiMostrati cm;
    private ComponentiSelezionati cs;
    private BComponente bc;
    private Facade fdb;

    public Gestione(){
        bc = new BComponente();
        fdb = Facade.getInstance();
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

    public ArrayList<ArrayList<String>> getSelected(){
        return cs.getLista();
    }

    public ArrayList<ArrayList<String>> getMostrati(){
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

    public boolean buy(){
        if(checkFinale()){
            if(fdb.buy(cs.getIds()).size() == 0){
                return true;
            }
        }
        return false;
    }

    public void automatic(int budget){
        SelezioneAutomatica s = new SelezioneAutomatica(budget);
        cs = s.getBuild();
    }

    public void resetS(){
        cm = new ComponentiMostrati();
    }

    public void resetC(){
        cs = new ComponentiSelezionati();
    }

    /**
     * manca ancora il sistemi per i messaggi di warning
     *
     * si potrebbe per esempio avvisare l'utente se ci sono risorse spaiate
     */

}
