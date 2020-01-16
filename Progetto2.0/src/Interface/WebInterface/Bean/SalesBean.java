package Interface.WebInterface.Bean;

import Logica.Facade;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * bean per la visualizzazione delle vendite
 * @author Francesco Rocchetti
 */
public class SalesBean implements Serializable {

    private Facade f;

    public SalesBean() {
        f= new Facade();
    }

    public ArrayList<ArrayList<String>> getSales(){
        return f.getSales();
    }
}
