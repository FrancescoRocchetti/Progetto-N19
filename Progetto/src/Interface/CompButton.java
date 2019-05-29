package Interface;

import javax.swing.*;
import Components.AbstractComponent;

public class CompButton extends JButton {
    private AbstractComponent abs;

    public CompButton(String nome, AbstractComponent abs) {
        super(nome);
        this.abs = abs;
    }

    public AbstractComponent getAbs() {
        return abs;
    }
}
