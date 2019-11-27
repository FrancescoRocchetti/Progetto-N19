package Interface.WebInterface;


import Logica.Facade;

import java.io.Serializable;
import java.util.ArrayList;

public class Page2Bean implements Serializable {

    private Facade f;

    public Page2Bean(){
        f = new Facade();
    }

    public ArrayList<ArrayList<String>> getAll(){
        return f.getAll();
    }
}
