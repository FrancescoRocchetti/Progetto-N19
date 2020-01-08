package Database;

import java.util.ArrayList;

/**
 * thread per velocizzare la lettura dal db
 */
public class Cache extends Thread{

    private Reading r;

    private ArrayList<ArrayList<String>> prodotti;
    private ArrayList<ArrayList<String>> caratteristiche;
    private ArrayList<String> types;

    public Cache(){
        super("cache");
        r = new Reading();
        prodotti = r.readProdotti(null);
        caratteristiche = r.readAllCaratteristiche();
        types = r.readTypes();

    }

    @Override
    public void run() {
        try{
            while (true){
                sleep(30000);   //t=30s
                prodotti = r.readProdotti(null);
                caratteristiche = r.readAllCaratteristiche();
                types = r.readTypes();
                }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> readProdotti(String type){
        if (type!=null){
            ArrayList<ArrayList<String>> temp = new ArrayList<>();

            for(ArrayList<String> ars: prodotti){
                if (ars.get(6).equalsIgnoreCase(type))
                    temp.add(ars);
            }

            return temp;
        } else
            return prodotti;
    }

    public ArrayList<ArrayList<String>> readCaratteristiche(){
        return caratteristiche;
    }

    public ArrayList<ArrayList<String>> readCaratteristiche(int id){
        ArrayList<ArrayList<String>> temp = new ArrayList<>();

        for(ArrayList<String> ars: caratteristiche){
            if(Integer.parseInt(ars.get(0))==id)
                temp.add(ars);
        }

        return temp;
    }

    public ArrayList<String> readTypes(){
        return types;
    }
}
