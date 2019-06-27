package Components;

import Resources.Resource;

public class CASE extends AbstractComponent {

    private String DimensioneCase;
    private int NSlot;

    public CASE(String[] input) {
        super(input);

        resource = new Resource.Builder()
                .withokCase(true)
                .withDimensionCase(caratteristiche[1])
                .withNSlot350(Integer.parseInt(caratteristiche[3]))
                .withNSlot250(Integer.parseInt(caratteristiche[2]))
                //.withokPSU(caratteristiche[4].equalsIgnoreCase("Y"))
                .build();

        DimensioneCase = caratteristiche[1];
        NSlot = Integer.parseInt(caratteristiche[3]);
    }

    public String getDimensioneCase() {
        return DimensioneCase;
    }

    public int getNSlot() {
        return NSlot;
    }

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
