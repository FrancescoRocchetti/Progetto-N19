package Components;

import Resources.Resource;

public class CPU extends AbstractComponent {

    public CPU(String[] input) { // input arriva da interfacingDB
        super(input);

        resource = new Resource.Builder()
                .withokCPU(true)
                .withokGPU(caratteristiche[6].equals("Y"))
                .withSocket(caratteristiche[7])
                .withnBit(Integer.parseInt(caratteristiche[5]))
                .withPower(0-Integer.parseInt(caratteristiche[4]))
                .build();
    }

    //TODO: metodi delle risorse

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
