package Database;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Reading r = new Reading();
        Writing w = new Writing();

        //Per favore cambiate il prodotto prima di eseguire
        //la parte commentata (se no finiamo con 300 prodotti uguali)
        //w.writeProdotti("SSD test da java",100,5,5,"NoVincolo","storage");

        ArrayList<ArrayList<String>> als = r.readProdotti(null);
        for(ArrayList<String> as : als){
            for (String s: as){
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }
}
