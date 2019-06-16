package Gestione;

import Components.PCParts;
import Interface.CompList;
import Interface.InserimentoSpecifiche;
import Interface.Remove;
import Interface.Update;
import Interface.Login;

import java.util.ArrayList;

import Components.AbstractComponent;

public class GestoreOperazioni implements ObserverGO{
    private static final String RMV = "RMV";
    private static final String UPD = "UPD";
    private static final String LST = "LST";

    private boolean modified;
    private boolean loggedIn;
    private String descrizione;
    private InserimentoSpecifiche ins;
    private Login l;
    private Remove rmv;
    private Update upd;
    private CompList lst;
    private ThreadLogin tlog;
    private ThreadAdd ta;
    private ThreadList tl;
    private ThreadRemove tr;
    private ThreadUpdate tu;
    private String mode; //RMV se si usa Remove, UPD se si usa Update e LST se si usa CompList

    public GestoreOperazioni(){
        modified = false;
        loggedIn = false;
        descrizione = null;
        tlog = new ThreadLogin(this);
        tlog.start();
        ta = new ThreadAdd(this);
        ta.start();
        tl = new ThreadList(this);
        tl.start();
        tr = new ThreadRemove(this);
        tr.start();
        tu = new ThreadUpdate(this);
        tu.start();
    }

    public void setIns(InserimentoSpecifiche ins){
        this.ins = ins;
    }

    public void setLogin(Login l){
        this.l = l;
    }

    public void setRemoveMode(Remove rmv){
        mode = RMV;
        this.rmv = rmv;
    }

    public void setUpdateMode(Update upd){
        mode = UPD;
        this.upd = upd;
    }

    public void setListMode(CompList lst){
        mode = LST;
        this.lst = lst;
    }

    public void accessToDB(String username, String password) {
        tlog.login(username, password);
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

    public void updateComponent(int index, int qty) {
        tu.updateCompById(index, qty);
    }

    public void remove(int id){
        tr.removeCompById(id);
    }

    public void getListComponents(){
        tl.getListOf(null);
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
    public void addStatus(boolean status) {
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
                if (arr != null) upd.successList(arr);
                else upd.failureList();
                break;
            }
            case LST:{
                if (arr != null) lst.successList(arr);
                else lst.failureList();
                break;
            }
        }
    }

    @Override
    public void remove(boolean status) {
        modified = status;
        if (status) rmv.successRemove();
        else rmv.failureRemove();
    }

    @Override
    public void update(boolean status) {
        modified = status;
        if (status) upd.successUpdate();
        else upd.failureUpdate();
    }

    @Override
    public void login(boolean status) {
        loggedIn = status;
        if (status) l.successLogin();
        else l.failureLogin();
    }

    public void stopThreads(){
        tlog.stopThread();
        ta.stopThread();
        tl.stopThread();
        tr.stopThread();
        tu.stopThread();
    }
}
