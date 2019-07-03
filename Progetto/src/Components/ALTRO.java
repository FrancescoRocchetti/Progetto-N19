package Components;

import Resources.Resource;

public class ALTRO extends AbstractComponent {

    public ALTRO(String[] input) {
        super(input);
        resource = new Resource.Builder().build();
    }
}
