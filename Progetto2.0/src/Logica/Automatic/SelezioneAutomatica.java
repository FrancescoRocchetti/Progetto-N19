package Logica.Automatic;

import Logica.ComponentiSelezionati;

public class SelezioneAutomatica {

    private StrategiaSelezione ss;
    private ComponentiSelezionati build;

    public SelezioneAutomatica(int budget) {
        if(budget<=750)
            ss = new CheapBuild();
        else if(budget<=2500)
            ss = new MidRangeBuild();
        else
            ss = new TopBuild();

        build = ss.selziona(budget);
    }

    public ComponentiSelezionati getBuild(){
        return build;
    }

}
