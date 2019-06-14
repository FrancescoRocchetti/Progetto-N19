package Gestione;

import Components.AbstractComponent;

import java.util.ArrayList;

public interface ObserverGS {
    void update(ArrayList<AbstractComponent> arr);
    void orderSuccess();
    void orderFailure();
}
