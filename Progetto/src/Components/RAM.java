package Components;

import Resources.Resource;

public class RAM extends AbstractComponent {

    public RAM(String[] input) {
        super(input);
        resource = new Resource.Builder()
                .withAmountRAM(Integer.parseInt(caratteristiche[2]))
                .withPower(0-Integer.parseInt("0"))//manca il wattaggio delle RAM
                .withModules(0)//manca il numero di moduli per confezione di RAM
                .build();
    }

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
