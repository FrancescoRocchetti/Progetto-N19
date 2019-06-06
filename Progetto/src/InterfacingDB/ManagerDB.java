package InterfacingDB;

import Components.AbstractComponent;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerDB {
    private Login l;
    private Writer w;
    private Reader r;
    private CheckInternet c;

    public ManagerDB() {
        l = new Login();
        w = new Writer();
        r = new Reader();

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

    public int getQuantityByID(int id) {
        return r.getQuantityByID(id);
    }

    public AbstractComponent getCompByID(int id) {
            return r.getCompByID(id);
    }

    public boolean checkInternet() {
        return c.check();
    }
}
