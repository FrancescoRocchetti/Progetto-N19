package Logica;

import java.util.ArrayList;

public class Facade {

    private Gestione g;
    private Scrittura s;
    private Database.Facade fdb;

    public Facade(){
        g = new Gestione();
        s = Scrittura.getInstance();
        fdb = Database.Facade.getInstance();
    }

    //mostra la lista delle componenti
    public ArrayList<String> getListbyType(String type){
        g.leggi(type);
        return g.getMostrati();
    }

    //mostra tutte componenti
    public ArrayList<ArrayList<String>> getAll(){
        return fdb.readComp();
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
    public boolean login(String user, String password){
        return fdb.login(user, password);
    }

    //mostra tutti i tipi di componenti
    public ArrayList<String> getAllTypes(){
        return fdb.readTipi();
    }

    //aggiunge un tipo di componenti
    public boolean aggiungiTipo(String nome){
        return fdb.scriviTipo(nome);
    }

    //elimina un tipo di componenti
    public boolean rimuoviTipo(String nome){
        return fdb.rimuoviTipo(nome);
    }

    //mostra tutti i vincoli applicabili
    public ArrayList<String> getAllVincoli(){
        return s.getVincoli();
    }

    //mostra tutti i vincoli applicabili
    public ArrayList<String> getAllTipiDiRisorsa(){
        return s.getRisorse();
    }

    //mostra tutti i tipi(nomi) di risorse già presenti (es. nsocket)
    public ArrayList<String> getAllNomiDiRisorse(){
        return fdb.readAllTypeFromCaratteristiche();
    }

    public ArrayList<ArrayList<String>> getAllCaratteristiche(){
        return fdb.readAllCaratteristiche();
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
        return s.addComp(nome,prezzo,n,rating,vincolo,tipo);
    }

    //aggiunge una caratteristica dal DB
    public boolean addDetail(int id, String tipo, String nome, String val){
        return fdb.addCaratteristica(id,tipo,nome,val);
    }

    //toglie una caratteristica dal DB
    public boolean dropDetail(int id, String nome){
        return fdb.removeRisorsa(id, nome);
    }

    //rimuove componente dal DB
    public boolean dropComp(int id){
        return fdb.removeComponent(id);
    }

    public boolean updateN(int id, int n){
        return fdb.updateN(id,n);
    }

    public boolean updatePrice(int id, int price){
        return fdb.updatePrice(id,price);
    }

    public boolean updateRating(int id, int rating){
        return fdb.updateRating(id,rating);
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
    public void automatic(int budget){
        g.automatic(budget);
    }

}
