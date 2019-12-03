package Logica;

import Logica.Risorse.Risorsa;

import java.util.ArrayList;

public class ComponentiMostrati {

    private ArrayList<Componente> alc;

    public ComponentiMostrati(){
        alc = new ArrayList<>();
    }

    public void add(Componente c){
        alc.add(c);
    }

    public void change(ArrayList<Componente> alc){
        this.alc = alc;
    }

    public Componente getCompbyId(int id){
        for(Componente c: alc){
            if(c.getId() == id)
                return c;
        }
        return null;
    }

    public ArrayList<String> getDetails(int id){
        ArrayList<Risorsa> tempR = new ArrayList<>();
        for(Componente c: alc){
            if(c.getId() == id)
                tempR = c.getRisorse();
        }

        ArrayList<String> tempS = new ArrayList<>();
        for(Risorsa r: tempR){
            tempS.add(r.getName()+" "+r.getValue());
        }

        return tempS;
    }

    public ArrayList<ArrayList<String>> getLista(){
        ArrayList<ArrayList<String>> als = new ArrayList<>();
        for(Componente c: alc){
            if(c.getN()>0){
                als.add(c.getString());
            }
        }
        return als;
    }

}
