package Constraints;

import Gestione.SelectedComponents;
import Resources.Resource;

public class Warning {
    private Warning wInstance = null;
    private String info= null;

    private Warning(){
    }

    public synchronized Warning getwInstance(){
        if(wInstance == null){
            wInstance = new Warning();
        }
        return wInstance;
    }

    public void check(SelectedComponents sc){
        info = "";
        Resource r = sc.getTotRes();

        if((r.isOkMOBO() && r.getnSATA() < 0) || (r.isOkCase() && r.getnSlot325() < 0)){
            info.concat("too many HDDs ");
        }

        if(r.isOkMOBO() && r.getModulesRAM() < 0){
            info.concat("too many RAM modules ");
        }

        if(r.isOkMOBO() && r.getnPci() < 0){
            info.concat("too many GPUs ");
        }


        if(info.equalsIgnoreCase("")){
            info.concat("NO WARNING");
        }
    }

    public String getInfo(){
        return info;
    }

}
