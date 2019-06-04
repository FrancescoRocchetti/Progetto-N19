package Components;

import Resources.Resource;

public class ALTRO extends AbstractComponent {

    public ALTRO(String[] input) {
        super(input);
        resource = new Resource.Builder().build();
    }



    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/

    //componenti che non necessitano di vincoli (se compro 2 mouse non ne devo attaccare 2 per forza)
}
