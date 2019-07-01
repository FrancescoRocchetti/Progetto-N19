package Gestione;

import Components.AbstractComponent;

import java.util.ArrayList;

/**
 * Interfaccia che permette a un qualunque oggetto
 * ObserverGO di osservare l'andamento di certe operazioni
 * eseguite con i Thread, in particolare ThreadLogin, ThreadAdd,
 * ThreadRemove, ThreadUpdate e ThreadList
 *
 * @author Fabio Riganti
 */

public interface ObserverGO {
    void addStatus(boolean status);

    void updateList(ArrayList<AbstractComponent> arr);

    void remove(boolean status);

    void update(boolean status);

    void login(boolean status);
}
