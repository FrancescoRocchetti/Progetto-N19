package Constraints;

public interface AbstractConstraint {

    //TODO: rimuovere questa interfaccia/classe astratta

    /**
     * questa classe è complentamente inutile in quanto:
     * 1) i vincoli vengono richiamati in momenti diversi del programma.
     * 2) non si riesce a sfruttare l' override in quanto tutti i metodi sono definiti
     * statici.
     */

    static boolean check() {
        return true;
    }

}
