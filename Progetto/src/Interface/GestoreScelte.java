package Interface;

import InterfacingDB.Reading;
import InterfacingDB.PCParts;
import Components.AbstractComponent;
import Gestione.SelectedComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestoreScelte extends Piattaforma {
    private ArrayList<String> str;
    private PCParts[] cmp;
    private JPanel[] pnl;
    private int row = 100;
    private int nr = 0;
    private SelectedComponents scp;

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
        } catch (SQLException e) {
            e.printStackTrace();
            //System.err.println("Errore nella lettura");
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

    private void obtainParts(PCParts[] components, JPanel[] panel) throws SQLException {
        int i = 0;
        ArrayList<AbstractComponent> arr;
        Reading dati = new Reading();
        JRadioButton comp;
        bg = new ButtonGroup();
        for(int z = 0; z < components.length; z++) {
            arr = dati.read(components[z]);
            for(AbstractComponent x : arr) {
                comp = new JRadioButton(x.getName() + " " + x.getType() + ", :" + x.getPrice() + "€");
                radioButtonListener(comp);
                bg.add(comp);
                panel[z].add(comp);
                nr++;
                if(row <= nr)
                    row++;
            }
            panel[z].setLayout(new GridLayout(row, 1));
        }
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
