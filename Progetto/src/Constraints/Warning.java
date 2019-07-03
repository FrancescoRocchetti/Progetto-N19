package Constraints;

import Gestione.SelectedComponents;
import Resources.Resource;

import java.util.ArrayList;

/**
 * check speculare a OtherConstraint nel caso di ordine di selezione invertito
 * @author Francesco Rocchetti
 */
public class Warning {
    private static Warning wInstance = null;
    private ArrayList<String> info;
    private boolean ok;

    private Warning() {
        info = new ArrayList<>();
    }

    public static synchronized Warning getwInstance() {
        if (wInstance == null) {
            wInstance = new Warning();
        }
        return wInstance;
    }

    /**
     * Controllo della lista dei componenti selezionati per errori.
     * Questi errori sono causati da una selezione disordinata dei componenti.
     * Per esempio selezionare lo storage prima del case può portare
     * a non avere lo spazio materiale per inserirli tutti
     *
     * @param sc componenti selezionati
     * @return
     */
    public boolean check(SelectedComponents sc) {
        info.clear();
        ok = true;
        Resource r = sc.getTotRes();

        if ((r.isOkMOBO() && r.getnSATA() < 0) || (r.isOkCase() && r.getnSlot350() < 0)) {
            info.add("too many HDDs ");
            ok = false;
        }

        if ((r.isOkMOBO() && r.getnSATA() < 0) || (r.isOkCase() && r.getnSlot250() < 0)) {
            info.add("too many SSDs ");
            ok = false;
        }

        if (r.isOkMOBO() && r.getModulesRAM() < 0) {
            info.add("too many RAM modules ");
            ok = false;
        }

        if (r.isOkMOBO() && r.getnPci() < 0) {
            info.add("too many GPUs ");
            ok = false;
        }

        if (r.isOkPSU() && r.getPower() < 0) {
            info.add("not enough power ");
            ok = false;
        }


        if (info.size() == 0) {
            info.add("NO WARNING");
        }

        return ok;
    }

    public ArrayList<String> getInfo() {
        return info;
    }

}