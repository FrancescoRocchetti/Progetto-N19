package Constraints;

public class ConsistencyConstraint extends AbstractConstraint {

    private boolean checkCPU(){
        return true;
    }

    private boolean checkMOBO(){
        return true;
    }

    private boolean checkRAM(){
        return true;
    }

    private boolean checkPSU(){
        return true;
    }

    private boolean checkGRAPHIC(){
        return true;
    }

    private boolean checkSTORAGE(){
        return true;
    }

    @Override
    public boolean check() {
        return (checkCPU() && checkMOBO() && checkRAM()
                && checkGRAPHIC() && checkPSU() && checkSTORAGE());
    }
}
