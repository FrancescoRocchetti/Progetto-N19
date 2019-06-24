package Components;

import Resources.Resource;

public class STORAGE extends AbstractComponent {

    public STORAGE(String[] input) {
        super(input);
        resource = new Resource.Builder()
                .withokStorage(true)
                .withNSATA(-1)
                .withNSlot(-1)
                .withPower(0-Integer.parseInt(caratteristiche[3]))
                .build();
    }

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
