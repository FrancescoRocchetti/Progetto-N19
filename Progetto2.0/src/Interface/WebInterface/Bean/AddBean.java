package Interface.WebInterface.Bean;

import Logica.Facade;

import org.eclipse.jetty.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * bean per la pagina web per l'inserimento di nuove componenti
 * @author Francesco Rocchetti
 */
public class AddBean implements Serializable {

    private Facade f;

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


    private void addComp(String name, String price, String qt, String rating, ArrayList<String> constrain, String type){

        String ct ="NoVincolo";
        if(constrain.size()==0) {
            constrain.add(ct);
        }


        try {
            f.addComp(name,Integer.parseInt(price),Integer.parseInt(qt),Integer.parseInt(rating),constrain,type);
        } catch (NumberFormatException e){
            System.err.println("inserite informazioni non valide per aggiungere un componente");
        }

    }

    public void req(HttpServletRequest req){
        String name = req.getParameter("inName");
        String type = req.getParameter("inType");
        String price = req.getParameter("inPrice");
        String qt = req.getParameter("inQt");
        String rt = req.getParameter("inRt");

        ArrayList<String> temp = new ArrayList<>();
        for(String s: getVincoli()){
            if(req.getParameter(s)!=null) {
                temp.add(s);
            }
        }


        if(name.toCharArray().length!=0) {
            addComp(name, price, qt, rt, temp, type);
        }
    }
}
