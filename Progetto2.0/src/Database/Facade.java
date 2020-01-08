package Database;

import java.util.ArrayList;

public class Facade {

    private Deleting d;
    private Reading r;
    private Updating u;
    private Writing w;
    private Cache c;
    private static Facade ourInstance = new Facade();

    private Facade(){
        d = new Deleting();
        r = new Reading();
        u = new Updating();
        w = new Writing();
        c = new Cache();
        c.start();
    }

    public static Facade getInstance() {
        return ourInstance;
    }

    /**scala le quantità dei componenti acquistati
     *
     * @param ids lista di id da componenti che si vogliono comprare
     * @return lista dei componenti che non si è riusciti a comprare
     */
    public ArrayList<Integer> buy(ArrayList<Integer> ids, String info){
        ArrayList temp = u.buy(ids);
        w.writeSale(ids,temp,info);
        return temp;
    }

    /**
     * login per gli admin
     * @param user
     * @param password
     * @return
     */
    public boolean login(String user, String password){
        return r.login(user,password);
    }

    /**
     * aggiunge un componente al DB
     */
    public boolean addComponent(String nome, int prezzo, int n, int rating, String vincolo, String tipo){
        return w.writeProdotti(nome,prezzo,n,rating,vincolo,tipo);
    }

    /**aggiunge una caratteristica al DB
     *
     * @param id
     * @param tipo
     * @param nome
     * @param val
     * @return
     */
    public boolean addCaratteristica(int id, String tipo, String nome, String val){
        return w.writeCaratteristica(id,tipo,nome,val);
    }


    /**rimuove un componente dal DB(e relative caratteristiche)
     *
     * @param id
     * @return
     */
    public boolean removeComponent(int id){
        return d.deleteProdotto(id);
    }

    /**
     * rimuove dal database una caratteristica di un prodotto
     * @param id del prodotto
     * @param nome della caratteristica
     * @return
     */
    public boolean removeRisorsa(int id, String nome){
        return d.deleteCaratteristica(id, nome);
    }

    /**legge tutti le componenti dal DB
     *
     * @return
     */
    public ArrayList<ArrayList<String>> readComp(){
        return c.readProdotti(null);
    }

    /**legge le componenti dal DB per tipo
     *
     * @param type
     * @return
     */
    public ArrayList<ArrayList<String>> readComp(String type){
        return c.readProdotti(type);
        //return r.readProdotti(type);
    }

    /**legge componenti per nome
     *
     * @param nome
     * @return
     */
    public ArrayList<ArrayList<String>> readCompSpecifico(String nome) {
        return r.readProdottoSpecifico(nome);
    }

    /**legge le caratteristiche dal DB per id
     *
     * @param id
     * @return
     */
    public ArrayList<ArrayList<String>> readCaratteristiche(int id){
        return c.readCaratteristiche(id);
    }

    /**
     * legge tutte le caratteristiche presenti su db
     * @return
     */
    public ArrayList<ArrayList<String>> readAllCaratteristiche(){
        return c.readCaratteristiche();
        //return r.readAllCaratteristiche();
    }

    /**
     * legge tutti i nomi utilizzati nella tabella CARATTERISTICHE
     * @return
     */
    public ArrayList<String> readAllTypeFromCaratteristiche(){
        return  r.readAllTypeFromCaratteristiche();
    }

    /**
     * aggiorna la quantità di un componente
     * @param id
     * @param n
     * @return
     */
    public boolean updateN(int id, int n){
        return u.updateN(id,n);
    }

    /**
     * aggiorna il prezzo di un componente
     * @param id
     * @param price
     * @return
     */
    public boolean updatePrice(int id, int price){
        return u.updatePrice(id,price);
    }

    /**
     * aggiorna il rating di un componente
     * @param id
     * @param rating
     * @return
     */
    public boolean updateRating(int id, int rating){
        return u.updateRating(id,rating);
    }

    /**
     * legge la tabella tipi (e di conseguenza mostra i tipi di componenti disponibili)
     * @return
     */
    public ArrayList<String> readTipi(){
        return c.readTypes();
        //return r.readTypes();
    }

    /**
     * aggiunge un tipo di prodotto
     * @param nome
     * @return
     */
    public boolean scriviTipo(String nome){
        return w.writeTipo(nome);
    }

    /**
     * rimuove un tipo di prodotto
     * @param nome
     * @return
     */
    public boolean rimuoviTipo(String nome){
        return d.deleteTipo(nome);
    }

    /**
     * legge il registro delle vendite
     * @return
     */
    public ArrayList<ArrayList<String>> getSales(){
        return r.readSales();
    }

}
