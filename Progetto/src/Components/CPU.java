package Components;

import Resources.Resource;

public class CPU extends AbstractComponent {

    private double clock;
    private int core;
    private int thread;

    public CPU(String[] input) { // input arriva da interfacingDB
        super(input);

        resource = new Resource.Builder()
                .withokCPU(true)
                .withokGPU(caratteristiche[6].equalsIgnoreCase("Y"))
                .withSocket(caratteristiche[7])
                .withnBit(Integer.parseInt(caratteristiche[5]))
                .withPower(0 - Integer.parseInt(caratteristiche[4]))
                .build();

        clock = Double.parseDouble(caratteristiche[1]);
        core = Integer.parseInt(caratteristiche[2]);
        thread = Integer.parseInt(caratteristiche[3]);
    }

    public double getClock() {
        return clock;
    }

    public int getCore() {
        return core;
    }

    public int getThread() {
        return thread;
    }

    //TODO: metodi delle risorse

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
