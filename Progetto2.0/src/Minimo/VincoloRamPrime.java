package Minimo;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.StrictMath.abs;

public class VincoloRamPrime extends Vincolo {

    private Integer[] prime ={1,2,3,5,7,11,13,17};

    public VincoloRamPrime(String nomeComponente) {
        super(nomeComponente);
    }

    @Override
    boolean check(ArrayList<Componente> alComponente, ArrayList<Componente> alResto) {
        alComponente.addAll(alResto);
        ArrayList<Risorsa> rComp = findByName(alComponente,"banchiram");

        int temp = 0;
        for(Risorsa r: rComp){
            RisorsaNumerica rn = (RisorsaNumerica) r;
            if(rn.getValue()<0){
                temp+=abs(rn.getValue());
            }
        }

        return Arrays.asList(prime).contains(temp);
    }

    @Override
    String nomeCompleto() {
        return "VincoloRamPrime: "+nomeComponente;
    }
}
