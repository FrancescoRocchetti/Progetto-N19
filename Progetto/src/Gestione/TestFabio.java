package Gestione;

import Components.AbstractComponent;
import Components.CPU;
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

            s = r.read(PCParts.CPU);
            ArrayList<AbstractComponent> ar = new ArrayList<>();

            for(String[] c: s){
                ar.add(new CPU(c));
            }

            for(AbstractComponent c : ar) {
                System.out.println(c);
            }


            /*s = r.read(PCParts.COOLER);
            for(String[] arr1: s) {
                for (String arr2 : arr1) {
                    System.out.print(arr2+"\t");
                }
                System.out.println();
            }

            s = r.read(PCParts.GPU);
            for(String[] arr1: s) {
                for (String arr2 : arr1) {
                    System.out.print(arr2+"\t");
                }
                System.out.println();*/


        }
        catch (Exception e){
            System.err.println("ops");
        }
    }

}
