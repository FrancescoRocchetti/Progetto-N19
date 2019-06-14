package Gestione;

import Components.PCParts;
import Constraints.ConsistencyConstraint;
import Constraints.Warning;
import Interface.InserimentoSpecifiche;
import InterfacingDB.*;

import java.util.ArrayList;

import Components.AbstractComponent;

public class GestoreOperazioni implements ObserverGO{
    private boolean modified;
    private boolean loggedIn;
    private ManagerDB mdb;
    private String descrizione;
    private InserimentoSpecifiche ins;
    private ThreadAdd ta;

    public GestoreOperazioni(){
        modified = false;
        loggedIn = false;
        mdb = new ManagerDB();
        descrizione = null;
        ta = new ThreadAdd(this);
        ta.start();
    }

    public void setIns(InserimentoSpecifiche ins){
        this.ins = ins;
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

    public void insertComponent(PCParts componente, int quantita, int prezzo, int valutazione) {
        String[] str = {
                "1",
                componente.name().toUpperCase(),
                descrizione,
                String.valueOf(quantita),
                String.valueOf(prezzo),
                String.valueOf(valutazione)};
        if (descrizione != null && checkValidation(str)) {
            ta.addComp(componente, descrizione, quantita,prezzo,valutazione);
        }
    }

    public boolean canAdd(){
        return descrizione!=null;
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
    public void updateAddStatus(boolean status) {
        modified = status;
        ins.updateAdd(status);
    }
}
