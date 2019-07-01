package Gestione;

import Components.AbstractComponent;
import Components.PCParts;
import Interface.*;

import java.util.ArrayList;

/**
 * Gestione.Manager usato per gestire le operazioni effettuate con
 * InserimentoSpecifiche, Update, Remove e CompList
 *
 * @author Fabio Riganti
 */

public class GestoreOperazioni implements ObserverGO {
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
    private Manager m;
    private String mode; //RMV se si usa Remove, UPD se si usa Update e LST se si usa CompList

    public GestoreOperazioni(Manager m) {
        this.m = m;
        modified = false;
        loggedIn = false;
        descrizione = null;
    }

    /**
     * Imposta con quale InserimentoSpecifiche GestoreOperazioni
     * deve comunicare
     *
     * @param ins
     */
    public void setIns(InserimentoSpecifiche ins) {
        this.ins = ins;
    }

    /**
     * Imposta con quale Login GestoreOperazioni
     * deve comunicare
     *
     * @param l
     */
    public void setLogin(Login l) {
        this.l = l;
    }

    /**
     * Imposta la modalità remove e con quale Remove
     * GestoreOperazioni deve comunicare
     *
     * @param rmv
     */
    public void setRemoveMode(Remove rmv) {
        mode = RMV;
        this.rmv = rmv;
    }

    /**
     * Imposta la modalità updateQuantity e con quale Update
     * GestoreOperazioni deve comunicare
     *
     * @param upd
     */
    public void setUpdateMode(Update upd) {
        mode = UPD;
        this.upd = upd;
    }

    /**
     * Imposta la modalità list e con quale List
     * GestoreOperazioni deve comunicare
     *
     * @param lst
     */
    public void setListMode(CompList lst) {
        mode = LST;
        this.lst = lst;
    }

    /**
     * Permette di accedere al DB
     *
     * @param username
     * @param password
     */
    public void accessToDB(String username, String password) {
        m.login(username, password);
    }

    /**
     * Inserisce il componente nel DB
     *
     * @param componente
     * @param quantita
     * @param prezzo
     * @param valutazione
     * @return true se si può inserire il componente nel DB, altrimenti falso
     */
    public boolean insertComponent(PCParts componente, int quantita, int prezzo, int valutazione) {
        String[] str = {
                "1",
                componente.name().toUpperCase(),
                descrizione,
                String.valueOf(quantita),
                String.valueOf(prezzo),
                String.valueOf(valutazione)};
        if (descrizione != null && checkValidation(str)) {
            m.addComp(componente, descrizione, quantita, prezzo, valutazione);
            return true;
        }
        return false;
    }

    /**
     * Imposta la modalità list e con quale List
     * GestoreOperazioni deve comunicare
     *
     * @return true se si può inserire il componente nel DB, altrimenti falso
     */
    public boolean canAdd() {
        return descrizione != null;
    }

    /**
     * Aggiorna la quantità di uno o più componenti
     *
     * @param index
     * @param qty
     */
    public void updateQuantity(int[] index, int qty) {
        m.updateQuantityById(index, qty);
    }

    /**
     * Aggiorna il prezzo di uno o più componenti
     *
     * @param index
     * @param price
     */
    public void updatePrice(int[] index, int price) {
        m.updatePriceById(index, price);
    }

    /**
     * Rimuove uno o piò componenti
     *
     * @param id
     */
    public void remove(int[] id) {
        m.removeCompById(id);
    }

    /**
     * Permette di ottenere la lista dei componenti
     */
    public void getListComponents() {
        m.getListOf(null);
    }

    /**
     * Permette di controllare la validità della descrizione
     *
     * @param str
     */
    public boolean checkValidation(String[] str) {
        return Validation.check(str);
    }

    /**
     * Permette di impostare la descrizione
     *
     * @param descrizione
     */
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
        switch (mode) {
            case RMV: {
                if (arr != null) rmv.successList(arr);
                else rmv.failureList();
                break;
            }
            case UPD: {
                if (arr != null) upd.successList(arr);
                else upd.failureList();
                break;
            }
            case LST: {
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
}
