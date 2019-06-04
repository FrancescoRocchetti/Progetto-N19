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
        try {
            return l.login(user, password);
        } catch (SQLException e) {
            l.forceClose();
            return false;
        }
    }

    public boolean write(PCParts part, String d, int q, int p, int r) {
        try {
            return w.write(part, d, q, p, r);
        } catch (SQLException e) {
            w.forceClose();
            return false;
        }
    }

    public boolean update(int cod, int quantità) {
        try {
            return w.update(cod, quantità);
        } catch (SQLException e) {
            w.forceClose();
            return false;
        }
    }

    public ArrayList<AbstractComponent> read(PCParts comp) {
        try {
            return r.read(comp);
        } catch (SQLException e) {
            r.forceClose();
            return null;
        }
    }

    public int getQuantityByID(int id) {
        try {
            return r.getQuantityByID(id);
        } catch (SQLException e) {
            r.forceClose();
            return -1;
        }
    }

    public AbstractComponent getCompByID(int id) {
        try {
            return r.getCompByID(id);
        } catch (SQLException e) {
            r.forceClose();
            return null;
        }
    }

    public boolean checkInternet() {
        return c.check();
    }
}
