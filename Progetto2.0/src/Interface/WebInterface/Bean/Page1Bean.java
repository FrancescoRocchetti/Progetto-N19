package Interface.WebInterface.Bean;

import Logica.Facade;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * bean per la pagina principale dello shop
 * @author Francesco Rocchetti
 */
public class Page1Bean implements Serializable {

    private Facade f;

    private boolean okOperation;

    public Page1Bean(){
        f = new Facade();
        okOperation = true;
    }

    private String cAttivo="";

    private boolean autobuild=false;

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
        okOperation = f.selectComponent(Integer.parseInt(id));
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

    public boolean check(){
        return f.allOk();
    }

    public String getPrice(){
        return String.valueOf(f.getTotPrice());
    }

    public String getPower(){
        return String.valueOf(f.getTotPower());
    }

    public void automatic(int price){
        f.automatic(price);
        autobuild=true;
    }

    public void resetOk(){
        okOperation = true;
    }

    public boolean isOkOperation() {
        return okOperation;
    }

    public boolean isFail(){
        if(autobuild && getSelected().isEmpty()){
            autobuild =false;
            return true;
        }
        else
            return false;
    }
}
