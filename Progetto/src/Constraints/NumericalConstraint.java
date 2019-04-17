package Constraints;

public class NumericalConstraint extends AbstractConstraint {

    private boolean checkPOWER(){
        return true;
    }

    @Override
    public boolean check() {
        return (checkPOWER());
    }
}
