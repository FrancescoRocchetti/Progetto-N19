package Interface;

import InterfacingDB.PCParts;

import javax.swing.*;

import Components.AbstractComponent;

import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Update extends JFrame {
    private final static int QTA = 99;

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
    private JSpinner qta;
    private JButton up;
    private String s = "";
    private boolean found;
    private GestoreOperazioni go;
    private String[] imgs;
    private String[] btnNames;


    public Update(InserimentoSpecifiche ins, GestoreOperazioni go) throws SQLException {
        super("Aggiornamento");
        ins.setVisible(false);
        this.go = go;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        c = getContentPane();
        bckg = new JPanel(new BorderLayout());
        btnPanel = new JPanel(new GridLayout(2, 5));
        choosePanel = new JPanel(new BorderLayout());
        comboBoxPanel = new JPanel(new GridLayout(3, 1));
        caseButton = new JButton("CASE");
        cooler = new JButton("Cooler");
        cpu = new JButton("CPU");
        gpu = new JButton("GPU");
        mobo = new JButton("MOBO");
        os = new JButton("OS");
        psu = new JButton("PSU");
        ram = new JButton("RAM");
        storage = new JButton("STORAGE");
        other = new JButton("Altro");
        btnArray = new JButton[]{caseButton, cooler, cpu, gpu, mobo, psu, ram, storage, os, other};
        imgs = new String[]{"nav-case.png", "nav-cpucooler.png", "nav-cpu.png", "nav-videocard.png", "nav-motherboard.png", "nav-os.png", "nav-powersupply.png", "nav-memory.png", "nav-ssd.png", "nav-other.png"};
        btnNames = new String[]{"CASE", "Cooler", "CPU", "GPU", "MOBO", "OS", "PSU", "RAM", "STORAGE", "Altro"};
        comp = new JComboBox();
        comp.addItem("No item selected...");
        qta = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        setSpinnerNotWritable(qta);
        up = new JButton("Update");
        up.setEnabled(false);

        addItemToUpdate(comp);

        up.addActionListener(e -> {
            String item;
            String[] cod;
            int upCod;
            item = (String) comp.getSelectedItem();
            cod = item.split(" ");
            upCod = Integer.parseInt(String.valueOf(cod[0]));
            if (!go.updateComponent(upCod, (int) qta.getValue())) {
                JOptionPane.showMessageDialog(null, "Componente inesistente\no errore di accesso al DB", "Errore", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(null, "Quantità aggiornata", "Aggiunto", JOptionPane.INFORMATION_MESSAGE);
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
        comboBoxPanel.add(new JLabel("Pieces to add:"));
        comboBoxPanel.add(qta);

        choosePanel.add(comboBoxPanel, BorderLayout.CENTER);
        choosePanel.add(up, BorderLayout.SOUTH);

        bckg.add(btnPanel, BorderLayout.CENTER);
        bckg.add(choosePanel, BorderLayout.SOUTH);
        c.add(bckg);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setSpinnerNotWritable(JSpinner spinner) {
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }

    // TODO: usare GestoreComponenti
    // FABIO: Fatto
    public void addItemToUpdate(JComboBox c) {
        int i = 0;
        for (JButton b : btnArray) {
            b.setMargin(new Insets(10, 10, 10, 10));
            URL url = getClass().getResource("Imgs/" + imgs[i]);
            ImageIcon img = new ImageIcon(url);
            Image image = img.getImage();
            Image newImage = image.getScaledInstance(100,100, Image.SCALE_DEFAULT);
            img = new ImageIcon(newImage);
            b.setLayout(new BorderLayout());
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
                up.setEnabled(found);
                if (!found) {
                    JOptionPane.showMessageDialog(null, "No items for " + b.getText().toUpperCase(), "No items found", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
    }
}
