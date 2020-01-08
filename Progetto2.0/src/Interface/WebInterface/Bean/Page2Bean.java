package Interface.WebInterface.Bean;

import Logica.Facade;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * bean per la pagina web che lista tutte le componenti
 */
public class Page2Bean implements Serializable {

    private Facade f;

    public Page2Bean(){
        f = new Facade();
    }

    public ArrayList<ArrayList<String>> getAll(){
        return f.getAll();
    }
}
