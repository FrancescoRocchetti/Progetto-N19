package Gestione;

import Components.AbstractComponent;
import Components.*;
import InterfacingDB.PCParts;
import InterfacingDB.Reading;

import java.util.ArrayList;

public class TestFabio {
    public static void main(String[] args) {
        try {
            Reading r = new Reading();
            ArrayList<String[]> s = r.read(PCParts.CPU);

            /**
            * in teoria mi dovrebbe restituire un array di stringhe
            * (una riga per componente e non una riga per caratteristica)
            * invece restituisce solo un componente diviso in più stringhe
            * Potrebbe funzionare anche così ma è molto più lento
            *
            * --------
            *
            * Scusa Rocca, ma la tua classe mi impediva di compilare, così
            * l'ho corretta.
            *
            * Auguroni,
            *
            * Fabbio.
            * */

            s = r.read(PCParts.GPU);
            SelectedComponents sc = new SelectedComponents();

            for(String[] c: s){
                sc.addCList(new GPU(c));
            }

            ArrayList<AbstractComponent> ar = sc.getAR();

            for(AbstractComponent c : ar) {
                System.out.println(c);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
