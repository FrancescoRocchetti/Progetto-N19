package Interface.WebInterface;

import Logica.Facade;

import java.io.Serializable;
import java.util.ArrayList;

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
