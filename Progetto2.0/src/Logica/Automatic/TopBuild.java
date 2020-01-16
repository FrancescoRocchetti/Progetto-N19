package Logica.Automatic;

import Logica.Componente;
import Logica.ComponentiSelezionati;

/**
 * classe per la selezione dei componenti (valuta solo il prezzo per fare la scelta)
 * @author Francesco Rocchetti
 */
public class TopBuild extends StrategiaSelezione{

    public TopBuild(int budget) {
        super(budget);
        this.cosaServe = new String[]{"CPU", "MOBO", "RAM", "PSU", "CASE", "STORAGE", "GPU", "COOLER"};
    }

    @Override
    public int compare(Componente o1, Componente o2) {
        int t1 = o1.getPrice();
        int t2 = o2.getPrice();

        return t2-t1;
    }
}
