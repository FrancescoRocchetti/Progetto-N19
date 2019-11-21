package Database.test;

import Database.Deleting;
import Database.Reading;
import Database.Writing;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Reading r = new Reading();
        Writing w = new Writing();
        Deleting d = new Deleting();

        /* Per favore cambiate il prodotto prima di eseguire
        la parte commentata (se no finiamo con 300 prodotti uguali)
        w.writeProdotti("SSD test da java",100,5,5,"NoVincolo","storage");*/

        //stessa cosa per le caratteristiche//
        //w.writeCaratteristica(2,"RisorsaLetterale","tipo","DDR4");//

        /*d.deleteProdotto(7);*/

        /*ArrayList<ArrayList<String>> als = r.readProdotti(null);
        for(ArrayList<String> as : als){
            for (String s: as){
                System.out.print(s+" ");
            }
            System.out.println();
        }*/

        /*ArrayList<ArrayList<String>> als = r.readCaratteristicaByID(1);
        for(ArrayList<String> as : als){
            for (String s: as){
                System.out.print(s+" ");
            }
            System.out.println();
        }*/

        /*ArrayList<ArrayList<String>> als = r.readAllCaratteristiche();
        for(ArrayList<String> as : als){
            for (String s: as){
                System.out.print(s+" ");
            }
            System.out.println();
        }*/

    }
}
