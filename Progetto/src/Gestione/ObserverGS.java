package Gestione;

import Components.AbstractComponent;

import java.util.ArrayList;

public interface ObserverGS {
    void updateList(ArrayList<AbstractComponent> arr);
    void orderSuccess();
    void orderFailure();
}
