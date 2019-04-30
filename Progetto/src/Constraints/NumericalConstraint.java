package Constraints;

public class NumericalConstraint extends AbstractConstraint {

    private static boolean checkPOWER(){
        return true;
    }

    //@Override
    public static boolean check() {
        return (checkPOWER());
    }
}
