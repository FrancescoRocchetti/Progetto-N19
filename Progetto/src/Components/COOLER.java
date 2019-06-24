package Components;

import Resources.Resource;

public class COOLER extends AbstractComponent {

    private String[] SupportedSocketC;

    public COOLER(String[] input) {
        super(input);

        resource = new Resource.Builder()
                .withokCooler(true)
                .withSpportedSocketC(caratteristiche[2].split(";"))
                .withPower(0-Integer.parseInt(caratteristiche[3]))
                .build();

        SupportedSocketC = caratteristiche[2].split(";");
    }

    public String[] getSupportedSocketC() {
        return SupportedSocketC;
    }
/*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
