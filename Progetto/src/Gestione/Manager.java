package Gestione;

import Components.AbstractComponent;
import Components.PCParts;
import Interface.Login;
import Interface.Piattaforma;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Manager che sta dietro tutta la logica del programma
 * e che fa partire i Thread e che istanzia GestoreScelte e
 * GestoreOperazioni
 *
 * @author Fabio Riganti
 *
 */

public class Manager {
    private GestoreScelte gs;
    private GestoreOperazioni go;
    private ThreadInventory t;
    private ThreadConfirm tc;
    private ThreadLogin tlog;
    private ThreadAdd ta;
    private ThreadList tl;
    private ThreadRemove tr;
    private ThreadUpdate tu;

    public Manager(){
        gs = new GestoreScelte(this);
        go = new GestoreOperazioni(this);
        t = new ThreadInventory(gs);
        t.start();
        tc = new ThreadConfirm(gs);
        tc.start();
        tlog = new ThreadLogin(go);
        tlog.start();
        ta = new ThreadAdd(go);
        ta.start();
        tl = new ThreadList(go);
        tl.start();
        tr = new ThreadRemove(go);
        tr.start();
        tu = new ThreadUpdate(go);
        tu.start();
        new Piattaforma(gs);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new Manager();
    }

    /**
     * Instanzia una JFrame Login
     *
     * @param p
     *
     */
    public void createLogin(Piattaforma p){
        new Login(p, go);
    }


    /**
     * Permette di accedere al DB
     *
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        tlog.login(username, password);
    }

    /**
     * Inserisce il componente nel DB
     *
     * @param componente
     * @param quantita
     * @param prezzo
     * @param valutazione
     *
     * @return true se si può inserire il componente nel DB, altrimenti falso
     */
    public void addComp(PCParts componente, String descrizione, int quantita, int prezzo, int valutazione) {
        ta.addComp(componente,descrizione,quantita,prezzo,valutazione);
    }

    /**
     * Aggiorna la quantità di uno o più componenti
     *
     * @param index
     * @param qty
     */
    public void updateQuantityById(int[] index, int qty) {
        tu.updateQuantityById(index, qty);
    }

    /**
     * Aggiorna il prezzo di uno o più componenti
     *
     * @param index
     * @param price
     */
    public void updatePriceById(int[] index, int price) {
        tu.updatePriceById(index, price);
    }

    /**
     * Rimuove uno o piò componenti
     *
     * @param id
     */
    public void removeCompById(int[] id) {
        tr.removeCompById(id);
    }

    /**
     * Permette di ottenere la lista dei componenti
     */
    public void getListOf(PCParts p) {
        tl.getListOf(p);
    }

    /**
     * Permette di ottenere la lista dei componenti
     *
     * @param comp
     */
    public void getInventoryOf(PCParts comp) {
        t.getInventoryOf(comp);
    }

    /**
     * Permette di confermare l'ordine
     *
     * @param codesOfComps
     */
    public void confirmOrder(int[] codesOfComps) {
        tc.confirmOrder(codesOfComps);
    }

    /**
     * Permette di ottenere una matrice di Object
     * a partire da una lista di AbstractComponent
     *
     * @param comp
     *
     * @return Object[][]
     */
    public Object[][] getObjectFromComps(ArrayList<AbstractComponent> comp) {
        Object[][] data = new Object[comp.size()][];
        AbstractComponent abs;
        for (int i = 0; i < comp.size(); i++) {
            data[i] = new Object[5];
            abs = comp.get(i);
            data[i][0] = abs.getID();
            data[i][1] = abs.getType();
            data[i][2] = abs.getName();
            data[i][3] = abs.getQuantity();
            data[i][4] = abs.getPrice() + " €";
        }
        return data;
    }
}
