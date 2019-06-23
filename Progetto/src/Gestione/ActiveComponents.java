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

    /**
     * Permette di costruire la lista dei componenti presenti
     * nella tabella dell'inventario di Piattaforma
     *
     * @param list
     */
    public void buildList(ArrayList<AbstractComponent> list){
        ac = list;
    }

    /**
     * Restituisce i componenti attivi
     *
     * @return Lista dei componenti
     */
    public ArrayList<AbstractComponent> getAc() {
        return ac;
    }

    /**
     * Restituisce un componente in base all codice prodotto
     *
     * @param id
     *
     * @return AbstractComponent
     */
    public AbstractComponent getCompByID(int id){
        for(AbstractComponent abs: ac) {
           if(abs.getID() == id)
               return abs;
        }
        return null;
    }
}
