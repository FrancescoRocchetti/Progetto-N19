package Interface.WebInterface.Bean;

import Logica.Facade;

import java.io.Serializable;

/**
 * bean per gestire il login degli admin
 * @author Francesco Rocchetti
 */
public class LoginBean implements Serializable {

    private boolean logged = false;
    private String user;
    private Facade f;

    public LoginBean() {
        f = new Facade();
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(String user, String password) {
        if(user!=null && password!=null) {
            this.logged = f.login(user, password);
            System.err.println(user + " " + password + " " + logged);
            setUser(user);
        }
    }

    public String getUser() {
        return user;
    }

    private void setUser(String user) {
        this.user = user;
    }

    public void reset(){
        logged = false;
        user = "";
    }
}
