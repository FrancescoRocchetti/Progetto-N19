package Gestione;

import Automatic.ABuild;
import InterfacingDB.CheckInternet;

/**
 * Thread usato per effettuare l'autobuild della configurazione
 *
 * @author Fabio Riganti
 */
public class ThreadAutoBuild extends Thread {
    private ObserverGS gs;
    private boolean accept;
    private int budget;
    private ABuild a;

    public ThreadAutoBuild(ObserverGS gs) {
        super("ThreadAutoBuild");
        this.accept = true;
        this.gs = gs;
    }

    public synchronized void setAutoBuild(int budget) {
        this.budget = budget;
        accept = false;
        notify();
    }

    @Override
    public void run() {
        try {
            while (true) {
                checkAccept();
                accept = false;
                if (!CheckInternet.check())
                    gs.setAutoBuild(null);
                else {
                    if (budget <= 0)
                        a = new ABuild();
                    else
                        a = new ABuild(budget);
                    SelectedComponents scp = a.getSc();
                    gs.setAutoBuild(scp);
                }
                accept = true;
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " terminato");
        }
    }

    private synchronized void checkAccept() throws InterruptedException {
        while (accept) {
            wait();
        }
    }
}
