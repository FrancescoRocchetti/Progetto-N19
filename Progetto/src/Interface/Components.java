package Interface;

import InterfacingDB.PCParts;

import javax.swing.*;
import java.awt.*;

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

    public Components() {
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
        btnArray = new JButton[]{caseButton, cooler, cpu, gpu, mobo, os, psu, ram, storage};
        comp = new JComboBox();
        rmv = new JButton("Remove");
        for(JButton b : btnArray)
            btnPanel.add(b);
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

    public static void main(String[] args) {
        Components c = new Components();
    }
}
