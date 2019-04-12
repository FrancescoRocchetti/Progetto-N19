package Components;

import Resources.Resource;

public class Storage extends AbstractComponent {

    public Storage(String[] input) {
        super(input);
        resource = new Resource.Builder()
                .withokStorage(true)
                .withNSATA(-1)
                .build();
    }

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
