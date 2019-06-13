package Gestione;

import Gestione.Validation;
import Interface.InserimentoSpecifiche;
import Interface.Piattaforma;
import InterfacingDB.*;

import java.util.ArrayList;

import Components.AbstractComponent;

public class GestoreOperazioni implements ObserverGS{
    private boolean modified;
    private boolean loggedIn;
    private ManagerDB mdb;
    private String descrizione;
    //private ThreadInventory t;

    public GestoreOperazioni(){
        modified = false;
        loggedIn = false;
        mdb = new ManagerDB();
        descrizione = null;
        /*t = new ThreadInventory(this);
        t.start();*/
    }

    public ArrayList<AbstractComponent> read(PCParts comp){
        return mdb.read(comp);
    }

    public boolean accessToDB(String username, String password) {
        if (mdb.login(username, password)) {
            loggedIn = true;
            return true;
        }
        return false;
    }

    public boolean insertComponent(PCParts componente, int quantita, int prezzo, int valutazione) {
        String[] str = {"1",
                componente.name().toUpperCase(),
                descrizione,
                String.valueOf(quantita),
                String.valueOf(prezzo),
                String.valueOf(valutazione)};
        if (descrizione != null && checkValidation(str)) {
            if(mdb.write(componente, descrizione, quantita, prezzo, valutazione)) {
                modified = true;
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean updateComponent(int index, int qty) {
        modified = mdb.update(index, qty);
        return modified;
    }

    public boolean remove(int id){
        modified = mdb.remove(id);
        return modified;
    }

    public int getQuantityByID(int id) {
        return mdb.getQuantityByID(id);

    }

    public ArrayList<AbstractComponent> getComponentsFromDB(PCParts parts) {
        return mdb.read(parts);

    }

    public boolean checkValidation(String[] str){
        return Validation.check(str);
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isModified() {
        return modified;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    @Override
    public void update(ArrayList<AbstractComponent> arr) {

    }
}
