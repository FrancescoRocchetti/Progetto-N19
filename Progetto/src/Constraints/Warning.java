package Constraints;

import Gestione.SelectedComponents;
import Resources.Resource;

public class Warning {
    private Warning wInstance = null;
    private String info= null;
    private boolean ok;

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
        ok = true;
        Resource r = sc.getTotRes();

        if((r.isOkMOBO() && r.getnSATA() < 0) || (r.isOkCase() && r.getnSlot325() < 0)){
            info.concat("too many HDDs ");
            ok = false;
        }

        if(r.isOkMOBO() && r.getModulesRAM() < 0){
            info.concat("too many RAM modules ");
            ok = false;
        }

        if(r.isOkMOBO() && r.getnPci() < 0){
            info.concat("too many GPUs ");
            ok = false;
        }

        if(r.isOkPSU() && r.getPower() < 0){
            info.concat("not enough power ");
            ok = false;
        }


        if(info.equalsIgnoreCase("")){
            info.concat("NO WARNING");
        }
    }

    public String getInfo(){
        return info;
    }

}
