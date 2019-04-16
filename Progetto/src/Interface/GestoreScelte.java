package Interface;

import javax.swing.*;
import java.util.ArrayList;

public class GestoreScelte {
    private ArrayList<String> str;
    public GestoreScelte() {
        str = new ArrayList<>();
    }

    public void addForDisplay(JRadioButton button) {
        str.add("- " + button.getText() + "\n");
    }

    public void displayOnPanel(JTextArea textArea) {
        String s = "";
        for(int i = 0; i < str.size(); i++) {
            s += str.get(i) + "\n";
        }
        textArea.setText(s);
    }
}
