package Logica;

import Database.Facade;
import Logica.Risorse.Risorsa;
import Logica.Vincoli.Vincolo;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * classe che costruisce una componente date le informazioni in ingresso dal db
 * @author Francesco Rocchetti
 */
public class BComponente {

    private Facade fdb;

    public BComponente(){
        fdb =  Facade.getInstance();
    }

    /**
     * restituisce un Arraylist di componenti con risorse e vincoli
     * @param type
     * @return
     */
    public ArrayList<Componente> compByType(String type){
        ArrayList<Componente> tempC = new ArrayList<>();
        ArrayList<ArrayList<String>> tempS = fdb.readComp(type);

        for(ArrayList<String> s: tempS){
            tempC.add(build((String[]) s.toArray(new String[0])));
        }

        return tempC;
    }

    /**
     * crea un singolo componente
     * @param testo
     * @return
     */
    private Componente build(String[] testo){
        Componente temp = new Componente(Integer.parseInt(testo[0]),testo[1],Integer.parseInt(testo[2]),Integer.parseInt(testo[3]),Integer.parseInt(testo[4]),testo[5],testo[6]);
        addVincolo(temp);
        addRisorsa(temp);
        return temp;
    }

    /**
     * aggiunge le risorse ad un singolo componente
     * @param c
     */
    private void addRisorsa(Componente c){
        ArrayList<ArrayList<String>> list = fdb.readCaratteristiche(c.getId());

        for(ArrayList<String> s : list){

            String nome = "Logica.Risorse.";
            nome = nome.concat(((String[]) s.toArray(new String[0]))[1]); //s[1]
            Risorsa rs;

            try {
                Class[] cArg = new Class[2];
                cArg[0] = String.class;
                cArg[1] = Object.class;

                Class cl = Class.forName(nome);
                Constructor con = cl.getDeclaredConstructor(cArg);
                rs = (Risorsa) con.newInstance(((String[]) s.toArray(new String[0]))[2],
                        ((String[]) s.toArray(new String[0]))[3]);  //s[2],s[3]

                c.addRisorsa(rs);
            } catch (Exception e) {
                System.err.println(e);
            }

        }
    }

    /**
     * aggiunge i vincoli ad un singolo componente
     * @param c
     */
    private void addVincolo(Componente c){
        for(String s : c.getVincoli().split(",")){

            String nome = "Logica.Vincoli.";
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
