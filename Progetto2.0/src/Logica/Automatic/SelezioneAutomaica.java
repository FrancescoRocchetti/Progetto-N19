package Logica.Automatic;

import Database.Facade;
import Logica.Componente;

import java.util.ArrayList;

public class SelezioneAutomaica {

    private StrategiaSelezione ss;
    private ArrayList<Componente> build;

    public SelezioneAutomaica(int budget) {
        Facade fdb = Facade.getInstance();
        if(budget<=750)
            ss = new CheapBuild();
        else if(budget>750 && budget<=2500)
            ss = new MidRangeBuild();
        else
            ss = new TopBuild();

        build = ss.selziona(budget,fdb);
    }

    private ArrayList<Componente> getBuild(){
        return build;
    }

}
