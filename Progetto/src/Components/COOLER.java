package Components;

import Resources.Resource;

public class COOLER extends AbstractComponent {

    public COOLER(String[] input) {
        super(input);

        resource = new Resource.Builder()
                .withokCooler(true)
                .build();
    }

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
