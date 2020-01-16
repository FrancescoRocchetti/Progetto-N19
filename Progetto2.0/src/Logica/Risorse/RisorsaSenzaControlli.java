package Logica.Risorse;

/**
 * Permette di aggiungere dei commenti ad un componente senza dover essere
 * sottoposto a check
 * @author Francesco Rocchetti
 */
public class RisorsaSenzaControlli extends Risorsa{

    private String val;

    public RisorsaSenzaControlli(String nome, Object val) {
        super(nome, val);
        this.val = (String) val;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getValue() {
        return val;
    }

    @Override
    public String toString() {
        return "RisorsaSenzaControlli{" +
                "name='" + name + '\'' +
                ", value=" + val +
                '}';
    }
}
