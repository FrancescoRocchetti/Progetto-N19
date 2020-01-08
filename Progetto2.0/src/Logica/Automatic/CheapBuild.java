package Logica.Automatic;

import Logica.Componente;
import Logica.ComponentiSelezionati;

public class CheapBuild extends StrategiaSelezione {

    protected String[] cosaServe = new String[]{"CPU", "MOBO", "RAM", "PSU", "CASE", "STORAGE"};

    public CheapBuild(int budget) {
        super(budget);
    }

    @Override
    public int compare(Componente o1, Componente o2) {
        double t1 = ((((double)o1.getRanking())/((double)o1.getPrice()))*100);
        double t2 = ((((double)o2.getRanking())/((double)o2.getPrice()))*100);

        return (int)(t2-t1);
    }
}
