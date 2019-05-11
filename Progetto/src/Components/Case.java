package Components;

import Resources.Resource;

public class Case extends AbstractComponent {

    public Case(String[] input) {
        super(input);

        resource = new Resource.Builder()
                .withokCase(true)
                .withDimensionCase(caratteristiche[1]) //manca una enum per comparare le dimensioni
                .withNSlot(Integer.parseInt(caratteristiche[3]))
                .build();
    }
    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
