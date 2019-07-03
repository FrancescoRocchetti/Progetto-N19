package Constraints;

import Components.*;
import Gestione.SelectedComponents;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Questa classe controlla i componenti in arrivo dal DB e scarta i pezzi
 * non compatibili con quelli già selezionati
 * (per esempio quelli con socket sbagliato)
 *
 * @author Francesco Rocchetti
 *
 */
public class AdaptabilityConstraint {

    //questi sono i vincoli applicati ai componenti che arrivano dal DB

    private static boolean checkMOBOCOOLER(MOBO m, COOLER c) {

        return m == null || c == null
                || Arrays.asList(c.getResource().getSupportedSocketC()).contains(m.getResource().getTypeSocket().split("-")[0]);

        //return true;
    }

    private static boolean checkPSU(SelectedComponents sc, PSU p){
        return (Math.abs(sc.getTotRes().getPower())<p.getResource().getPower());
    }

    private static boolean checkCPUCOOLER(CPU m, COOLER c) {

        return m == null || c == null
                || Arrays.asList(c.getResource().getSupportedSocketC()).contains(m.getResource().getTypeSocket().split("-")[0]);

        //return true;
    }

    private static boolean checkMOBOCPU(MOBO m, CPU c) {
        return m == null || c == null
                || c.getResource().getTypeSocket().equals(m.getResource().getTypeSocket());
    }

    private static boolean checkMOBORAM(MOBO m, RAM r) {
        return m == null || r == null
                || r.getResource().getTypeRAM().equals(m.getResource().getTypeRAM());
    }

    private static boolean checkCPURAM(CPU c, RAM r) {
        return c == null || r == null
                || r.getResource().getTypeRAM().equals(c.getResource().getTypeRAM());
    }

    private static boolean checkCPUOS(CPU c, OS o) {
        return c == null || o == null
                || o.getResource().getnBit() <= c.getResource().getnBit();
    }

    private static boolean checkMOBOCASE(MOBO m, CASE c) {

        return m == null || c == null
                || CaseSize.valueOf(m.getResource().getDimensionMOBO()).ordinal() >= CaseSize.valueOf(c.getResource().getDimensionCase()).ordinal();

    }


    /**
     * Rimuove dall'arraylist in arrivo dal DB i componenti non compatibili
     *
     * @param al componenti in arrivo dal db
     * @param sc componeneti già selezionati
     * @return ArrayList di componenti compatibili con quelli gia selezionati
     */
    public static ArrayList<AbstractComponent> check(ArrayList<AbstractComponent> al, SelectedComponents sc) {

        ArrayList<AbstractComponent> ao = new ArrayList<>();

        for (AbstractComponent ac : al) {
            if (ac.getResource().isOkCPU()) {
                if (checkMOBOCPU((MOBO) sc.getType(PCParts.MOBO), (CPU) ac)
                        && checkCPURAM((CPU) ac, (RAM) sc.getType(PCParts.RAM))
                        && checkCPUOS((CPU) ac, (OS) sc.getType(PCParts.OS))
                        && checkCPUCOOLER((CPU) ac, (COOLER) sc.getType(PCParts.COOLER))) {
                    ao.add(ac);
                }

            } else if (ac.getResource().isOkMOBO()) {
                if (checkMOBOCPU((MOBO) ac, (CPU) sc.getType(PCParts.CPU))
                        && checkMOBORAM((MOBO) ac, (RAM) sc.getType(PCParts.RAM))
                        && checkMOBOCASE((MOBO) ac, (CASE) sc.getType(PCParts.CASE))
                        && checkMOBOCOOLER((MOBO) ac, (COOLER) sc.getType(PCParts.COOLER))) {
                    ao.add(ac);
                }

            } else if (ac.getResource().getModulesRAM() != 0) {
                if (checkMOBORAM((MOBO) sc.getType(PCParts.MOBO), (RAM) ac)
                        && checkCPURAM((CPU) sc.getType(PCParts.CPU), (RAM) ac)) {
                    ao.add(ac);
                }

            } else if (ac.getResource().isOkCase()) {
                if (checkMOBOCASE((MOBO) sc.getType(PCParts.MOBO), (CASE) ac)) {
                    ao.add(ac);
                }
            } else if (ac.getResource().isOkCooler() && !ac.getResource().isOkCPU()) {
                if (checkCPUCOOLER((CPU) sc.getType(PCParts.CPU), (COOLER) ac)
                        && checkMOBOCOOLER((MOBO) sc.getType(PCParts.MOBO), (COOLER) ac)) {
                    ao.add(ac);
                }

            } else if (ac.getResource().getnBit() != 0) {
                if (checkCPUOS((CPU) sc.getType(PCParts.CPU), (OS) ac)) {
                    ao.add(ac);
                }
            } else if (ac.getResource().isOkPSU()) {
                if (checkPSU(sc, (PSU) ac)) {
                    ao.add(ac);
                }

            } else {
                ao.add(ac);
            }
        }

        return ao;
    }
}
