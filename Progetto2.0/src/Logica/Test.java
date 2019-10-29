package Logica;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class Test {
    public static void main(String[] args) {

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forPackage("")));


        Set<Class<? extends Object>> allClasses =
                reflections.getSubTypesOf(Object.class);

        for(Object o: allClasses){
            System.out.println(o);
        }
    }
}
