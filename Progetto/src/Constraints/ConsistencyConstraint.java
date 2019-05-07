package Constraints;

public class ConsistencyConstraint implements AbstractConstraint {

    private static boolean checkCPU(){
        return true;
    }

    private static boolean checkMOBO(){
        return true;
    }

    private static boolean checkRAM(){
        return true;
    }

    private static boolean checkPSU(){
        return true;
    }

    private static boolean checkGRAPHIC(){
        return true;
    }

    private static boolean checkSTORAGE(){
        return true;
    }

    //@Override
    public static boolean check() {
        return (checkCPU() && checkMOBO() && checkRAM()
                && checkGRAPHIC() && checkPSU() && checkSTORAGE());
    }
}
