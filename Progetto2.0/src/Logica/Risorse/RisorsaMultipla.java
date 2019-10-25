package Logica.Risorse;

/**
 *tentativo per una risorsa del tipo "mobo che accetta sia ddr3 e ddr4"
 * anche se è solamente una coppia di RisorsaLetterale estende direttamente Risorsa
 * per evitare problemi dovuti al polimorfismo
 */

public class RisorsaMultipla extends Risorsa {

    private String value;

    public RisorsaMultipla(String name, Object value) {
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
        return "RisorsaMultipla{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
