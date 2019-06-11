package Constraints;

import Components.*;
import Constraints.DeprecatedClass.AbstractConstraint;
import Gestione.SelectedComponents;
import InterfacingDB.PCParts;

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
                || m.getResource().getDimensionMOBO().equals(c.getResource().getDimensionCase());
        //pls fix
    }

    //rimuove dall'arraylist in arrivo dal DB i componenti non compatibili
    public static ArrayList<AbstractComponent> check(ArrayList<AbstractComponent> al, SelectedComponents sc) {

        ArrayList<AbstractComponent> ao = new ArrayList<>();

        for (AbstractComponent ac : al) {
            switch (ac.getType().toLowerCase()) {
                case "cpu":
                    if (checkMOBOCPU((MOBO) sc.getType(PCParts.MOBO), (CPU) ac)
                            && checkCPURAM((CPU) ac, (RAM) sc.getType(PCParts.RAM))
                            && checkCPUOS((CPU) ac, (OS) sc.getType(PCParts.OS))) {
                        ao.add(ac);
                    }
                    break;
                case "mobo":
                    if (checkMOBOCPU((MOBO) ac, (CPU) sc.getType(PCParts.CPU))
                            && checkMOBORAM((MOBO) ac, (RAM) sc.getType(PCParts.RAM))
                            && checkMOBOCASE((MOBO) ac, (CASE) sc.getType(PCParts.CASE))) {
                        ao.add(ac);
                    }
                    break;
                case "ram":
                    if (checkMOBORAM((MOBO) sc.getType(PCParts.MOBO), (RAM) ac)
                            && checkCPURAM((CPU) sc.getType(PCParts.CPU), (RAM) ac)) {
                        ao.add(ac);
                    }
                    break;
                case "case":
                    if (checkMOBOCASE((MOBO) sc.getType(PCParts.MOBO), (CASE) ac)) {
                        ao.add(ac);
                    }
                    break;
                case "os":
                    if (checkCPUOS((CPU) sc.getType(PCParts.CPU), (OS) ac)) {
                        ao.add(ac);
                    }
                    break;
                default:
                    ao.add(ac);
            }
        }

        return ao;
    }
}
