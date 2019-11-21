package Interface.WebInterface;

import java.io.Serializable;

public class TestSito implements Serializable {

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
}
