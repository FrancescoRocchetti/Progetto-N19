package Interface.WebInterface;

import Logica.Facade;

import java.io.Serializable;
import java.util.ArrayList;

public class Page1Bean implements Serializable {

    private Facade f;

    public Page1Bean(){
        f = new Facade();
    }

    private String[] esempioListaComponenti={"Cpu","Mobo","RAM","Storage","Case","PSU"};
    private String cAttivo="";

    public String getcAttivo() {
        return cAttivo;
    }

    public void setcAttivo(String cAttivo) {
        this.cAttivo = cAttivo;
    }

    public String[] getComponenti(){
        return esempioListaComponenti;
    }

    public ArrayList<String> tipiComponenti(){
        return f.getAllTypes();
    }

    public ArrayList<String> getByType(String type){
        return f.getListbyType(type);
    }
}
