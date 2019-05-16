package Gestione;

import Components.AbstractComponent;
import Components.*;
import Constraints.AdaptabilityConstraint;
import Constraints.ConsistencyConstraint;
import InterfacingDB.PCParts;
import InterfacingDB.Reading;
import Resources.Resource;

import java.util.ArrayList;

public class TestFabio {
    public static void main(String[] args) {
        try {
            /**
            * in teoria mi dovrebbe restituire un array di stringhe
            * (una riga per componente e non una riga per caratteristica)
            * invece restituisce solo un componente diviso in più stringhe
            * Potrebbe funzionare anche così ma è molto più lento
            *
            * --------
            *
            * Scusa Rocca, ma la tua classe mi impediva di compilare, così
            * l'ho corretta.
            *
            * Auguroni,
            *
            * Fabbio.
            * */

            /**
             * simulazione di build:
             * -il sistema apre la connessione
             * -viene creata una SelectedComponents
             * -viene aggiunta una CPU a caso
             * -viene richiesta la lista delle MOBO
             * -vengono scartate le MOBO non compatibili
             * -viene aggiunta a SC una MOBO accettabile
             * -repeat
             */

            Reading r = new Reading();
            ArrayList<String[]> s = r.read(PCParts.CPU);

            SelectedComponents sc = new SelectedComponents();

            //carico una cpu
            sc.addCList(new CPU(s.get(0)));

            s = r.read(PCParts.MOBO);
            ArrayList<AbstractComponent> am = new ArrayList<>();
            for(String[] riga : s) {
                am.add(new MOBO(riga));
            }

            am = AdaptabilityConstraint.check(am, sc);

            sc.addCList(am.get(0));
            am = new ArrayList<>();
            s = r.read(PCParts.RAM);
            for(String[] riga : s) {
                am.add(new RAM(riga));
            }

            ArrayList<AbstractComponent> an = AdaptabilityConstraint.check(am, sc);
            sc.addCList(an.get(0));

            for(AbstractComponent ac : sc.getAR()){
                System.out.println(ac);
            }

            System.out.println(sc.getTotRes());
            System.out.println(ConsistencyConstraint.checkRes(sc));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
