package Minimo;

import java.util.ArrayList;

/**
 * per le risorse numeriche
 */

public class VincoloNoNegativi extends Vincolo{

    public VincoloNoNegativi(String nomeComponente){
        super(nomeComponente);
    }

    @Override
    boolean check(ArrayList<Componente> alComponente, ArrayList<Componente> alResto) {
        ArrayList<Risorsa> rComp = findByType(alComponente, "RisorsaNumerica");
        ArrayList<ArrayList<Risorsa>> rResto = new ArrayList<>();

        //questo check probabilmente è inutile

        /*ArrayList<Risorsa> checkdoppi = new ArrayList<>();
        for(Risorsa r: rComp){
            ArrayList<Risorsa> temp = findRisorsa(rComp,r.getName());
            if(temp.size()==1){
                checkdoppi.add(r);
            }
            else{
                int valtemp=0;
                for(Risorsa r1: temp){
                    valtemp+=((RisorsaNumerica) r1).getValue();
                }
                checkdoppi.add(new RisorsaNumerica(r.getName(),valtemp));
            }
        }
        rComp = checkdoppi;*/

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
    String nomeCompleto() {
        return "VincoloNoNegativo: "+nomeComponente;
    }
}
