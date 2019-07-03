package Gestione;

import Components.AbstractComponent;

import java.util.ArrayList;

/**
 * Interfaccia che permette a un qualunque oggetto
 * ObserverGO di osservare l'andamento di certe operazioni
 * eseguite con i Thread, in particolare ThreadInventory e
 * ThreadConfirm
 *
 * @author Fabio Riganti
 */

public interface ObserverGS {
    void updateList(ArrayList<AbstractComponent> arr);

    void setAutoBuild(SelectedComponents scp);

    void orderSuccess();

    void orderFailure();
}
