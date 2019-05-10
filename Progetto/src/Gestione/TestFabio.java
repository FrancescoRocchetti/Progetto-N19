package Gestione;

import InterfacingDB.PCParts;
import InterfacingDB.Reading;

public class TestFabio {
    public static void main(String[] args) {
        try {
            Reading r = new Reading();
            String[] s = r.read(PCParts.CPU);
            for(String g: s){
                System.out.println(g);
            }

            /*
            * in teoria mi dovrebbe restituire un array di stringhe
            * (una riga per componente e non una riga per caratteristica)
            * invece restituisce solo un componente diviso in più stringhe
            * Potrebbe funzionare anche così ma è molto più lento
            * */

            s = r.read(PCParts.CPU);
            for(String g: s){
                System.out.println(g);
            }
            s = r.read(PCParts.CPU);
            for(String g: s){
                System.out.println(g);
            }

        }
        catch (Exception e){
            System.err.println("ops");
        }
    }

}
