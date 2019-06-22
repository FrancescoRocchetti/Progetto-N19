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
 *
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

    public boolean buy(int cod){
        return w.buy(cod);
    }

    public boolean login(String user, String password) {
        return l.login(user, password);
    }

    public boolean write(PCParts part, String d, int q, int p, int r) {
        return w.write(part, d, q, p, r);
    }

    public boolean update(int cod, int quantità) {
        return w.update(cod, quantità);

    }

    public boolean remove(int cod){
        return w.remove(cod);
    }

    public ArrayList<AbstractComponent> read(PCParts comp) {
        return r.read(comp);
    }
}
