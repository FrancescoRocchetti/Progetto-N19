package Logica.Automatic;

import Logica.Componente;
import Logica.ComponentiSelezionati;

/**
 * classe per le selezioni meno costose (valuta il rank delle componenti e non sceglie certi tipi di pezzi)
 * @author Francesco Rocchetti
 */
public class CheapBuild extends StrategiaSelezione {

    public CheapBuild(int budget) {
        super(budget);
        this.cosaServe = new String[]{"CPU", "MOBO", "RAM", "PSU", "CASE", "STORAGE"};
    }

    @Override
    public int compare(Componente o1, Componente o2) {
        double t1 = ((((double)o1.getRanking())/((double)o1.getPrice()))*100);
        double t2 = ((((double)o2.getRanking())/((double)o2.getPrice()))*100);

        return (int)(t2-t1);
    }
}
