package Minimo;

import java.util.ArrayList;

/**
 * è solamente una classe di test
 */

public class NoVincolo extends Vincolo{

    public NoVincolo(String nomeComponente){
        super(nomeComponente);
    }

    @Override
    boolean check(ArrayList<Componente> alComponente, ArrayList<Componente> alResto) {
        //System.err.println("check no vincolo");
        return true;
    }

    @Override
    String nomeCompleto() {
        return "NoVincolo: "+nomeComponente;
    }
}
