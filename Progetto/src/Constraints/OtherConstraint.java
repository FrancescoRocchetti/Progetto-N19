package Constraints;

import Components.AbstractComponent;
import Gestione.SelectedComponents;
import Resources.Resource;

public class OtherConstraint {

    /**
     * la sequenza di utilizzo delle classi vincolo è la seguente:
     * Adaptibilty è chiamata su vettori di componenti provenienti dal DB per scartare
     * componenti non "utilizzabili".
     * Other impedisce l'aggiunta di componenti in sovrannumero (es. 2 cpu)
     * Numerical esegue i conti sul Wattaggio dopo l'aggiunta di ogni componente
     * Consistency probisce ultimazione di una build se non possiede i componenti essenziali
     *
     * In  quanto Other e Numerical vengono richiamate nello stesso momento si potrebbe
     * valutare la possibilità di inglobare una classe nell'altra, ma tenendole separate
     * si ha la possibilità di definire una classe solo per i warning e una per bloccare
     * comportamenti errati
     */

    private static Resource initialCheck(SelectedComponents sc){
        //in questo metodo sommo le risorse già in possesso della build
        //(questo metodo potrebbe essere spostato in SelectedComponents)
        //TODO
        return new Resource.Builder().build();
    }

    private static Resource sumResource(AbstractComponent ac, SelectedComponents sc){
        //in questo metodo trovo la risorsa ipotetica con l'aggiunta del pezzo
        //TODO
        return new Resource.Builder().build();
    }

    private static boolean difCheck(){
        /*in questo metodo controllo se risorse che precedentemente avevano valore
        * strettamente positivo diventano <= 0 ???*/
        /*questa gestione dei vicoli è comunque dipendente dall'ordine di selezione dei
        * componenti per esempio se gli ssd venono scelti prima della MOBO il metodo
        * di vincolo è inefficace nel trovare l'errore; possbile soluzione può essere trasferire
        * il controllo alla fine della build e utilizzare questa classe solo per
        * i warning oppure si devono utilizzare utilizare i tag isOK delle risorse
         * (questa soluzione potrebbe comunque creare situazioni di blocco)*/
        //TODO
        return true;
    }

    //(checkMOBOGPU)(checkMOBOSTORAGE)(checkMOBOGPU)(checkMOBOSTORAGE)(checkCASESTORAGE)

    public static boolean check(AbstractComponent ac, SelectedComponents sc){
        return true;
    }
}
