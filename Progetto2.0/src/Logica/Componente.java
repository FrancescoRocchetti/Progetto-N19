package Logica;

import Logica.Risorse.Risorsa;
import Logica.Vincoli.Vincolo;

import java.util.ArrayList;

/**
 * classe per la componente
 *
 * come nel vecchio progetto
 * ma le risorse sono un array
 * e contiene anche i vincoli associati ad ogni singolo componente
 */

public class Componente implements Comparable{

    private int id;
    private String name;
    private String type;
    private int price;
    private int ranking;
    private int n;
    private String vincoli;

    private ArrayList<Risorsa> risorse;
    private ArrayList<Vincolo> alv;

    public Componente(int id, String name, int price, int n, int ranking, String vincoli, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.n = n;
        this.ranking = ranking;
        this.vincoli = vincoli;

        risorse = new ArrayList<>();
        alv = new ArrayList<>();
    }

    public void addRisorsa(Risorsa r){
        risorse.add(r);
    }

    public void addVincolo(Vincolo v){
        alv.add(v);
    }

    public ArrayList<String> getString() {

        ArrayList<String> temp = new ArrayList<>();

        String temp1="";
        for(Risorsa r : risorse){
            temp1 = temp1.concat(r.toString()) + " ";
        }
        temp1 = temp1 +";";
        for(Vincolo v : alv){
            temp1 = temp1.concat(v.getClass().getSimpleName()) + " ";
        }

        temp.add(String.valueOf(id));

        temp.add( "Componente{" +
               "name='" + name + '\'' +
               ", type='" + type + '\'' +
               ", price=" + price +
               ", ranking=" + ranking +
               ", risorse=" + temp1 +
               '}');

        return temp;
    }

    @Override
    public int compareTo(Object o) {
        Componente c = (Componente) o;
        return type.compareTo( c.getType());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getRanking() {
        return ranking;
    }

    public int getN() {
        return n;
    }

    public String getVincoli() {
        return vincoli;
    }

    public ArrayList<Risorsa> getRisorse() {
        return risorse;
    }

    public ArrayList<Vincolo> getAlv() {
        return alv;
    }
}
