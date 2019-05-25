package Constraints;

import Gestione.SelectedComponents;
import InterfacingDB.PCParts;
import Resources.Resource;

import java.util.ArrayList;

public class NumericalConstraint implements AbstractConstraint {

    //QUESTA PARTE è DIVENTATA INUTILE DA QUANDO ESISTONO LE RISORSE
    private static boolean checkPOWER(SelectedComponents sc){
        if(sc.getType(PCParts.PSU) != null) {
            int w = 0;
            ArrayList<Resource> ac = sc.getRes();
            for (Resource r : ac) {
                w += r.getPower();
            }
            return (w >= 0);
        }
        else
            return true;
    }

    public static boolean check(SelectedComponents sc) {
        return (checkPOWER(sc));
    }
}
