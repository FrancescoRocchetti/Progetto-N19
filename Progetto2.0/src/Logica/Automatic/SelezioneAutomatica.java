package Logica.Automatic;

import Logica.ComponentiSelezionati;

/**
 * build automatica
 */
public class SelezioneAutomatica {

    private ComponentiSelezionati build;

    public SelezioneAutomatica(int budget) {
        if(budget<=750){
            CheapBuild ss = new CheapBuild(budget);
            ss.cerca();
            build = ss.getCs();
        }
        else if(budget<=2500){
            MidRangeBuild ss = new MidRangeBuild(budget);
            ss.cerca();
            build = ss.getCs();
        }
        else{
            TopBuild ss = new TopBuild(budget);
            ss.cerca();
            build = ss.getCs();
        }
    }

    public ComponentiSelezionati getBuild(){
        return build;
    }

}
