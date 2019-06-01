package Gestione;

import Components.*;
import Constraints.OtherConstraint;
import Constraints.Warning;
import InterfacingDB.PCParts;
import Resources.*;

import java.util.ArrayList;

//classe che gestisce i componenti già scelti
public class SelectedComponents {
    private ArrayList<AbstractComponent> sc;
    private Warning w;

    public SelectedComponents(){
        sc = new ArrayList<>();
        w = Warning.getwInstance();
    }

    public AbstractComponent getComponent(int i){
        return sc.get(i);
    }

    public int getSize(){
        return sc.size();
    }

    public ArrayList<AbstractComponent> getAR(){
        return sc;
    }

    public void addCList(AbstractComponent ac){
        if(OtherConstraint.check(ac, this)){
            sc.add(ac);
            w.check(this);
            System.err.println(w.getInfo());
        }

    }

    private void substitution(AbstractComponent ac) {
        for(int i = 0; i<sc.size(); i++){
            if(ac.getType().equals(sc.get(i).getType())){
                sc.set(i,ac);
                return;
            }
        }
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (AbstractComponent aStr : sc) {
            s.append(aStr.getName()+" - "+aStr.getPrice()+" €").append("\n");
        }
        return s.toString();
    }

    public void rmvCList(int id) {
        AbstractComponent abs;
        for(int i = 0; i < sc.size(); i++){
            abs = sc.get(i);
            if(abs.getID() == id){
                sc.remove(i);
                return;
            }
        }
    }
}
