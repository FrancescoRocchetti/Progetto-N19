package Interface;

import InterfacingDB.LoginDB;
import InterfacingDB.PCParts;
import InterfacingDB.Writing;

import java.sql.SQLException;

public class GestoreOperazioni {
    private boolean modified;
    private Piattaforma p;

    public GestoreOperazioni(Piattaforma p){
        modified = false;
        this.p = p;
        p.setEnabled(false);
        p.setDefaultCloseOperation(0);
    }

    public void setPlatform(){
        p.setEnabled(true);
        p.setDefaultCloseOperation(3);
        if(modified)
            p.refresh();
    }

    public boolean accessToDB(String username, String password) {
        LoginDB logInDB;
        try {
            logInDB = new LoginDB();
            if (logInDB.login(username, password)) {
                InserimentoSpecifiche ins = new InserimentoSpecifiche(this, username);
                return true;
            }
            return false;
        } catch (SQLException e1) {
            return false;
        }
    }

    public boolean insertComponent(PCParts componente, String descrizione, int quantita, int prezzo, int valutazione) {
        Writing writing = new Writing();
        try {
            if(checkDescription(componente, descrizione)) {
                writing.write(componente, descrizione, quantita, prezzo, valutazione);
                modified = true;
                return true;
            }
        } catch (SQLException e1) {
            return false;
        }
        return false;
    }

    private boolean checkDescription(PCParts componente, String descrizione) {
        return true;
    }
}
