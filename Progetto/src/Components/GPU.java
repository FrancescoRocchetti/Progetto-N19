package Components;

import Resources.Resource;

public class GPU extends AbstractComponent {

    public GPU(String[] input) {
        super(input);
        resource = new Resource.Builder()
                .withokGPU(true)
                .withPower(0-Integer.parseInt(caratteristiche[2]))
                .withNPci(-1)
                .build();
    }

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
