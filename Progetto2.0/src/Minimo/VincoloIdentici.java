package Minimo;

import java.util.ArrayList;

/**
 * per le risorse letterali
 */

public class VincoloIdentici extends Vincolo{

    public VincoloIdentici(String nomeComponente){
        super(nomeComponente);
    }

    @Override
    boolean check(ArrayList<Componente> alComponente, ArrayList<Componente> alResto) {
        ArrayList<Risorsa> rComp = findByType(alComponente, "RisorsaLetterale");
        ArrayList<ArrayList<Risorsa>> rResto = new ArrayList<>();

        for(Risorsa r: rComp){
            rResto.add(findByName(alResto,r.getName()));
        }

        int i=0;
        for(Risorsa r: rComp){
            RisorsaLetterale rl = (RisorsaLetterale) r;
            String st = rl.getValue();

            ArrayList<Risorsa> temp = rResto.get(i);

            for(Risorsa r2: temp){
                RisorsaLetterale rl2 = (RisorsaLetterale) r2;
                String pt = rl2.getValue();
                if(!pt.equalsIgnoreCase(st)){
                    System.err.println("check identici fallito");
                    return false;
                }
            }

            i++;
        }


        return true;
    }

    @Override
    String nomeCompleto() {
        return "VincoloIdentici: "+nomeComponente;
    }
}
