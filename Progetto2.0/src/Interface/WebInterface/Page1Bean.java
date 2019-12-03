package Interface.WebInterface;

import Logica.Facade;

import java.io.Serializable;
import java.util.ArrayList;

public class Page1Bean implements Serializable {

    private Facade f;

    public Page1Bean(){
        f = new Facade();
    }

    private String cAttivo="";

    public String getcAttivo() {
        return cAttivo;
    }

    public void setcAttivo(String cAttivo) {
        this.cAttivo = cAttivo;
    }

    public ArrayList<String> tipiComponenti(){
        return f.getAllTypes();
    }

    public ArrayList<ArrayList<String>> getByType(String type){
        return f.getListbyType(type);
    }

    public ArrayList<ArrayList<String>> getSelected(){
        return f.getSelectedList();
    }

    public void select(String id){
        f.selectComponent(Integer.parseInt(id));
    }

    public void remove(String id){
        f.removeComponent(Integer.parseInt(id));
    }

    public void reset(){
        f.resetSelected();
    }

    public void confirm(String info){
        f.confirm(info);
    }

    public String getPrice(){
        return String.valueOf(f.getTotPrice());
    }

    public String getPower(){
        return String.valueOf(f.getTotPower());
    }
}
