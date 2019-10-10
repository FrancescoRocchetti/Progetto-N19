package Minimo;

/**
 * per le risorse del tipo: nome socket, tecnologia memoria, ecc...
 */

public class RisorsaLetterale extends Risorsa{

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

    /*@Override
    public boolean check(Risorsa r) {
        return  ((RisorsaLetterale) r).getValue().equalsIgnoreCase(name);
    }

    @Override
    public RisorsaLetterale sum(Risorsa r) {
        if(check(r))
            return (RisorsaLetterale)r;
        else
            return null;
    }*/

    @Override
    public String toString() {
        return "RisorsaLetterale{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
