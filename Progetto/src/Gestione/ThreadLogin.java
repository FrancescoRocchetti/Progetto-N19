package Gestione;

import InterfacingDB.CheckInternet;
import InterfacingDB.ManagerDB;

public class ThreadLogin extends Thread{
    private ManagerDB mdb;
    private ObserverGO go;
    private String user;
    private String password;
    private boolean accept;

    public ThreadLogin(ObserverGO go) {
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
        while(true) {
            try {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    throw new NoInternetException("");
                boolean status = mdb.login(user,password);
                go.login(status);
                accept = true;
            } catch (Exception e) {
                go.login(false);
                accept = true;
            }
        }
    }

    private synchronized void checkAccept(){
        while(accept){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(10);
            }
        }
    }
}
