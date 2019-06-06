package Interface;

import InterfacingDB.*;

import java.util.ArrayList;

import Components.AbstractComponent;

public class GestoreOperazioni {
    private boolean modified;
    private Piattaforma p;
    private ManagerDB mdb;

    public GestoreOperazioni(Piattaforma p) {
        modified = false;
        this.p = p;
        mdb = new ManagerDB();
        p.setEnabled(false);
        p.setDefaultCloseOperation(0);
    }

    public void unlockPlatform() {
        p.setEnabled(true);
        p.setDefaultCloseOperation(3);
        if (modified)
            p.refresh();
    }

    public boolean accessToDB(String username, String password) {
        if (mdb.login(username, password)) {
            InserimentoSpecifiche ins = new InserimentoSpecifiche(this, username);
            return true;
        }
        return false;
    }

    public boolean insertComponent(PCParts componente, String descrizione, int quantita, int prezzo, int valutazione) {
        if (checkDescription(componente, descrizione)) {
            mdb.write(componente, descrizione, quantita, prezzo, valutazione);
            modified = true;
            return true;
        }
        return false;
    }

    public boolean updateComponent(int index, int qty) {
        mdb.update(index, qty);
        return true;
    }

    public boolean remove(int id){
        return mdb.remove(id);
    }

    public int getQuantityByID(int id) {
        return mdb.getQuantityByID(id);

    }

    private boolean checkDescription(PCParts componente, String descrizione) {
        return true;
    }

    public ArrayList<AbstractComponent> getComponentsFromDB(PCParts parts) {
        return mdb.read(parts);

    }

    public String[][] getString(PCParts part) {
        ArrayList<AbstractComponent> comp = mdb.read(part);
        if (comp == null) {
            return null;
        }
        String data[][] = new String[comp.size()][];
        AbstractComponent abs;
        for (int i = 0; i < comp.size(); i++) {
            data[i] = new String[5];
            abs = comp.get(i);
            data[i][0] = String.valueOf(abs.getID());
            data[i][1] = abs.getType();
            data[i][2] = abs.getName();
            data[i][3] = String.valueOf(abs.getQuantity());
            data[i][4] = abs.getPrice() + " €";
        }
        return data;
    }
}
