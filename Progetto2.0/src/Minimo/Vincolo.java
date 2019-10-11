package Minimo;

import java.util.ArrayList;

/**
 * classe astratta dei vincoli
 * contiene anche i due metodi find utilizatti
 * dai vincoli per cercare le risorse su cui operare
 */

public abstract class Vincolo {

    protected String nomeComponente;

    public Vincolo(String nomeComponente){
        this.nomeComponente=nomeComponente;
    }

    //trova le risorse con un certo nome a partire da un arraylist di componenti
    protected ArrayList<Risorsa> findByName(ArrayList<Componente> alc, String name){
        ArrayList<Risorsa> temp = new ArrayList<>();
        for(Componente c: alc){
            ArrayList<Risorsa> tempR = c.getRisorse();
            for(Risorsa r: tempR) {
                if(r.getName().equalsIgnoreCase(name)){
                    temp.add(r);
                }
            }
        }
        return temp;
    }

    //trova le risorsecon un certo nome in un arraylistdi risorse
    //TODO: rimuovere codice riciclato
    protected ArrayList<Risorsa> findRisorsa(ArrayList<Risorsa> alr,String name){
        ArrayList<Risorsa> temp = new ArrayList<>();
        for(Risorsa r: alr){
            if(r.getName().equalsIgnoreCase(name)){
                temp.add(r);
            }
        }
        return temp;
    }

    //trova le risorse di un certo tipo in un arraylist di componenti
    protected ArrayList<Risorsa> findByType(ArrayList<Componente> alc, String type){
        ArrayList<Risorsa> temp = new ArrayList<>();
        for(Componente c: alc){
            ArrayList<Risorsa> tempR = c.getRisorse();
            for(Risorsa r: tempR) {
                if (r.getClass().getSimpleName().equalsIgnoreCase(type)) {
                    temp.add(r);
                }
            }
        }
        return temp;
    }

    abstract boolean check(ArrayList<Componente> alComponente, ArrayList<Componente> alResto);

    abstract String nomeCompleto();
}
