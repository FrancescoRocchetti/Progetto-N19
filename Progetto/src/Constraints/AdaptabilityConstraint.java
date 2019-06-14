package Constraints;

import Components.*;
import Constraints.DeprecatedClass.AbstractConstraint;
import Gestione.SelectedComponents;
import Components.PCParts;

import java.util.ArrayList;

public class AdaptabilityConstraint implements AbstractConstraint {

    //questi sono i vincoli applicati ai componenti che arrivano dal DB

    private static boolean checkMOBOCPU(MOBO m, CPU c) {
        return m == null || c == null
                || c.getSocket().equals(m.getSocket());
    }

    private static boolean checkMOBORAM(MOBO m, RAM r) {
        return m == null || r == null
                || r.getTypeRAM().equals(m.getTypeRAM());
    }

    private static boolean checkCPURAM(CPU c, RAM r) {
        return true/*c == null || r == null
                || r.getTypeRAM().equals(c.getTypeRAM())  */;
    }

    private static boolean checkCPUOS(CPU c, OS o) {
        return c == null || o == null
                || o.getBit() <= c.getBit();
    }

    private static boolean checkMOBOCASE(MOBO m, CASE c) {

        return m == null || c == null
                || CaseSize.valueOf(m.getResource().getDimensionMOBO()).ordinal()>=CaseSize.valueOf(c.getResource().getDimensionCase()).ordinal();

    }

    //rimuove dall'arraylist in arrivo dal DB i componenti non compatibili
    public static ArrayList<AbstractComponent> check(ArrayList<AbstractComponent> al, SelectedComponents sc) {

        ArrayList<AbstractComponent> ao = new ArrayList<>();

        for (AbstractComponent ac : al) {
            if (ac.getResource().isOkCPU()) {
                if (checkMOBOCPU((MOBO) sc.getType(PCParts.MOBO), (CPU) ac)
                        && checkCPURAM((CPU) ac, (RAM) sc.getType(PCParts.RAM))
                        && checkCPUOS((CPU) ac, (OS) sc.getType(PCParts.OS))) {
                    ao.add(ac);
                }

            } else if (ac.getResource().isOkMOBO()) {
                if (checkMOBOCPU((MOBO) ac, (CPU) sc.getType(PCParts.CPU))
                        && checkMOBORAM((MOBO) ac, (RAM) sc.getType(PCParts.RAM))
                        && checkMOBOCASE((MOBO) ac, (CASE) sc.getType(PCParts.CASE))) {
                    ao.add(ac);
                }

            } else if (ac.getResource().getModulesRAM()!=0) {
                if (checkMOBORAM((MOBO) sc.getType(PCParts.MOBO), (RAM) ac)
                        && checkCPURAM((CPU) sc.getType(PCParts.CPU), (RAM) ac)) {
                    ao.add(ac);
                }

            } else if (ac.getResource().isOkCase()) {
                if (checkMOBOCASE((MOBO) sc.getType(PCParts.MOBO), (CASE) ac)) {
                    ao.add(ac);
                }

            } else if (ac.getResource().getnBit()!=0) {
                if (checkCPUOS((CPU) sc.getType(PCParts.CPU), (OS) ac)) {
                    ao.add(ac);
                }

            } else {
                ao.add(ac);
            }
        }

        return ao;
    }
}
