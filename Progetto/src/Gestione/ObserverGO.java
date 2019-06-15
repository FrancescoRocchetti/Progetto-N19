package Gestione;

import Components.AbstractComponent;

import java.util.ArrayList;

public interface ObserverGO {
    void updateAddStatus(boolean status);
    void updateList(ArrayList<AbstractComponent> arr);
    void remove(boolean status);
}
