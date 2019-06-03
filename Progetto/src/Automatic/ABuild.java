package Automatic;

import Gestione.SelectedComponents;

public class ABuild {
    private SelectedComponents sc;
    private int maxCost;

    private final double cCpu = 0.2;
    private final double cGpu = 0.25;
    private final double cMobo = 0.15;
    private final double cRam = 0.075;
    private final double cPsu = 0.075;
    private final double cCooler = 0.05;
    private final double cCase = 0.1;
    private final double cStorage = 0.1;


    public ABuild(int maxCost) {
        this.maxCost = maxCost;
        sc = new SelectedComponents();
    }

    public boolean genBuild() {
        return false;
    }

    public SelectedComponents getSc() {
        return sc;
    }
}
