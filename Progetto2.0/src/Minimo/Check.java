package Minimo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * questa è la classe che fa partire la verifica dei vincoli
 * tecnicamente è solamente un metodo static e potrebbe essere spostato in un'altra classe
 * è qui solo per comodità
 */

public class Check {

    public static boolean c(ArrayList<Componente> alc){
        Collections.sort(alc);
        int size = alc.size();
        ArrayList<Componente> a1;
        ArrayList<Componente> a2;
        ArrayList<Vincolo> alv;

        for(int i=0; i<size; i++){
            a1 = new ArrayList<>();
            a2 = new ArrayList<>();
            alv = new ArrayList<>();
            int loopck=0;

            a1.add(alc.get(i));
            while (i+1<size && loopck==0) {
                loopck++;
                if(a1.get(0).getName().equalsIgnoreCase(alc.get(i+1).getName()))
                {
                    a1.add(alc.get(i + 1));
                    loopck--;
                    i++;
                }
            }
            a2.addAll(alc);
            a2.removeAll(a1);

            alv.addAll(a1.get(0).getAlv());
            for (Vincolo v: alv) {
                if(!v.check(a1,a2))
                    return false;
            }
        }
        return true;
    }
}
