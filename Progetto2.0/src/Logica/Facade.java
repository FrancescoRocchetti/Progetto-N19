package Logica;

import java.util.ArrayList;

public class Facade {

    private Gestione g;

    public Facade(){
        g = new Gestione();
    }

    //mostra la lista delle componenti
    public ArrayList<String> getListbyType(String type){
        g.leggi(type);
        return g.getMostrati();
    }

    //mostra tutto
    public ArrayList<String> getAll(){
        return null;
    }

    //mostra i componenti selezionati
    public ArrayList<String> getSelectedList(){
        return g.getSelected();
    }

    //aggiunge un componente alla lista dei selezionati
    public void selectComponent(int id){
        g.aggiungi(id);
    }

    //rimuove un componente dalla lista dei selezionati
    public void removeComponent(int id){
        g.rimuovi(id);
    }

    //login admin
    public boolean login(){
        return true;
    }

    //mostra i dettagli
    public ArrayList<String> getDetail(int id){
        return g.dettagli(id);
    }

    //mostra il prezzo totale
    public int getTotPrice(){
        return g.printCost();
    }

    //mostra la potenza totale
    public int getTotPower(){
        return g.printPower();
    }

    //fine selezione
    public boolean confirm(){
        return true;
    }

    //aggiunge un componente al DB
    public boolean addComp(){
        return true;
    }

    //aggiunge una caratteristica dal DB
    public boolean addDetail(){
        return true;
    }

    //toglie una caratteristica dal DB
    public boolean dropDetail(){
        return true;
    }

    //rimuove componente dal DB
    public boolean dropComp(){
        return true;
    }

    //aggiorna componente dal DB
    public boolean updateComp(){
        return true;
    }

    //mostra i warning
    public String getMessage(){
        return null;
    }

    //resetta la lista dei componenti selezionati
    public void resetSelected(){
        g.resetS();
    }

    //resetta la connessione al DB
    public void resetConnection(){

    }

    //build automatica
    public void automatic(){

    }

}
