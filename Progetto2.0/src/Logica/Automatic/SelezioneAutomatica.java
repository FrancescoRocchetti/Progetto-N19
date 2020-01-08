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

        //ss.cerca(budget,ss.cosaServe);
    }

    public ComponentiSelezionati getBuild(){
        return ss.getCs();
    }

}
