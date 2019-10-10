package Minimo;

import java.util.ArrayList;

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

    public String toString() {
        String temp = "";
        for(Componente c: alc){
            temp = temp + c.toString() + "\n";
        }
        return "Selected{\n"+ temp +"}";
    }
}
