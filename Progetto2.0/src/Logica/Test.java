package Logica;

import Logica.Vincoli.Vincolo;
import Minimo.TipiComponenti;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

/**
 * main tempotaneo per svolgere alcuni test
 */

public class Test {
    public static void main(String[] args) {

        Scrittura s = Scrittura.getInstance();

        ArrayList<String> ar = s.getType();

        for(String st: ar){
            System.out.println(st);
        }
    }
}
