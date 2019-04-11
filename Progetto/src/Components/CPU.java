package Components;

import Resources.Resource;

public class CPU extends AbstractComponent {

    public CPU(String[] input) { // input arriva da interfacingDB
        super(input);
        String[] caratteristiche = input[2].split("_");
        resource = new Resource.Builder()
                .withokCPU(true)
                .withSocket(caratteristiche[7])
                .withnBit(Integer.parseInt(caratteristiche[5]))
                .withPower(0-Integer.parseInt(caratteristiche[4]))
                .build();
    }

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
