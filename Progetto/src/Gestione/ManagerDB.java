package Gestione;

import Components.AbstractComponent;
import Components.PCParts;
import InterfacingDB.Login;
import InterfacingDB.Reader;
import InterfacingDB.Writer;

import java.util.ArrayList;

/**
 * Gestore delle classi Login, Writer e Reader
 *
 * @author Fabio Riganti
 */

public class ManagerDB {
    private Login l;
    private Writer w;
    private Reader r;

    public ManagerDB() {
        l = new Login();
        w = new Writer();
        r = new Reader();

    }

    /**
     * Permette di aggiungere un componente al carrello
     *
     * @param cod
     * @return true se l'acquisto è avvenuto correttamente,
     * altrimenti false
     */
    public boolean buy(int cod) {
        return w.buy(cod);
    }

    /**
     * Permette di effettuare il login come Admin alla piattaforma
     *
     * @param user
     * @param password
     * @return true se l'acquisto è avvenuto correttamente,
     * altrimenti false
     */
    public boolean login(String user, String password) {
        return l.login(user, password);
    }

    /**
     * Scrive i componenti nel DB
     *
     * @param part: tipo di componente
     * @param d:    descrizione componente
     * @param q:    quantità del componente
     * @param p:    prezzo del componente
     * @param r:    rank del componente
     * @return true se l'inserimento è andato a buon fine,
     * altrimenti false
     */
    public boolean write(PCParts part, String d, int q, int p, int r) {
        return w.write(part, d, q, p, r);
    }

    /**
     * Permette di effettuare l'aggiornamento della quantità
     *
     * @param cod
     * @param quantità
     * @return true se l'aggiornamento della quantità è andata a buon fine,
     * altrimenti false
     */
    public boolean updateQuantity(int cod, int quantità) {
        return w.updateQuantity(cod, quantità);

    }

    public boolean updatePrice(int cod, int price) {
        return w.updatePrice(cod, price);

    }

    /**
     * Permette di effettuare l'aggiornamento della quantità
     *
     * @param cod
     * @return true se l'aggiornamento della quantità è andata a buon fine,
     * altrimenti false
     */
    public boolean remove(int cod) {
        return w.remove(cod);
    }

    /**
     * Permette di effettuare l'aggiornamento della quantità
     *
     * @param comp
     * @return una lista di AbstractComponent se la lettura è andata a buon fine,
     * altrimenti null
     */
    public ArrayList<AbstractComponent> read(PCParts comp) {
        return r.read(comp);
    }
}
