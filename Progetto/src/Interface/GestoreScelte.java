package Interface;

import InterfacingDB.DeprecatedClasses.Reading;
import InterfacingDB.PCParts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GestoreScelte extends Piattaforma {
    private ArrayList<String> str;
    private PCParts[] cmp;
    private JPanel[] pnl;
    private int row = 100;
    private int nr = 0;

    public GestoreScelte() {
        super();
        str = new ArrayList<>();
        cmp = new PCParts[]{PCParts.MOBO, PCParts.CPU, PCParts.RAM, PCParts.STORAGE, PCParts.GPU, PCParts.PSU, PCParts.COOLER, PCParts.OS, PCParts.CASE};
        pnl = new JPanel[]{panels[0], panels[1], panels[2], panels[3], panels[4], panels[5], panels[6], panels[7], panels[8]};
        newConfigListener();
        loginListener();
        exitListener();
        try {
            obtainParts(cmp, pnl);
            /*obtainParts(PCParts.MOBO, panels[0]);
            obtainParts(PCParts.CPU, panels[1]);
            obtainParts(PCParts.RAM, panels[2]);
            obtainParts(PCParts.STORAGE, panels[3]);
            obtainParts(PCParts.GPU, panels[4]);
            obtainParts(PCParts.PSU, panels[5]);
            obtainParts(PCParts.COOLER, panels[6]);
            obtainParts(PCParts.OS, panels[7]);
            obtainParts(PCParts.CASE, panels[8]);*/
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

    private void obtainParts(PCParts[] components, JPanel[] panel) throws IOException {
        String[] arr;
        Reading dati = new Reading();
        bg = new ButtonGroup();
        for(int i = 0; i < components.length; i++) {
            while (((arr = dati.read(components[i])) != null)) {
                JRadioButton comp = new JRadioButton(arr[2] + " " + arr[3] + " :" + arr[4]);
                radioButtonListener(comp);
                bg.add(comp);
                panel[i].add(comp);
                nr++;
                if(row <= nr)
                    row++;
            }
            panel[i].setLayout(new GridLayout(row, 1));
        }
        /*int i = 0;
        String[] arr;
        Reading dati = new Reading();
        bg = new ButtonGroup();
        while ((arr = dati.read(components)) != null) {
            JRadioButton comp = new JRadioButton(arr[2] + " " + arr[3] + " :" + arr[4]);
            radioButtonListener(comp);
            bg.add(comp);
            panel.add(comp);
            i++;
        }*/
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
