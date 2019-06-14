package Gestione;

import Components.PCParts;
import InterfacingDB.CheckInternet;
import InterfacingDB.ManagerDB;

public class ThreadAdd extends Thread{

    private ManagerDB mdb;
    private ObserverGO go;
    private boolean accept;
    private String[] str;

    public ThreadAdd(ObserverGO go) {
        mdb = new ManagerDB();
        accept = true;
        this.go = go;
    }

    public synchronized void addComp(PCParts componente, String descrizione, int quantita, int prezzo, int valutazione) {
        accept = false;
        String[] s = {
                componente.name().toUpperCase(),
                descrizione,
                String.valueOf(quantita),
                String.valueOf(prezzo),
                String.valueOf(valutazione)};
        str = s;
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
                go.updateAddStatus(mdb.write(PCParts.valueOf(str[0]), str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]),Integer.parseInt(str[4])));
                accept = true;
            } catch (Exception e) {
                go.updateAddStatus(false);
                accept = true;
            }
        }
    }

    private synchronized void checkAccept(){
        while(accept){
            try {
                wait();
            } catch (InterruptedException e) {
                go.updateAddStatus(false);
            }
        }
    }

}
