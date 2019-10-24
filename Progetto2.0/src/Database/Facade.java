package Database;

import java.util.ArrayList;

public class Facade {

    //scala le quantità dei componenti acquistati
    public boolean buy(){
        return true;
    }

    //login admin
    public boolean login(){
        return true;
    }

    //aggiunge un componente al DB
    public boolean addComponent(){
        return true;
    }

    //aggiunge una caratteristica al DB
    public boolean addCaratteristica(){
        return true;
    }

    //modifica quantià, valutazione e prezzo
    public boolean upadteComponent(){
        return true;
    }

    //rimuove un componente dal DB(e relative caratteristiche)
    public boolean removeComponent(){
        return true;
    }

    //legge tutti le componenti dal DB
    public ArrayList<ArrayList<String>> readComp(){
        return null;
    }

    //legge le componenti dal DB
    public ArrayList<ArrayList<String>> readComp(String type){
        return null;
    }

    //legge le caratteristiche dal DB
    public ArrayList<ArrayList<String>> readCaratteristiche(int i){
        return null;
    }

}
