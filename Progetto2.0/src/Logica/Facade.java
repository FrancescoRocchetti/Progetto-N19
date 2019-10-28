package Logica;

import java.util.ArrayList;

public class Facade {

    private Gestione g;
    private Scrittura s;
    private Database.Facade fdb;
    private static Facade ourInstance = new Facade();

    private Facade(){
        g = new Gestione();
        s = new Scrittura();
        fdb = Database.Facade.getInstance();
    }

    public static Facade getInstance() {
        return ourInstance;
    }

    //mostra la lista delle componenti
    public ArrayList<String> getListbyType(String type){
        g.leggi(type);
        return g.getMostrati();
    }

    //mostra tutte componenti
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

    //mostra tutti i tipi di componenti
    public ArrayList<String> getAllTypes(){
        return null;
    }

    //mostra tutti i vincoli applicabili
    public ArrayList<String> getAllVincoli(){
        return null;
    }

    //mostra tutti i tipi(nomi) di risorse già presenti
    public ArrayList<String> getAllTipiDiRisorse(){
        return null;
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
        return g.buy();
    }

    //i componenti selezionati costituiscono un pc "accettabile"
    public boolean allOk(){
        return g.checkFinale();
    }

    //aggiunge un componente al DB
    public boolean addComp(String nome, int prezzo, int n, int rating, String vincolo, String tipo){
        return fdb.addComponent(nome,prezzo,n,rating,vincolo,tipo);
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
