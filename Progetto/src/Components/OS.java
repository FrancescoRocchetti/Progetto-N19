package Components;

import Resources.Resource;

public class OS extends AbstractComponent {

    public OS(String[] input) {
        super(input);
        resource = new Resource.Builder()
                .withnBit(Integer.parseInt(caratteristiche[1]))
                .build();
    }
    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
