package Logica.Risorse;

/**
 * per risorse del tipo: banchi di ram, potenza consumata, ecc...
 */

public class RisorsaNumerica extends Risorsa {


    private int value;

    public RisorsaNumerica(String name, Object value) {
        super(name,value);
        this.value = Integer.parseInt((String)value);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "RisorsaNumerica{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
