package Logica.Risorse;

/**
 * classe astratta delle risorsa
 */

public abstract class Risorsa {

    protected String name;

    public Risorsa(String nome, Object val){
        this.name = nome;
    }

    public abstract String getName();   //restituisce il nome della risorsa
    public abstract Object getValue();  //restituisce il valore della risorsa
}
