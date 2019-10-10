package Minimo;

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

    /*@Override
    public boolean check(Risorsa r) {
        RisorsaNumerica rn = (RisorsaNumerica) r;
        if((rn.getValue()<0 && this.value<0)||(rn.getValue()+this.value >=0))
            return true;
        else
            return false;
    }

    @Override
    public RisorsaNumerica sum(Risorsa r) {
        if (check(r))
            return new RisorsaNumerica(r.getName(),value+((RisorsaNumerica) r).getValue());
        else
            return null;
    }*/

    @Override
    public String toString() {
        return "RisorsaNumerica{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
