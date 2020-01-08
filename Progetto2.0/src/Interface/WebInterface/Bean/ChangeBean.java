package Interface.WebInterface.Bean;

import Logica.Facade;

import java.io.Serializable;

public class ChangeBean implements Serializable {

    private Facade f;
    private int id;
    private boolean ck = false;

    public ChangeBean() {
        f = new Facade();
    }

    public void changePrice(String price){
        f.updatePrice(id,Integer.parseInt(price));
        ck = true;
    }

    public void changeQt(String qt){
        f.updateN(id,Integer.parseInt(qt));
        ck = true;
    }

    public void changeRating(String rating){
        f.updateRating(id,Integer.parseInt(rating));
        ck = true;
    }

    public int getId() {
        return id;
    }

    public void setId(Object ido) {
        String id = (String) ido;
        if(id!=null && !id.equalsIgnoreCase("0"))
            this.id = Integer.parseInt(id);
    }

    public boolean isCk() {
        return ck;
    }

    public void setCk(boolean ck) {
        this.ck = ck;
    }
}
