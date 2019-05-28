package Interface;

import InterfacingDB.LoginDB;
import InterfacingDB.PCParts;
import InterfacingDB.Reading;
import InterfacingDB.Writing;

import java.sql.SQLException;
import java.util.ArrayList;

import Components.AbstractComponent;

public class GestoreOperazioni {
    private boolean modified;
    private Piattaforma p;
    private Reading reading;
    private Writing writing;
    private LoginDB login;

    public GestoreOperazioni(Piattaforma p){
        modified = false;
        this.p = p;
        reading = new Reading();
        writing = new Writing();
        login = new LoginDB();
        p.setEnabled(false);
        p.setDefaultCloseOperation(0);
    }

    public void unlockPlatform(){
        p.setEnabled(true);
        p.setDefaultCloseOperation(3);
        if(modified)
            p.refresh();
    }

    public boolean accessToDB(String username, String password) {
        try {
            if (login.login(username, password)) {
                InserimentoSpecifiche ins = new InserimentoSpecifiche(this, username);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            login.forceClose();
            return false;
        }
    }

    public boolean insertComponent(PCParts componente, String descrizione, int quantita, int prezzo, int valutazione) {
        try {
            if(checkDescription(componente, descrizione)) {
                writing.write(componente, descrizione, quantita, prezzo, valutazione);
                modified = true;
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            writing.forceClose();
            return false;
        }
    }

    public boolean updateComponent(int index, int qty){
        try {
            writing.update(index, qty);
            return true;
        } catch (Exception e){
            System.err.println(e.getMessage());
            writing.forceClose();
            return false;
        }
    }

    public int getQuantityByID(int id){
        try {
            return reading.getQuantityByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private boolean checkDescription(PCParts componente, String descrizione) {
        return true;
    }

    public ArrayList<AbstractComponent> getComponentsFromDB(PCParts parts){
        try {
            return reading.read(parts);
        } catch (SQLException e) {
            return null;
        }
    }

    public String[][] getString(PCParts part){
        try{
            ArrayList<AbstractComponent> comp = reading.read(part);
            String data[][] = new String[comp.size()][];
            AbstractComponent abs;
            for(int i = 0; i < comp.size(); i++){
                data[i] = new String[5];
                abs = comp.get(i);
                data[i][0] = String.valueOf(abs.getID());
                data[i][1] = abs.getType();
                data[i][2] = abs.getName();
                data[i][3] = String.valueOf(abs.getQuantity());
                data[i][4] = abs.getPrice()+" €";
            }
            return data;
        } catch (Exception e){
            System.err.println(e.getMessage());
            reading.forceClose();
            return null;
        }
    }
}
