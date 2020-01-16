package Interface.WebInterface.Bean;

import Logica.Facade;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * bean per la pagina web che permette la gestione dei tipi di componenti
 * @author Francesco Rocchetti
 */
public class TypeBean implements Serializable {

    Facade f;

    public TypeBean() {
        f = new Facade();
    }

    public ArrayList<String> getAllTypes(){
        return f.getAllTypes();
    }

    public void deleteType(String type){
        f.rimuoviTipo(type);
        System.err.println(type);
    }

    public void addType(String type){
        f.aggiungiTipo(type.toUpperCase());
        System.err.println(type);
    }
}
