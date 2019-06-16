package Gestione;

import Components.AbstractComponent;

import java.util.ArrayList;

public interface ObserverGO {
    void addStatus(boolean status);
    void updateList(ArrayList<AbstractComponent> arr);
    void remove(boolean status);
    void update(boolean status);
    void login(boolean status);
}
