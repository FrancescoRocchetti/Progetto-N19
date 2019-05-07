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

    //@Override
    public static boolean check() {
        return (checkMOBOCPU() && checkMOBORAM() && checkMOBOGPU() && checkMOBOSTORAGE() &&
                checkCPURAM() && checkCPUOS() && checkMOBOCASE() && checkCASESTORAGE());
    }
}
