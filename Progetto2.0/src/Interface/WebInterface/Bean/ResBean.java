package Interface.WebInterface.Bean;

import Logica.Facade;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * gestione delle Caratteristiche
 */
public class ResBean implements Serializable {

    private int id;
    private Facade f;

    public ResBean() {
        f = new Facade();
    }

    public ArrayList<ArrayList<String>> getRes(){
        return f.getCaratteristichebyId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        if(id!=null && !id.equalsIgnoreCase("0"))
            this.id = Integer.parseInt(id);
    }

    public ArrayList<String> getNames(){
        return f.getAllNomiDiRisorse();
    }

    public ArrayList<String> getTypes(){
        return f.getAllTipiDiRisorsa();
    }

    public void req(HttpServletRequest req){
        System.err.println(req.getParameter("inName")
            +" "+req.getParameter("inValue")
            +" "+req.getParameter("inType"));

        f.addDetail(id,req.getParameter("inType"),req.getParameter("inName"),req.getParameter("inValue"));
    }


    public void rmv(HttpServletRequest req){
        int i = Integer.parseInt(req.getParameter("buttonDR"));
        String name = getRes().get(i).get(2);
        f.dropDetail(id,name);
        System.err.println(id+" "+name);
    }
}
