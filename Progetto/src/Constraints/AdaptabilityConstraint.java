package Constraints;

import Components.*;
import Gestione.SelectedComponents;
import InterfacingDB.PCParts;

import java.util.ArrayList;

public class AdaptabilityConstraint implements AbstractConstraint {

    //questi sono i vincoli applicati ai componenti che arrivano dal DB

    private static boolean checkMOBOCPU(MOBO m, CPU c){
        if(m==null || c==null
                || c.getResource().getTypeSocket() == m.getResource().getTypeSocket())
            return true;
        else
            return false;
    }

    private static boolean checkMOBORAM(MOBO m, RAM r){
        if(m==null || r==null
                || r.getResource().getTypeRAM() == m.getResource().getTypeRAM())
            return true;
        else
            return false;
    }

    private static boolean checkMOBOGPU(){
        return true;
    }

    private static boolean checkMOBOSTORAGE(){
        return true;
    }

    private static boolean checkCPURAM(CPU c, RAM r){
        if(c==null || r==null
                || r.getResource().getTypeRAM() == c.getResource().getTypeRAM())
            return true;
        else
            return false;
    }

    private static boolean checkCPUOS(CPU c, OS o){
        if(c==null || o==null
                || o.getResource().getnBit() <= c.getResource().getnBit())
            return true;
        else
            return false;
    }

    private static boolean checkMOBOCASE(MOBO m, Case c){
        if(m==null || c==null
                || m.getResource().getDimensionCase() == c.getResource().getDimensionCase())
            return true;
        else
            return false;
    }

    private static boolean checkCASESTORAGE(){
        return true;
    }

    //TODO: ?
    //TODO: aggiungere una quarta classe di check per il numero massimo di componenti

    //cpu-> (checkMOBOCPU)(checkCPURAM)(checkCPUOS)
    //mobo-> (checkMOBOCPU)(checkMOBORAM)(checkMOBOGPU)--4(checkMOBOSTORAGE)--4(checkMOBOCASE)
    //ram-> (checkMOBORAM)(checkCPURAM)
    //gpu-> (checkMOBOGPU)--4
    //storage-> (checkMOBOSTORAGE)--4
    //case-> (checkMOBOCASE)(checkCASESTORAGE)--4
    //os-> (checkCPUOS)


    //rimuove dall'arraylist in arrivo dal DB i componenti non compatibili
    public static ArrayList<AbstractComponent> check(ArrayList<AbstractComponent> al, SelectedComponents sc){

        for(AbstractComponent ac : al) {
            switch (ac.getType()) {
                case "cpu":
                    if (!(checkMOBOCPU((MOBO) sc.getType(PCParts.MOBO), (CPU) ac)
                            && checkCPURAM((CPU) ac,(RAM) sc.getType(PCParts.RAM))
                            && checkCPUOS((CPU) ac, (OS) sc.getType(PCParts.OS)))) {
                        al.remove(ac);
                    }
                    break;
                case "mobo":
                    if (!(checkMOBOCPU((MOBO) ac, (CPU) sc.getType(PCParts.CPU))
                            && checkMOBORAM((MOBO) ac, (RAM) sc.getType(PCParts.RAM))
                            && checkMOBOCASE((MOBO) ac, (Case) sc.getType(PCParts.CASE)))){
                        al.remove(ac);
                    }
                    break;
                case "ram":
                    if (!(checkMOBORAM((MOBO) sc.getType(PCParts.MOBO), (RAM) ac)
                            && checkCPURAM((CPU) sc.getType(PCParts.CPU), (RAM) ac))){
                        al.remove(ac);
                    }
                    break;
                case "case":
                    if (!(checkMOBOCASE((MOBO) sc.getType(PCParts.MOBO), (Case) ac))){
                        al.remove(ac);
                    }
                    break;
                case "os":
                    if (!(checkCPUOS((CPU) sc.getType(PCParts.CPU), (OS) ac))){
                        al.remove(ac);
                    }
                    break;
            }
        }
        return al;
    }

    /*
    public static boolean check() {
        //questo va sostituito con uno switch case
        return (checkMOBOCPU() && checkMOBORAM() && checkMOBOGPU() && checkMOBOSTORAGE() &&
                checkCPURAM() && checkCPUOS() && checkMOBOCASE() && checkCASESTORAGE());
    }
    */
}
