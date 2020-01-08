package Logica.Automatic;

import Logica.Componente;
import Logica.ComponentiSelezionati;

public class TopBuild extends StrategiaSelezione{

    protected String[] cosaServe = new String[]{"CPU", "MOBO", "RAM", "PSU", "CASE", "STORAGE", "GPU", "COOLER"};

    @Override
    public int compare(Componente o1, Componente o2) {
        int t1 = o1.getPrice();
        int t2 = o2.getPrice();

        return t2-t1;
    }
}
