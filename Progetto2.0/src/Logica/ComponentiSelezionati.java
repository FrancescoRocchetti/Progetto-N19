package Logica;

import Logica.Risorse.*;
import Logica.Vincoli.Vincolo;

import java.util.ArrayList;
import static java.lang.Math.abs;

/**
 * classe della lista di componenti selezionati
 */

public class ComponentiSelezionati {

    private ArrayList<Componente> alc;

    public ComponentiSelezionati() {
        alc = new ArrayList<>();
    }

    public boolean put(Componente c){
        if(check(c)) {
            alc.add(c);
            return true;
        }
        return false;
    }

    public void remove(int i){
        for(Componente c: alc){
            if(c.getId() == i){
                alc.remove(c);
                return;
            }
        }
    }

    private boolean check(Componente c){
        ArrayList<Componente> temp = new ArrayList<>(alc);
        temp.add(c);
        return Check.c(temp);
    }

    public boolean checkFinale(){
        return Check.checkFinale(alc);
    }

    public String printVincoli(){
        String temp = "";
        for(Componente c: alc){
            for (Vincolo v : c.getAlv()){
                temp = temp + v.nomeCompleto()+ " ";
            }
            temp = temp + "\n";
        }
        return temp;
    }

    //spostare questa funzione
    public int getTotCost(){
        int temp=0;

        for(Componente c: alc){
            temp+=c.getPrice();
        }

        return temp;
    }

    //spostare questa funzione
    public int getPower(){
        int temp=0;

        for(Componente c: alc){
            for(Risorsa r: c.getRisorse()){
                if(r.getName().equalsIgnoreCase("power")){
                    if(((RisorsaNumerica)r).getValue()<0){
                        temp+=((RisorsaNumerica)r).getValue();
                    }
                }
            }
        }

        return abs(temp);
    }

    public ArrayList<String> getLista(){
        ArrayList<String> als = new ArrayList<>();
        for(Componente c: alc){
            als.add(c.toString());
        }
        return als;
    }

    public ArrayList<Integer> getIds(){
        ArrayList<Integer> temp = new ArrayList<>();
        for(Componente c: alc){
            temp.add(c.getId());
        }
        return temp;
    }

    public String toString() {
        String temp = "";
        for(Componente c: alc){
            temp = temp + c.toString() + "\n";
        }
        return "Selected{\n"+ temp +"}";
    }
}