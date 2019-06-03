package Gestione;

import Components.*;

import java.lang.reflect.Constructor;

public class Validation {

    /**
     * Questo metodo verifica che si possa, data la stringa, generare un Componente
     * <p>
     * Il metodo richiede però che il tipo presente nella stringa sia uguale al nome di
     * una classe nel Package Components (CASE-SENSITIVE)
     *
     * @param inp
     * @return
     */

    public static boolean check(String[] inp) {
        if (inp.length != 6) {
            System.err.println("6");
            return false;
        }

        String nome = "Components.";
        nome = nome.concat(inp[1]);
        AbstractComponent c;

        try {
            Class cl = Class.forName(nome);
            Constructor con = cl.getDeclaredConstructor(String[].class);
            c = (AbstractComponent) con.newInstance((Object) inp);
        } catch (Exception e) {
            System.err.println("wrong");
            return false;
        }

        return true;
    }
}
