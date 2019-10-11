package Minimo;

import java.util.ArrayList;
import static java.lang.Math.abs;

/**
 * classe della lista di componenti selezionati
 */

public class Selected {

    private ArrayList<Componente> alc;

    public Selected() {
        alc = new ArrayList<>();
    }

    public void put(Componente c){
        if(check(c))
            alc.add(c);
    }

    private boolean check(Componente c){
        ArrayList<Componente> temp = new ArrayList<>(alc);
        temp.add(c);
        return Check.c(temp);
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

    public int getTotCost(){
        int temp=0;

        for(Componente c: alc){
            temp+=c.getPrice();
        }

        return temp;
    }

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

    public String toString() {
        String temp = "";
        for(Componente c: alc){
            temp = temp + c.toString() + "\n";
        }
        return "Selected{\n"+ temp +"}";
    }
}
