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
        g.add(1);
        g.add(1);   //questa ram non viene aggiunta perchè non è un numero primo
        g.add(1);   //questa ram non viene aggiunta perchè non ci sono abbastanza slot


        System.out.println(g.printSelected());
        System.out.println(g.printCost()+" $");
        System.out.println(g.printPower()+" W");

        System.out.println(g.checkFinale());

    }
}
