package Interface.DeprecatedClasses;

import Components.AbstractComponent;

import javax.swing.*;
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
