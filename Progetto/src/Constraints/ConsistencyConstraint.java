package Constraints;

import Gestione.SelectedComponents;
import Resources.Resource;
import Resources.SommatoreRes;

/**
 * Questi sono i vincoli richiamati quando si vuole ultimare una configurazione.
 * Viene controllato che i pezzi selezionanti possano comporre un PC intero
 *
 * @author Francesco Rocchetti
 *
 *
 */

public class ConsistencyConstraint {

    /**
     * Verifica la completezza di una build
     *
     * @param sc componenti selezionati
     * @return
     */
    public static boolean checkRes(SelectedComponents sc) {
        Resource r = SommatoreRes.sum(sc.getRes());

        return (r.isOkCPU() && r.isOkMOBO() && r.isOkRAM()
                && r.isOkGPU() && r.isOkPSU() && r.isOkCase()
                && r.isOkStorage() && (r.isOkCooler() || r.isOkCoolerI()) && NumericalConstraint.check2(sc));
    }


    //vecchio metodo
    /*private static boolean checkCPU(SelectedComponents sc) {
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
        return (sc.getType(PCParts.COOLER) != null
                || sc.getType(PCParts.CPU).getResource().isOkCooler());
    }

    public static boolean check(SelectedComponents sc) {
        return (checkCPU(sc) && checkMOBO(sc) && checkRAM(sc)
                && checkGRAPHIC(sc) && checkPSU(sc)
                && checkSTORAGE(sc) && checkCOOLER(sc));
    }*/
}
