package Interface.WebInterface;

import Logica.Facade;

import java.io.Serializable;
import java.util.ArrayList;

public class AddBean implements Serializable {

    Facade f;

    public AddBean() {
        this.f = new Facade();
    }

    public ArrayList<String> getVincoli(){
        ArrayList<String> temp = f.getAllVincoli();
        temp.remove("NoVincolo");
        return temp;
    }

    public ArrayList<String> getType(){
        return f.getAllTypes();
    }

    //gestire ct
    public void addComp(String name, String price, String qt, String rating, ArrayList<String> constrain, String type){
        String ct ="NoVincolo";
        f.addComp(name,Integer.parseInt(price),Integer.parseInt(qt),Integer.parseInt(rating),constrain,type);
    }
}
