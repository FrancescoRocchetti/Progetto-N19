package Logica;

import Database.Facade;
import Logica.Automatic.SelezioneAutomatica;

import java.util.ArrayList;

/**
 * classe di gestione degli acquisti
 * @author Francesco Rocchetti
 */
public class Gestione {

    private ComponentiMostrati cm;
    private ComponentiSelezionati cs;
    private BComponente bc;
    private Facade fdb;

    public Gestione(){
        bc = new BComponente();
        fdb = Facade.getInstance();
        resetMostrati();
        resetSelected();
    }

    public void leggi(String type){
        cm.change(bc.compByType(type));
    }

    public boolean aggiungi(int i){
        return cs.put(cm.getCompbyId(i));
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

    public boolean confirm(String info){
        return buy(info);
    }

    public boolean confirm(){
        String temp = Scrittura.getIP();
        temp = temp+ "/" +System.getProperty("user.name");
        return buy(temp);
    }

    private boolean buy(String info){
        if(checkFinale()){
            if(fdb.buy(cs.getIds(), info).size() == 0){
                return true;
            }
        }
        return false;
    }

    public void automatic(int budget){
        SelezioneAutomatica s = new SelezioneAutomatica(budget);
        cs = s.getBuild();
        System.err.println(s.getBuild().getIds());
    }

    public void resetMostrati(){
        cm = new ComponentiMostrati();
    }

    public void resetSelected(){
        cs = new ComponentiSelezionati();
    }

    /**
     * manca ancora il sistemi per i messaggi di warning
     *
     * si potrebbe per esempio avvisare l'utente se ci sono risorse spaiate
     */

}
