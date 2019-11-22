package Logica;

import Logica.Risorse.Risorsa;
import Logica.Vincoli.Vincolo;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * questa è la classe che gestisce(aiuta) l'inserimento di nuovi componenti nel DB
 *
 * TODO: magari è necessario un sistema di cache per certe informazioni
 */

public class Scrittura {

    private Database.Facade fdb;
    private Reflections reflections;
    private static Scrittura ourInstance = new Scrittura();

    private Scrittura() {
        fdb = Database.Facade.getInstance();
        reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forPackage("")));
    }

    public static Scrittura getInstance() {
        return ourInstance;
    }

    /**
     * @return tutti i vincoli applicabili ad un componente
     */
    public ArrayList<String> getVincoli(){
        Set<Class<? extends Vincolo>> modules =
                reflections.getSubTypesOf(Logica.Vincoli.Vincolo.class);

        ArrayList<String> temp = new ArrayList<>();

        for(Object o: modules){
            temp.add(o.toString().substring(o.toString().lastIndexOf('.')+1));
        }

        return temp;
    }

    /**
     * @return tutti i tipi di risorse esistenti
     */
    public ArrayList<String> getRisorse(){
        Set<Class<? extends Risorsa>> modules =
                reflections.getSubTypesOf(Logica.Risorse.Risorsa.class);

        ArrayList<String> temp = new ArrayList<>();

        for(Object o: modules){
            temp.add(o.toString().substring(o.toString().lastIndexOf('.')+1));
        }

        return temp;
    }

    public boolean addComp(String nome, int prezzo, int n, int rating, String vincolo, String tipo){
        fdb.addComponent(nome,prezzo,n,rating,vincolo,tipo);
        ArrayList<ArrayList<String>> temp= fdb.readCompSpecifico(nome);
        int id = Integer.parseInt(temp.get(temp.size()-1).get(temp.get(0).size()-1)); //TODO scrivere sta cosa in modo leggibile
        fdb.addCaratteristica(id, "RisorsaSenzaControlli", "ok_"+tipo.toLowerCase(), "ok");
        return true;
    }

    //public boolean addComp

}
