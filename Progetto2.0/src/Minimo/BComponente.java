package Minimo;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * tecnicamente questa classe dovrebbe fare da costruttore di componenti e poi
 * aggiungere le risorse
 */

public class BComponente {

    public Componente build(String[] testo){
        Componente temp = new Componente(Integer.parseInt(testo[0]),testo[1],Integer.parseInt(testo[2]),Integer.parseInt(testo[3]),Integer.parseInt(testo[4]),testo[5],testo[6]);
        addVincolo(temp);
        addRisorsa(temp);
        return temp;
    }

    private void addRisorsa(Componente c){
        Read r = new Read();
        ArrayList<String[]> list = r.readC(String.valueOf(c.getId()));



        for(String[] s : list){

            String nome = "Minimo.";
            nome = nome.concat(s[1]);
            Risorsa rs;

            try {
                Class[] cArg = new Class[2];
                cArg[0] = String.class;
                cArg[1] = Object.class;

                Class cl = Class.forName(nome);
                Constructor con = cl.getDeclaredConstructor(cArg);
                rs = (Risorsa) con.newInstance(s[2],s[3]);

                c.addRisorsa(rs);
            } catch (Exception e) {
                System.err.println(e);
            }

        }
    }

    private void addVincolo(Componente c){
        for(String s : c.getVincoli().split(",")){

            String nome = "Minimo.";
            nome = nome.concat(s);
            Vincolo v;

            try {
                Class cl = Class.forName(nome);
                Constructor con = cl.getDeclaredConstructor(String.class);
                v = (Vincolo) con.newInstance(c.getName());

                c.addVincolo(v);
            } catch (Exception e) {
                System.err.println(e);
            }

        }
    }

}
