package Logica.Automatic;

import Logica.Componente;
import Logica.ComponentiSelezionati;

public class TopBuild extends StrategiaSelezione{

    @Override
    public ComponentiSelezionati selziona(int prezzo) {
        cosaServe = new String[]{"CPU", "MOBO", "RAM", "PSU", "CASE", "STORAGE", "GPU", "COOLER"};
        return null;
    }

    @Override
    public int compare(Componente o1, Componente o2) {
        int t1 = o1.getPrice();
        int t2 = o2.getPrice();

        return t2-t1;
    }
}
