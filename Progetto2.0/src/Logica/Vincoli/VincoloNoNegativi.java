package Logica.Vincoli;

import Logica.Componente;
import Logica.Risorse.*;

import java.util.ArrayList;

/**
 * per le risorse numeriche
 * @author Francesco Rocchetti
 */
public class VincoloNoNegativi extends Vincolo {

    public VincoloNoNegativi(String nomeComponente){
        super(nomeComponente);
    }

    @Override
    public boolean check(ArrayList<Componente> alComponente, ArrayList<Componente> alResto) {
        ArrayList<Risorsa> rComp = findByType(alComponente, "RisorsaNumerica");
        ArrayList<ArrayList<Risorsa>> rResto = new ArrayList<>();

        for(Risorsa r: rComp){
            rResto.add(findByName(alResto,r.getName()));
        }

        int i=0;
        for(Risorsa r: rComp){
            RisorsaNumerica rn = (RisorsaNumerica) r;
            boolean checkmaipositivo = (rn.getValue()>0?false:true);
            int tot = rn.getValue();

            ArrayList<Risorsa> temp = rResto.get(i);

            for(Risorsa r2: temp){
                RisorsaNumerica rn2 = (RisorsaNumerica) r2;
                int pt = rn2.getValue();
                if(pt > 0)
                    checkmaipositivo = false;
                tot+=pt;
            }
            if(!(tot>=0 || checkmaipositivo==true)){
                System.err.println("check non negativi fallito");
                return false;
            }
            i++;
        }


        return true;
    }

    @Override
    public String nomeCompleto() {
        return "VincoloNoNegativo: "+nomeComponente;
    }
}
