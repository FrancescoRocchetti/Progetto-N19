package Gestione;

import Components.*;
import InterfacingDB.PCParts;
import Resources.*;

import java.util.ArrayList;

//classe che gestisce i componenti già scelti
public class SelectedComponents {
    ArrayList<AbstractComponent> sc;

    public SelectedComponents(){
        sc = new ArrayList<>();
    }

    public AbstractComponent getComponent(int i){
        return sc.get(i);
    }

    public int getSize(){
        return sc.size();
    }

    public ArrayList<AbstractComponent> getAR(){
        ArrayList temp = sc;
        return temp;
    }

    public void addCList(AbstractComponent ac){
        sc.add(ac);
    }

    public void remCList(AbstractComponent ac){
        sc.remove(ac);
    }

    public void remIList(int indice){
        sc.remove(indice);
    }

    public ArrayList<Resource> getRes(){
        ArrayList<Resource> temp = new ArrayList<>();
        for(AbstractComponent ac : sc){
            temp.add(ac.getResource());
        }

        return temp;
    }

    public Resource getTotRes(){
        return SommatoreRes.sum(this.getRes());
    }

    public AbstractComponent getType(PCParts comp){

        //restituisco solo un componente (dovrebbe essere abbastanza)

        AbstractComponent temp = null;

        for(AbstractComponent ac : sc){
            if(ac.getType().equalsIgnoreCase(comp.toString())){
                temp = ac;
                break;
            }
        }
        return temp;
    }

}
