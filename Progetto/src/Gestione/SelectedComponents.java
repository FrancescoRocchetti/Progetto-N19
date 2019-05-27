package Gestione;

import Components.*;
import InterfacingDB.PCParts;
import Resources.*;

import java.util.ArrayList;

//classe che gestisce i componenti già scelti
public class SelectedComponents {
    private ArrayList<AbstractComponent> sc;

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
        for(int i = 0; i< sc.size(); i++)
            if(ac.getType().equals(sc.get(i).getType())){
                sc.remove(i);
                sc.add(ac);
                return;
            }
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

    public int getTotPrice(){
        int temp = 0;
        for(AbstractComponent ac : sc){
            temp+=ac.getPrice();
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

    public boolean isAlreadyIn(AbstractComponent comp){
        return sc.contains(comp);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (AbstractComponent aStr : sc) {
            s.append(aStr.getName()+" - "+aStr.getPrice()+" €").append("\n");
        }
        return s.toString();
    }
}
