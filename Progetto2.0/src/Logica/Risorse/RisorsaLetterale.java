package Logica.Risorse;

/**
 * per le risorse del tipo: nome socket, tecnologia memoria, ecc...
 * @author Francesco Rocchetti
 */
public class RisorsaLetterale extends Risorsa {

    private String value;

    public RisorsaLetterale(String name, Object value) {
        super(name,value);
        this.value = (String) value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "RisorsaLetterale{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
