package Components;

import Resources.Resource;

public class RAM extends AbstractComponent {

    public RAM(String[] input) {
        super(input);
        resource = new Resource.Builder()
                .withokRAM(true)
                .withTypeRAM(caratteristiche[2])
                .withAmountRAM(Integer.parseInt(caratteristiche[3]))
                .withPower(0-Integer.parseInt(caratteristiche[1]))
                .withModules(-Integer.parseInt(caratteristiche[5]))
                .build();
    }


    //TODO: metodi delle risorse

    /*@Override
    public int compareTo(Object o) {
        return 0;
    }*/
}
