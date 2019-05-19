package Interface;

import InterfacingDB.PCParts;
import InterfacingDB.Reading;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Components extends JFrame {
    private Container c;
    private JPanel bckg;
    private JPanel btnPanel;
    private JPanel choosePanel;
    private JButton caseButton;
    private JButton cooler;
    private JButton cpu;
    private JButton gpu;
    private JButton mobo;
    private JButton os;
    private JButton psu;
    private JButton ram;
    private JButton storage;
    private JButton[] btnArray;
    private JComboBox comp;
    private JButton rmv;
    private ArrayList<String[]> componenti;
    private String s = "";
    private boolean found;

    public Components() throws SQLException {
        super("Remove component");
        c = getContentPane();
        bckg = new JPanel(new BorderLayout());
        btnPanel = new JPanel(new GridLayout(3,3));
        choosePanel = new JPanel(new BorderLayout());
        caseButton = new JButton("Case");
        cooler = new JButton("Cooler");
        cpu = new JButton("CPU");
        gpu = new JButton("GPU");
        mobo = new JButton("MOBO");
        os = new JButton("OS");
        psu = new JButton("PSU");
        ram = new JButton("RAM");
        storage = new JButton("Storage");
        btnArray = new JButton[]{caseButton, cooler, cpu, gpu, mobo, psu, ram, storage, os};
        comp = new JComboBox();
        rmv = new JButton("Remove");
        componenti = new ArrayList<>();
        Reading reading = new Reading();
        componenti = reading.read(null);

        for(JButton b : btnArray) {
            btnPanel.add(b);
            b.addActionListener(e -> {
                comp.removeAllItems();
                found = false;
                for(String[] x : componenti) {
                    s += x[0] + " " + x[1] + " " + x[2] + " " + x[3] + " " + x[4] + "\n";
                    if(x[1].equals(b.getText().toUpperCase())) {
                        found = true;
                        comp.addItem(s);
                    }
                    s = "";
                }
                if(!found)
                    JOptionPane.showMessageDialog(null, "No items for " + b.getText().toUpperCase(), "No items found", JOptionPane.INFORMATION_MESSAGE);
            });
        }

        choosePanel.add(comp, BorderLayout.CENTER);
        choosePanel.add(rmv, BorderLayout.SOUTH);

        bckg.add(btnPanel, BorderLayout.CENTER);
        bckg.add(choosePanel, BorderLayout.SOUTH);
        c.add(bckg);

        setVisible(true);
        setResizable(false);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) throws SQLException {
        Components components = new Components();
    }
}
