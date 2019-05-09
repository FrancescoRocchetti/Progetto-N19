package Gestione;

import Components.*;
import InterfacingDB.PCParts;

import java.util.ArrayList;

//classe che gestisce i componenti già scelti
public class SelectedComponents {
    ArrayList<AbstractComponent> sc;

    public SelectedComponents(){
        sc = new ArrayList<>();
    }

    public AbstractComponent getC(int i){
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

    public AbstractComponent getType(PCParts comp){
    //TODO: far restituire arraylist?
        AbstractComponent temp = null;

        for(AbstractComponent ac: sc){
            if(ac.getType()== comp.toString()){
                temp = ac;
                break;
            }
        }
        return temp;
    }

}
