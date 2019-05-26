package Interface;

import InterfacingDB.Reading;
import InterfacingDB.PCParts;
import Components.AbstractComponent;
import Gestione.SelectedComponents;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestoreScelte extends Piattaforma {
    private ArrayList<String> str;
    private int row = 100;
    private int nr = 0;
    private SelectedComponents scp;
    private PCParts[] cmp = new PCParts[]{PCParts.MOBO, PCParts.CPU, PCParts.RAM, PCParts.STORAGE, PCParts.GPU, PCParts.PSU, PCParts.COOLER, PCParts.OS, PCParts.CASE};
    private JPanel[] pnl = new JPanel[]{panels[0], panels[1], panels[2], panels[3], panels[4], panels[5], panels[6], panels[7], panels[8]};

    public GestoreScelte() {
        super();
        scp = new SelectedComponents();
        str = new ArrayList<>();
        newConfigListener();
        loginListener();
        rechargeListener();
        exitListener();
        try {
            obtainParts(cmp, pnl);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore: impossibile connettersi al DB", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void addForDisplay(JRadioButton button) {
        str.add("- " + button.getText() + "\n");
    }

    private void displayOnPanel(JTextArea textArea) {
        StringBuilder s = new StringBuilder();
        for (String aStr : str) {
            s.append(aStr).append("\n");
        }
        textArea.setText(s.toString());
    }

    private void obtainParts(PCParts[] components, JPanel[] panel) throws SQLException {
        ArrayList<AbstractComponent> arr;
        Reading dati = new Reading();
        CompRadio comp;
        bg = new ButtonGroup();
        for(int z = 0; z < components.length; z++) {
            arr = dati.read(components[z]);
            for(AbstractComponent x : arr) {
                comp = new CompRadio(x.getName() + " " + x.getPrice()+"€",x);
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

    private void radioButtonListener(CompRadio comp) {
        comp.addActionListener(e -> {
            String currentChoice;
            String alreadyPresent;
            boolean giaPresente;
            currentChoice = "- " + comp.getText() + "\n";
            if(str.isEmpty())
                addToCart(comp);
            else {
                giaPresente = false;
                for (String aStr : str) {
                    alreadyPresent = aStr;
                    if (currentChoice.equals(alreadyPresent))
                        giaPresente = true;
                }
                if (!giaPresente)
                    addToCart(comp);
                else
                    JOptionPane.showMessageDialog(null, "Articolo già presente", "", JOptionPane.INFORMATION_MESSAGE);
            }
            // TODO: gestire la sovrascrittura dei componenti
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
            // TODO: controllare tot perchè non si azzera
            tot = 0;
            price.setText(String.valueOf(tot) + " €");
            items.setText("");
            str.clear();
        });
    }

    private void rechargeListener() {
        recharge.addActionListener(e -> {
            try {
                for(JPanel p : pnl)
                    p.removeAll();
                obtainParts(cmp, pnl);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "Errore: impossibile connettersi al DB.\nIl programma terminerà la sua esecuzione.", "Errore", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
                System.exit(10);
            }
        });
    }

    private void addToCart(CompRadio c) {
        addForDisplay(c);
        displayOnPanel(items);
        scp.addCList(c.getAbs());
        tot = scp.getTotPrice();
        price.setText(String.valueOf(tot) + " €");
    }

    private void exitListener() {
        exit.addActionListener(e -> System.exit(0));
    }
}
