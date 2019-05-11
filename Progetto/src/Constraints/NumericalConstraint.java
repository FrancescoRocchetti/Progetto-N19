package Constraints;

import Gestione.SelectedComponents;
import Resources.Resource;

import java.util.ArrayList;

public class NumericalConstraint implements AbstractConstraint {

    //questi vincoli creano solo dei warning
    private static boolean checkPOWER(SelectedComponents sc){
        int w=0;
        ArrayList<Resource> ac = sc.getRes();
        for(Resource r : ac){
            w+=r.getPower();
        }
        return (w>=0);
    }

    public static boolean check(SelectedComponents sc) {
        return (checkPOWER(sc));
    }
}
