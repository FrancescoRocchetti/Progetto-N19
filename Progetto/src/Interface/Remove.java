package Interface;

import Components.AbstractComponent;
import InterfacingDB.PCParts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
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
    private String s = "";
    private boolean found;
    private int qtaToRmv = 0;
    private GestoreOperazioni go;
    private String[] imgs;
    private String[] btnNames;
    private JButton close;
    private JPanel southPanel;


    public Remove(InserimentoSpecifiche ins, GestoreOperazioni go) {
        super("Remove component");
        ins.setVisible(false);
        this.go = go;
        c = getContentPane();
        bckg = new JPanel(new BorderLayout());
        btnPanel = new JPanel(new GridLayout(2, 5));
        choosePanel = new JPanel(new BorderLayout());
        comboBoxPanel = new JPanel(new GridLayout(1, 1));
        caseButton = new JButton();
        cooler = new JButton();
        cpu = new JButton();
        gpu = new JButton();
        mobo = new JButton();
        os = new JButton();
        psu = new JButton();
        ram = new JButton();
        storage = new JButton();
        other = new JButton();
        btnArray = new JButton[]{caseButton, cooler, cpu, gpu, mobo, psu, ram, storage, os, other};
        imgs = new String[]{"nav-case.png", "nav-cpucooler.png", "nav-cpu.png", "nav-videocard.png", "nav-motherboard.png", "nav-os.png", "nav-powersupply.png", "nav-memory.png", "nav-ssd.png", "nav-other.png"};
        btnNames = new String[]{"CASE", "Cooler", "CPU", "GPU", "MOBO", "OS", "PSU", "RAM", "STORAGE", "Altro"};
        comp = new JComboBox();
        comp.addItem("No item selected...");
        //qta = new JComboBox();
        rmv = new JButton("Remove");
        close = new JButton("Close");
        southPanel = new JPanel(new GridLayout(1,2));
        rmv.setEnabled(false);

        addItemToRmv(comp);

        rmv.addActionListener(e -> {
            String item;
            String[] cod;
            int rmCod;
            int qtaRmv;
            item = (String) comp.getSelectedItem();
            cod = item.split(" ");
            rmCod = Integer.parseInt(String.valueOf(cod[0]));
            //qtaRmv = (int) qta.getSelectedItem();
            if(go.remove(rmCod)) {
                JOptionPane.showMessageDialog(null, "Componente rimosso", "Rimozione", JOptionPane.INFORMATION_MESSAGE);
                addItemToRmv(comp);
            }
            /*if (!go.updateComponent(rmCod, -qtaRmv)) {
                JOptionPane.showMessageDialog(null, "Componente inesistente\no errore di accesso al DB", "Errore", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(null, "Quantità aggiornata", "Aggiunto", JOptionPane.INFORMATION_MESSAGE);*/
        });

        close.addActionListener(e -> {
            dispose();
            ins.setVisible(true);
        });

        comp.addActionListener(e -> {
            if (comp.getSelectedItem() != null) {
                //qta.removeAllItems();
                String item = (String) comp.getSelectedItem();
                String[] id;
                id = item.split(" ");
                qtaToRmv = go.getQuantityByID(Integer.parseInt(id[0]));
                /*for (int i = 1; i <= qtaToRmv; i++)
                    qta.addItem(i);*/
            }
        });

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                ins.setVisible(true);
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        comboBoxPanel.add(comp);
        //comboBoxPanel.add(new JLabel("Pieces to remove:"));
        //comboBoxPanel.add(qta);

        choosePanel.add(comboBoxPanel, BorderLayout.CENTER);
        southPanel.add(rmv);
        southPanel.add(close);
        choosePanel.add(southPanel, BorderLayout.SOUTH);

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

    public void addItemToRmv(JComboBox c) {
        int i = 0;
        for (JButton b : btnArray) {
            b.setMargin(new Insets(10, 10, 10, 10));
            URL url = getClass().getResource("Imgs/" + imgs[i]);
            ImageIcon img = new ImageIcon(url);
            /*Image image = img.getImage();
            Image newImage = image.getScaledInstance(100,100, Image.SCALE_DEFAULT);
            img = new ImageIcon(newImage);*/
            b.setIcon(img);
            b.setText(btnNames[i]);
            i++;
            btnPanel.add(b);
            b.addActionListener(e -> {
                ArrayList<AbstractComponent> str = go.getComponentsFromDB(PCParts.valueOf(b.getAccessibleContext().getAccessibleName().toUpperCase()));
                c.removeAllItems();
                found = false;
                for (AbstractComponent x : str) {
                    s = x.getID() + " " + x.getType() + " " + x.getName() + " " + x.getPrice() + " " + x.getQuantity() + "\n";
                    //if(x.getType().equals(b.getText().toUpperCase())) {
                    c.addItem(s);
                    found = true;
                    //}
                    //s = "";
                }
                rmv.setEnabled(found);
                if (!found)
                    c.addItem("No items...");
            });
        }
    }
}
