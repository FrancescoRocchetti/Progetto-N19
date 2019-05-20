package Interface;

import InterfacingDB.DeprecatedClasses.Reading;
import InterfacingDB.PCParts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GestoreScelte extends Piattaforma {
    protected ArrayList<String> str;
    public GestoreScelte() {
        super();
        str = new ArrayList<>();
        newConfigListener();
        loginListener();
        exitListener();
        try {
            obtainParts(PCParts.MOBO, panels[0]);
            obtainParts(PCParts.CPU, panels[1]);
            obtainParts(PCParts.RAM, panels[2]);
            obtainParts(PCParts.STORAGE, panels[3]);
            obtainParts(PCParts.GPU, panels[4]);
            obtainParts(PCParts.PSU, panels[5]);
            obtainParts(PCParts.COOLER, panels[6]);
            obtainParts(PCParts.OS, panels[7]);
            obtainParts(PCParts.CASE, panels[8]);
        } catch (IOException e) {
            System.err.println("Errore nella lettura");
        }
    }

    private void addForDisplay(JRadioButton button) {
        str.add("- " + button.getText() + "\n");
    }

    private void displayOnPanel(JTextArea textArea) {
        String s = "";
        for(int i = 0; i < str.size(); i++) {
            s += str.get(i) + "\n";
        }
        textArea.setText(s);
    }

    private void obtainParts(PCParts components, JPanel panel) throws IOException {
        int i = 0;
        String[] arr;
        Reading dati = new Reading();
        bg = new ButtonGroup();
        while ((arr = dati.read(components)) != null) {
            JRadioButton comp = new JRadioButton(arr[2] + " " + arr[3] + " :" + arr[4]);
            radioButtonListener(comp);
            bg.add(comp);
            panel.add(comp);
            i++;
        }
        panel.setLayout(new GridLayout(i, 1));
    }

    private void radioButtonListener(JRadioButton comp) {
        comp.addActionListener(e -> {
            addForDisplay(comp);
            displayOnPanel(items);
            tot += Double.parseDouble(comp.getText().split(" :")[1]);
            price.setText(String.valueOf(tot) + " €");
        });
    }

    private void loginListener() {
        logAdmin.addActionListener(e -> {
            Login l = new Login(this);
            setVisible(false);
            l.setLocationRelativeTo(null);
        });
    }

    private void newConfigListener() {
        newConfig.addActionListener(e -> {
            tot = 0;
            price.setText("0 €");
            items.setText("");
            str.clear();
        });
    }

    private void exitListener() {
        exit.addActionListener(e -> {
            System.exit(0);
        });
    }
}
