package Constraints;

import Components.AbstractComponent;
import Gestione.SelectedComponents;
import Resources.Resource;
import Resources.SommatoreRes;

import java.util.ArrayList;

public class OtherConstraint {

    /**
     * la sequenza di utilizzo delle classi vincolo è la seguente:
     * Adaptibilty è chiamata su vettori di componenti provenienti dal DB per scartare
     * componenti non "utilizzabili".
     * ALTRO impedisce l'aggiunta di componenti in sovrannumero (es. 2 cpu)
     * Numerical esegue i conti sul Wattaggio dopo l'aggiunta di ogni componente
     * Consistency probisce ultimazione di una build se non possiede i componenti essenziali
     * <p>
     * In quanto ALTRO e Numerical vengono richiamate praticamente nello stesso momento
     * si potrebbe valutare la possibilità di inglobare una classe nell'altra, ma
     * tenendole separate si ha la possibilità di definire una classe solo per i
     * warning e una per bloccare comportamenti errati
     */

    private static Resource initialCheck(SelectedComponents sc) {
        //in questo metodo sommo le risorse già in possesso della build

        return SommatoreRes.sum(sc.getRes());
    }

    private static Resource sumResource(AbstractComponent ac, SelectedComponents sc) {
        //in questo metodo trovo la risorsa ipotetica con l'aggiunta del pezzo selezonato

        ArrayList<Resource> temp = sc.getRes();
        temp.add(ac.getResource());

        return SommatoreRes.sum(temp);
    }

    private static boolean difCheck(AbstractComponent ac, SelectedComponents sc) {
        /*in questo metodo controllo se risorse che precedentemente avevano valore
         * strettamente positivo diventano < 0 */
        /*questa gestione dei vicoli è comunque dipendente dall'ordine di selezione dei
         * componenti per esempio se gli ssd venono scelti prima della MOBO il metodo
         * di vincolo è inefficace nel trovare l'errore; possbile soluzione può essere trasferire
         * il controllo alla fine della build e utilizzare questa classe solo per
         * i warning */
        /*questo metodo inoltre impedisce l'inserimento di cpu(o mobo o psu o case)
         in sovrannumero*/

        Resource before = initialCheck(sc);
        Resource after = sumResource(ac, sc);
        Resource rAc = ac.getResource();
        boolean temp = true;

        if (before.isOkCPU() && rAc.isOkCPU()) {
            temp = false;
        }

        if (before.isOkMOBO() && rAc.isOkMOBO()) {
            temp = false;
        }

        if (before.isOkPSU() && rAc.isOkPSU()) {
            temp = false;
        }

        if (before.isOkCase() && rAc.isOkCase()) {
            temp = false;
        }

        if (before.isOkMOBO() && (after.getnSATA() < 0) && (before.getnSATA() >= 0)) {
            //(checkMOBOSTORAGE)
            temp = false;
        }

        if (before.isOkMOBO() && (after.getnPci() < 0) && (before.getnPci() >= 0)) {
            //(checkMOBOGPU)
            temp = false;
        }

        if (before.isOkCase() && (after.getnSlot325() < 0) && (before.getnSlot325() >= 0)) {
            //(checkCASESTORAGE)
            temp = false;
        }

        if (before.isOkMOBO() && (after.getModulesRAM() < 0) && (before.getModulesRAM() >= 0)) {
            //(nram)
            temp = false;
        }

        if (before.isOkMOBO() && (after.getnSATA() < 0) && (before.getnSATA() >= 0)) {
            //(nstorage)
            temp = false;
        }

        return temp;
    }

    public static boolean check(AbstractComponent ac, SelectedComponents sc) {
        //System.out.println(ac.getClass().getName());
        return difCheck(ac, sc);
    }
}
