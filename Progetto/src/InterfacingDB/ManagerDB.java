package InterfacingDB;

import Components.AbstractComponent;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerDB {
    private LoginDB l;
    private Writing w;
    private Reading r;

    public ManagerDB(){
        l = new LoginDB();
        w = new Writing();
        r = new Reading();
    }

    public boolean login(String user, String password){
        try {
            return l.login(user, password);
        } catch (SQLException e) {
            l.forceClose();
            return false;
        }
    }

    public boolean write(PCParts part, String d, int q, int p, int r){
        try {
            return w.write(part, d, q, p, r);
        } catch (SQLException e) {
            w.forceClose();
            return false;
        }
    }

    public boolean update(int cod, int quantità){
        try {
            return w.update(cod, quantità);
        } catch (SQLException e) {
            w.forceClose();
            return false;
        }
    }

    public ArrayList<AbstractComponent> read(PCParts comp){
        try {
            return r.read(comp);
        } catch (SQLException e){
            r.forceClose();
            return null;
        }
    }

    public int getQuantityByID(int id){
        try {
            return r.getQuantityByID(id);
        } catch (SQLException e){
            r.forceClose();
            return -1;
        }
    }
}
