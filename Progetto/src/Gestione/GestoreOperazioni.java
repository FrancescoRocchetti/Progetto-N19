package Gestione;

import Components.PCParts;
import Interface.InserimentoSpecifiche;
import Interface.Remove;
import Interface.Update;
import InterfacingDB.*;

import java.util.ArrayList;

import Components.AbstractComponent;

public class GestoreOperazioni implements ObserverGO{
    private static final String RMV = "RMV";
    private static final String UPD = "UPD";
    private static final String LST = "LST";

    private boolean modified;
    private boolean loggedIn;
    private ManagerDB mdb;
    private String descrizione;
    private InserimentoSpecifiche ins;
    private Remove rmv;
    private Update upd;
    private ThreadAdd ta;
    private ThreadList tl;
    private ThreadRemove tr;
    private String mode; //RMV se si usa Remove, UPD se si usa Update e LST se si usa CompList

    public GestoreOperazioni(){
        modified = false;
        loggedIn = false;
        mdb = new ManagerDB();
        descrizione = null;
        ta = new ThreadAdd(this);
        ta.start();
        tl = new ThreadList(this);
        tl.start();
        tr = new ThreadRemove(this);
        tr.start();
    }

    public void setIns(InserimentoSpecifiche ins){
        this.ins = ins;
    }

    public void setRemoveMode(Remove rmv){
        mode = RMV;
        this.rmv = rmv;
    }

    public void setUpdateMode(Update upd){
        mode = UPD;
        this.upd = upd;
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

    public void remove(int id){
        tr.removeCompById(id);
    }

    public void getListComponents(){
        tl.getListOf(null);
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

    public void updateList(ArrayList<AbstractComponent> arr) {
        switch(mode) {
            case RMV:{
                if (arr != null) rmv.successList(arr);
                else rmv.failureList();
                break;
            }
            case UPD:{
                break;
            }
        }
    }

    @Override
    public void remove(boolean status) {
        if (status) rmv.successRemove();
        else rmv.failureRemove();
    }
}
