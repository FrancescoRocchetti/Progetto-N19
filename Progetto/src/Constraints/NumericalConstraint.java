package Constraints;

import Components.AbstractComponent;
import Gestione.SelectedComponents;

import java.util.ArrayList;

public class NumericalConstraint implements AbstractConstraint {

    private static boolean checkPOWER(SelectedComponents sc){
        int w=0;
        ArrayList<AbstractComponent> ac = sc.getAR();
        for(AbstractComponent c : ac){
            w+=c.getResource().getPower();
        }
        return (w>=0);
    }

    public static boolean check(SelectedComponents sc) {
        return (checkPOWER(sc));
    }
}
