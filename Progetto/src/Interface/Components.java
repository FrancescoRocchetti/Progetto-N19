package Interface;

import Components.AbstractComponent;
import InterfacingDB.Reading;
import InterfacingDB.Writing;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Components extends JFrame {
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
    private JButton[] btnArray;
    private JComboBox comp;
    private JComboBox qta;
    private JButton rmv;
    private ArrayList<AbstractComponent> componenti;
    private String s = "";
    private boolean found;
    private int qtaToRmv = 0;

    public Components() throws SQLException {
        super("Remove component");
        c = getContentPane();
        bckg = new JPanel(new BorderLayout());
        btnPanel = new JPanel(new GridLayout(3,3));
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
        btnArray = new JButton[]{caseButton, cooler, cpu, gpu, mobo, psu, ram, storage, os};
        comp = new JComboBox();
        qta = new JComboBox();
        rmv = new JButton("Remove");
        componenti = new ArrayList<>();
        Reading reading = new Reading();
        componenti = reading.read(null);

        addItemToRmv(comp, componenti);

        rmv.addActionListener(e -> {
            Writing writing = new Writing();
            String item;
            String[] cod;
            String qtaItem;
            int rmCod;
            int qtaRmv;
            item = (String) comp.getSelectedItem();
            cod = item.split(" ");
            rmCod = Integer.parseInt(String.valueOf(cod[0]));
            qtaRmv = (Integer) qta.getSelectedItem();
            System.out.println(qtaRmv);
            try {
                writing.remove(rmCod, qtaRmv);
                JOptionPane.showMessageDialog(null, item + " rimosso con successo", "Componente rimosso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        comp.addActionListener(e -> {
            if(comp.getSelectedItem() != null) {
                qta.removeAllItems();
                String item = (String) comp.getSelectedItem();
                String[] id;
                id = item.split(" ");
                try {
                    qtaToRmv = reading.getQuantityByID(Integer.parseInt(id[0]));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

        setVisible(true);
        setResizable(false);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /*public static void main(String[] args) throws SQLException {
        Components components = new Components();
    }*/

    public void addItemToRmv(JComboBox c, ArrayList<AbstractComponent> str) {
        for(JButton b : btnArray) {
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
                if(!found)
                    JOptionPane.showMessageDialog(null, "No items for " + b.getText().toUpperCase(), "No items found", JOptionPane.INFORMATION_MESSAGE);
            });
        }
    }
}
