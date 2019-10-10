package Minimo;

/**
 * piccola classe di test
 */

public class Main {
    public static void main(String[] args) {

        Gestione g = new Gestione();

        g.leggi("cpu");
        g.add(0);
        g.leggi("mobo");
        g.add(1);   //questa mobo non è compatibile e quindi non viene aggiunta
        System.out.println(g.printSelected());

        g.add(0);
        g.leggi("ram");
        g.add(0);

        System.out.println(g.printSelected());

    }
}
