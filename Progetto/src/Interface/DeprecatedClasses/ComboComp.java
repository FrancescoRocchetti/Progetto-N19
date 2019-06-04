package Interface.DeprecatedClasses;

import javax.swing.*;

import Components.AbstractComponent;

import java.util.ArrayList;

public class ComboComp extends JComboBox {
    private ArrayList<AbstractComponent> list;

    public ComboComp() {
        list = new ArrayList<>();
    }

    public void refresh() {
        addItem("");
    }
}
