package Gestione;

import Components.AbstractComponent;

import java.util.ArrayList;

/**
 * Classe usata per mantenere in memoria i componenti
 * scaricati da DB attraverso la interfaccia Piattaforma
 *
 * @author Fabio Riganti
 *
 */

public class ActiveComponents {
    private ArrayList<AbstractComponent> ac;

    public ActiveComponents() {
        ac = new ArrayList<>();
    }

    public void buildList(ArrayList<AbstractComponent> list){
        ac = list;
    }

    public ArrayList<AbstractComponent> getAc() {
        return ac;
    }

    public AbstractComponent getCompByID(int id){
        for(AbstractComponent abs: ac) {
           if(abs.getID() == id)
               return abs;
        }
        return null;
    }
}
