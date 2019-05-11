package Constraints;

import Gestione.SelectedComponents;
import InterfacingDB.PCParts;

public class ConsistencyConstraint implements AbstractConstraint {

    //si può fare in un altro modo (con le risorse)
    //questi sono i vincoli richiamati quando si vuole ultimare una configurazione
    private static boolean checkCPU(SelectedComponents sc) {
        return !(sc.getType(PCParts.CPU) == null);
    }

    private static boolean checkMOBO(SelectedComponents sc) {
        return !(sc.getType(PCParts.MOBO) == null);
    }

    private static boolean checkRAM(SelectedComponents sc) {
        return !(sc.getType(PCParts.RAM) == null);
    }

    private static boolean checkPSU(SelectedComponents sc) {
        return (sc.getType(PCParts.PSU) != null && NumericalConstraint.check(sc));
    }

    private static boolean checkGRAPHIC(SelectedComponents sc) {
        return (sc.getType(PCParts.GPU) != null ||
                sc.getType(PCParts.CPU).getResource().isOkGPU());
    }

    private static boolean checkSTORAGE(SelectedComponents sc) {
        return !(sc.getType(PCParts.STORAGE) == null);
    }

    private static boolean checkCOOLER(SelectedComponents sc) {
        return (sc.getType(PCParts.COOLER)!=null
                || sc.getType(PCParts.CPU).getResource().isOkCooler());
    }

    public static boolean check(SelectedComponents sc) {
        return (checkCPU(sc) && checkMOBO(sc) && checkRAM(sc)
                && checkGRAPHIC(sc) && checkPSU(sc)
                && checkSTORAGE(sc) && checkCOOLER(sc));
    }
}
