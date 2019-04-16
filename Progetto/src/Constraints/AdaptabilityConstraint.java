package Constraints;

public class AdaptabilityConstraint extends AbstractConstraint {

    private boolean checkMOBOCPU(){
        return true;
    }

    private boolean checkMOBORAM(){
        return true;
    }

    private boolean checkMOBOGPU(){
        return true;
    }

    private boolean checkMOBOSTORAGE(){
        return true;
    }

    private boolean checkCPURAM(){
        return true;
    }

    private boolean checkCPUOS(){
        return true;
    }

    private boolean checkMOBOCASE(){
        return true;
    }

    private boolean checkCASESSTORAGE(){
        return true;
    }

    @Override
    public boolean check() {
        return (checkMOBOCPU() && checkMOBORAM() && checkMOBOGPU() && checkMOBOSTORAGE() &&
                checkCPURAM() && checkCPUOS() && checkMOBOCASE() && checkCASESSTORAGE());
    }
}
