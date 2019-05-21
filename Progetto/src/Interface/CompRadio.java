package Interface;

import javax.swing.*;
import Components.AbstractComponent;

public class CompRadio extends JRadioButton {
    private AbstractComponent abs;

    public CompRadio(String nome, AbstractComponent abs) {
        super(nome);
        this.abs = abs;
    }

    public AbstractComponent getAbs() {
        return abs;
    }
}
