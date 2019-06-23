package Gestione;

import Components.PCParts;
import InterfacingDB.CheckInternet;

/**
 * Thread usato per aggiungere componenti al DB
 *
 * @author Fabio Riganti
 */

public class ThreadAdd extends Thread{

    private ManagerDB mdb;
    private ObserverGO go;
    private boolean accept;
    private String[] str;

    public ThreadAdd(ObserverGO go) {
        super("ThreadAdd");
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
        try{
            while(true) {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    go.addStatus(false);
                else go.addStatus(mdb.write(PCParts.valueOf(str[0]), str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]),Integer.parseInt(str[4])));
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
