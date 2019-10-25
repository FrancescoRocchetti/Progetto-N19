package Logica.Vincoli;

import Logica.Componente;
import Logica.Risorse.Risorsa;
import Logica.Risorse.RisorsaLetterale;
import Logica.Risorse.RisorsaMultipla;

import java.util.ArrayList;

/**
 *  vincolo per le risorse multiple
 *  ANCORA DA TESTARE
 */

public class VincoloMultiplo extends Vincolo{

    public VincoloMultiplo(String nomeComponente) {
        super(nomeComponente);
    }

    @Override
    public boolean check(ArrayList<Componente> alComponente, ArrayList<Componente> alResto) {
        ArrayList<Risorsa> rComp = findByType(alComponente, "RisorsaMultipla");
        ArrayList<ArrayList<Risorsa>> rResto = new ArrayList<>();

        for(Risorsa r: rComp){
            rResto.add(findByName(alResto,r.getName()));
        }

        int i=0;
        for(Risorsa r: rComp){
            RisorsaMultipla rl = (RisorsaMultipla) r;
            String st = rl.getValue();

            ArrayList<Risorsa> temp = rResto.get(i);

            int ck =0;
            for(Risorsa r2: temp){
                String pt = (String) r2.getValue();
                if(pt.equalsIgnoreCase(st)){
                    ck++;
                }
            }
            if(ck==0) {
                System.err.println("check multiplo fallito");
                return false;
            }

            i++;
        }

        return true;
    }

    @Override
    public String nomeCompleto() {
        return  "VincoloMultiplo: "+nomeComponente;
    }
}
