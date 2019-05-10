package Constraints;

public class AdaptabilityConstraint implements AbstractConstraint {

    private static boolean checkMOBOCPU(){
        return true;
    }

    private static boolean checkMOBORAM(){
        return true;
    }

    private static boolean checkMOBOGPU(){
        return true;
    }

    private static boolean checkMOBOSTORAGE(){
        return true;
    }

    private static boolean checkCPURAM(){
        return true;
    }

    private static boolean checkCPUOS(){
        return true;
    }

    private static boolean checkMOBOCASE(){
        return true;
    }

    private static boolean checkCASESTORAGE(){
        return true;
    }

    //TODO: ?
    //TODO: aggiungere una quarta classe di check per il numero massimo di componenti

    //cpu-> (checkMOBOCPU)(checkCPURAM)(checkCPUOS)
    //mobo-> (checkMOBOCPU)(checkMOBOGPU)(checkMOBOSTORAGE)(checkMOBOCASE)
    //ram-> (checkMOBORAM)(checkCPURAM)
    //gpu-> (checkMOBOGPU)--4
    //storage-> (checkMOBOSTORAGE)--4
    //case-> (checkMOBOCASE)(checkCASESTORAGE)--4
    //os-> (checkCPUOS)

    public static boolean check() {
        //questo va sostituito con uno switch case
        return (checkMOBOCPU() && checkMOBORAM() && checkMOBOGPU() && checkMOBOSTORAGE() &&
                checkCPURAM() && checkCPUOS() && checkMOBOCASE() && checkCASESTORAGE());
    }
}
