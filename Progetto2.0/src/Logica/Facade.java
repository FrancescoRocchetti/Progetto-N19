package Logica;

import java.util.ArrayList;

/**
 * @author Francesco Rocchetti
 */
public class Facade {

    private Gestione g;
    private Scrittura s;
    private Database.Facade fdb;

    public Facade(){
        g = new Gestione();
        s = Scrittura.getInstance();
        fdb = Database.Facade.getInstance();
    }

    /**mostra la lista delle componenti
     * il primo elemento di ogni Arraylist<String> è l'id del componente
     * il secondo sono le informazioni vere e proprie
     *
     * @param type
     * @return
     */
    public ArrayList<ArrayList<String>> getListbyType(String type){
        g.leggi(type);
        return g.getMostrati();
    }

    /**mostra tutte componenti
     * in questo caso Arraylist<String> ha una lunghezza di 7
     *
     * @return
     */
    public ArrayList<ArrayList<String>> getAll(){
        return fdb.readComp();
    }

    /**mostra i componenti selezionati
     * rimangono valide le stesse considerazioni di getListbyType
     *
     * @return
     */
    public ArrayList<ArrayList<String>> getSelectedList(){
        return g.getSelected();
    }

    //aggiunge un componente alla lista dei selezionati
    public boolean selectComponent(int id){
        return g.aggiungi(id);
    }

    //rimuove un componente dalla lista dei selezionati
    public void removeComponent(int id){
        g.rimuovi(id);
    }

    public void removeComponent(int id[]){
        for(int i: id){
            g.rimuovi(i);
        }
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

    //mostra tutti le risorse applicabili
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

    //mostra tutte le caratteristiche di un componente (per Admin)
    public ArrayList<ArrayList<String>> getCaratteristichebyId(int id){
        return fdb.readCaratteristiche(id);
    }

    //mostra i dettagli di un componente (cioè le sue risorse)
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

    /**
     * fine selezione per interfaccia standard
     * @return
     */
    public boolean confirm(){
        return g.confirm();
    }

    /**
     * fine selezione per interfaccia web
     * @param info  ip dell'utente
     */
    public void confirm(String info){
        g.confirm(info);
    }

    //i componenti selezionati costituiscono un pc "accettabile"
    public boolean allOk(){
        return g.checkFinale();
    }

    /**aggiunge un componente al DB
     *
     * @param nome
     * @param prezzo
     * @param n quantità
     * @param rating    da 0 a 5
     * @param vincolo   Arraylist<String> dei vincoli da applicare(utilizzare quelli passati da questa facade)
     * @param tipo
     * @return
     */
    public boolean addComp(String nome, int prezzo, int n, int rating, ArrayList<String> vincolo, String tipo){
        return s.addComp(nome,prezzo,n,rating,vincolo,tipo);
    }

    /**aggiunge una caratteristica al db
     *
     * @param id del componente a cui è riferita la caratteristica
     * @param tipo (utilizzare i tipi di caratteristica passati da questa facade)
     * @param nome nome della caratteristica (es. Tecnologia Ram)
     * @param val valore
     * @return
     */
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

    /**mostra i warning
     * NON ANCORA IMPLEMENTATA
     * @return
     */
    public String getMessage(){
        return null;
    }

    //resetta la lista dei componenti selezionati
    public void resetSelected(){
        g.resetSelected();
    }

    /**resetta la connessione al DB
     *
     * forse non è necessaria
     */
    public void resetConnection(){

    }

    //build automatica
    public void automatic(int budget){
        g.automatic(budget);
    }

    public ArrayList<ArrayList<String>> getSales(){
        return fdb.getSales();
    }

}
