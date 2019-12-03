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

    //login admin
    public boolean login(String user, String password){
        return r.login(user,password);
    }

    //aggiunge un componente al DB
    public boolean addComponent(String nome, int prezzo, int n, int rating, String vincolo, String tipo){
        return w.writeProdotti(nome,prezzo,n,rating,vincolo,tipo);
    }

    //aggiunge una caratteristica al DB
    public boolean addCaratteristica(int id, String tipo, String nome, String val){
        return w.writeCaratteristica(id,tipo,nome,val);
    }

   /*
   //modifica quantià, valutazione e prezzo
    public boolean upadteComponent(){
        return true;
    }*/

    //rimuove un componente dal DB(e relative caratteristiche)
    public boolean removeComponent(int id){
        return d.deleteProdotto(id);
    }

    public boolean removeRisorsa(int id, String nome){
        return d.deleteCaratteristica(id, nome);
    }

    //legge tutti le componenti dal DB
    public ArrayList<ArrayList<String>> readComp(){
        return c.readProdotti(null);
        //return r.readProdotti(null);
    }

    //legge le componenti dal DB
    public ArrayList<ArrayList<String>> readComp(String type){
        return c.readProdotti(type);
        //return r.readProdotti(type);
    }

    //legge componenti per nome
    public ArrayList<ArrayList<String>> readCompSpecifico(String nome) {
        return r.readProdottoSpecifico(nome);
    }

    //legge le caratteristiche dal DB
    public ArrayList<ArrayList<String>> readCaratteristiche(int id){
        return c.readCaratteristiche(id);
        //return r.readCaratteristicaByID(id);
    }

    public ArrayList<ArrayList<String>> readAllCaratteristiche(){
        return c.readCaratteristiche();
        //return r.readAllCaratteristiche();
    }

    public ArrayList<String> readAllTypeFromCaratteristiche(){
        return  r.readAllTypeFromCaratteristiche();
    }

    public boolean updateN(int id, int n){
        return u.updateN(id,n);
    }

    public boolean updatePrice(int id, int price){
        return u.updatePrice(id,price);
    }

    public boolean updateRating(int id, int rating){
        return u.updateRating(id,rating);
    }

    public ArrayList<String> readTipi(){
        return c.readTypes();
        //return r.readTypes();
    }

    public boolean scriviTipo(String nome){
        return w.writeTipo(nome);
    }

    public boolean rimuoviTipo(String nome){
        return d.deleteTipo(nome);
    }

}
