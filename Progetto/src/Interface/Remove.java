package Interface;

import Components.AbstractComponent;
import InterfacingDB.Reading;
import InterfacingDB.Writing;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Remove extends JFrame {
    private Container c;
    private JPanel bckg;
    private JPanel btnPanel;
    private JPanel choosePanel;
    private JPanel comboBoxPanel;
    private JButton caseButton;
    private JButton cooler;
    private JButton cpu;
    private JButton gpu;
    private JButton mobo;
    private JButton os;
    private JButton psu;
    private JButton ram;
    private JButton storage;
    private JButton other;
    private JButton[] btnArray;
    private JComboBox comp;
    private JComboBox qta;
    private JButton rmv;
    private ArrayList<AbstractComponent> componenti;
    private String s = "";
    private boolean found;
    private int qtaToRmv = 0;
    private GestoreOperazioni go;


    public Remove(InserimentoSpecifiche ins, GestoreOperazioni go) throws SQLException {
        super("Remove component");
        this.go = go;
        c = getContentPane();
        bckg = new JPanel(new BorderLayout());
        btnPanel = new JPanel(new GridLayout(2,5));
        choosePanel = new JPanel(new BorderLayout());
        comboBoxPanel = new JPanel(new GridLayout(3,1));
        caseButton = new JButton("Case");
        cooler = new JButton("Cooler");
        cpu = new JButton("CPU");
        gpu = new JButton("GPU");
        mobo = new JButton("MOBO");
        os = new JButton("OS");
        psu = new JButton("PSU");
        ram = new JButton("RAM");
        storage = new JButton("Storage");
        other = new JButton("Altro");
        btnArray = new JButton[]{caseButton, cooler, cpu, gpu, mobo, psu, ram, storage, os, other};
        comp = new JComboBox();
        comp.addItem("No item selected...");
        qta = new JComboBox();
        rmv = new JButton("Remove");
        rmv.setEnabled(false);

        addItemToRmv(comp, componenti);

        rmv.addActionListener(e -> {
            String item;
            String[] cod;
            int rmCod;
            int qtaRmv;
            item = (String) comp.getSelectedItem();
            cod = item.split(" ");
            rmCod = Integer.parseInt(String.valueOf(cod[0]));
            qtaRmv = (int) qta.getSelectedItem();
            if(!go.updateComponent(rmCod, -qtaRmv)){
                JOptionPane.showMessageDialog(null, "Componente inesistente\no errore di accesso al DB", "Errore", JOptionPane.ERROR_MESSAGE);
            } else JOptionPane.showMessageDialog(null, "Quantità aggiornata", "Aggiunto", JOptionPane.INFORMATION_MESSAGE);
        });

        comp.addActionListener(e -> {
            if(comp.getSelectedItem() != null) {
                qta.removeAllItems();
                String item = (String) comp.getSelectedItem();
                String[] id;
                id = item.split(" ");
                qtaToRmv = go.getQuantityByID(Integer.parseInt(id[0]));
                for(int i = 1; i <= qtaToRmv; i++)
                    qta.addItem(i);
            }
        });

        comboBoxPanel.add(comp);
        comboBoxPanel.add(new JLabel("Pieces to remove:"));
        comboBoxPanel.add(qta);

        choosePanel.add(comboBoxPanel, BorderLayout.CENTER);
        choosePanel.add(rmv, BorderLayout.SOUTH);

        bckg.add(btnPanel, BorderLayout.CENTER);
        bckg.add(choosePanel, BorderLayout.SOUTH);
        c.add(bckg);

        setResizable(false);
        //setSize(300,300);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /*public static void main(String[] args) throws SQLException {
        Remove remove = new Remove();
    }*/

    public void addItemToRmv(JComboBox c, ArrayList<AbstractComponent> str) {
        for(JButton b : btnArray) {
            b.setMargin(new Insets(10, 10, 10, 10));
            btnPanel.add(b);
            b.addActionListener(e -> {
                c.removeAllItems();
                found = false;
                for(AbstractComponent x : str) {
                    s += x.getID() + " " + x.getType() + " " + x.getName() + " " + x.getPrice() + " " + x.getQuantity() + "\n";
                    if(x.getType().equals(b.getText().toUpperCase())) {
                        c.addItem(s);
                        found = true;
                    }
                    s = "";
                }
                rmv.setEnabled(found);
                if(!found) {
                    qta.removeAllItems();
                    JOptionPane.showMessageDialog(null, "No items for " + b.getText().toUpperCase(), "No items found", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
    }
}
