package Components;

import Resources.Resource;

public class PSU extends AbstractComponent {

    public PSU(String[] input) {
        super(input);
        resource = new Resource.Builder()
                .withPower(Integer.parseInt(caratteristiche[1]))
                .build();
    }

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
