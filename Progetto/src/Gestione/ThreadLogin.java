package Gestione;

import InterfacingDB.CheckInternet;

/**
 * Thread usato per effettuare il login alla
 * pagina admin
 *
 * @author Fabio Riganti
 *
 */

public class ThreadLogin extends Thread{
    private ManagerDB mdb;
    private ObserverGO go;
    private String user;
    private String password;
    private boolean accept;

    public ThreadLogin(ObserverGO go) {
        super("ThreadLogin");
        mdb = new ManagerDB();
        this.accept = true;
        this.go = go;
    }

    public synchronized void login(String user, String password) {
        accept = false;
        this.user = user;
        this.password = password;
        notify();
    }

    @Override
    public void run() {
        try {
            while(true) {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    go.login(false);
                else {
                    boolean status = mdb.login(user, password);
                    go.login(status);
                }
                accept = true;
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName()+" terminato");
        }
    }

    private synchronized void checkAccept() throws InterruptedException {
        while(accept){
            wait();
        }
    }

    public void stopThread(){
        interrupt();
    }
}
