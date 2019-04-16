package Components;

import Resources.Resource;

public class MOBO extends AbstractComponent {

    public MOBO(String[] input) {
        super(input);
        resource = new Resource.Builder()
                .withNSATA(3)//manca numero di sata
                .withSocket(caratteristiche[1])
                .withModules(Integer.parseInt(caratteristiche[2]))
                .withTypeRAM(caratteristiche[3])
                .withPower(0)//manca potenza per la mobo
                .withNPci(Integer.parseInt(caratteristiche[5]))
                .withNPcie(Integer.parseInt(caratteristiche[4]))
                .withokMOBO(true)
                .build();
    }

    /*@Override
        public int compareTo(Object o) {
            return 0;
        }*/
    public MOBO() {
    }
}
