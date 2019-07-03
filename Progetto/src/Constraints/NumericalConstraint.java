package Constraints;

import Components.PCParts;
import Gestione.SelectedComponents;
import Resources.Resource;

import java.util.ArrayList;

/**
 * Check per il controllo della potenza disponibile
 * @author Francesco Rocchetti
 *
 */
public class NumericalConstraint {

    //vecchio metod
    private static boolean checkPOWER(SelectedComponents sc) {
        if (sc.getType(PCParts.PSU) != null) {
            int w = 0;
            ArrayList<Resource> ac = sc.getRes();
            for (Resource r : ac) {
                w += r.getPower();
            }
            return (w >= 0);
        } else
            return true;
    }

    /**
     * Controllo il wattaggio del sistema
     *
     * @param sc
     * @return
     */
    public static boolean check2(SelectedComponents sc) {
        //System.out.println(sc.getTotRes().isOkPSU());
        //System.out.println(sc.getTotRes().getPower());
        return (sc.getTotRes().isOkPSU() && (sc.getTotRes().getPower() >= 0));
    }

    public static boolean check(SelectedComponents sc) {
        return (checkPOWER(sc));
    }
}
