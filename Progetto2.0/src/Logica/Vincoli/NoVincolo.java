package Logica.Vincoli;

import Logica.Componente;
import java.util.ArrayList;

/**
 * è solamente una classe di test
 * @author Francesco Rocchetti
 */
public class NoVincolo extends Vincolo {

    public NoVincolo(String nomeComponente){
        super(nomeComponente);
    }

    @Override
    public boolean check(ArrayList<Componente> alComponente, ArrayList<Componente> alResto) {
        //System.err.println("check no vincolo");
        return true;
    }

    @Override
    public String nomeCompleto() {
        return "NoVincolo: "+nomeComponente;
    }
}
