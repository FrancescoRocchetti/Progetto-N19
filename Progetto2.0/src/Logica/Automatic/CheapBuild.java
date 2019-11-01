package Logica.Automatic;

import Logica.Componente;
import Logica.ComponentiSelezionati;

public class CheapBuild extends StrategiaSelezione {

    @Override
    public ComponentiSelezionati selziona(int prezzo) {
        cosaServe = new String[]{"CPU", "MOBO", "RAM", "PSU", "CASE", "STORAGE"};
        return null;
    }

    @Override
    public int compare(Componente o1, Componente o2) {
        int t1 = (int)((((double)o1.getRanking())/((double)o1.getPrice()))*100);
        int t2 = (int)((((double)o2.getRanking())/((double)o2.getPrice()))*100);

        return t2-t1;
    }
}
