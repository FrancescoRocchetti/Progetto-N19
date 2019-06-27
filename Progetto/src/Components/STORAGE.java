package Components;

import Resources.Resource;

public class STORAGE extends AbstractComponent {

    public STORAGE(String[] input) {
        super(input);
        resource = new Resource.Builder()
                .withokStorage(true)
                .withNSATA(-1)
                .withNSlot350(caratteristiche[1].equals("3.5")?-1:0)
                .withNSlot250(caratteristiche[1].equals("2.5")?-1:0)
                .withPower(0-Integer.parseInt(caratteristiche[3]))
                .build();
    }

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
