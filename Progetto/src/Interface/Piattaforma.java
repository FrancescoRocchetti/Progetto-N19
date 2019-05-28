package Interface;

import Interface.CustomJTable.RadioButtonEditor;
import Interface.CustomJTable.RadioButtonRenderer;
import InterfacingDB.PCParts;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Components.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Piattaforma extends JFrame {

    private static final int CATEGORIES = 10;
    private static final int COLUMNS = 5;
    private static final PCParts[] CMP = new PCParts[]{PCParts.MOBO, PCParts.CPU, PCParts.RAM, PCParts.STORAGE, PCParts.GPU, PCParts.PSU, PCParts.COOLER, PCParts.OS, PCParts.CASE, PCParts.ALTRO};

    private Container c;
    private Toolkit kit;
    private Dimension dim;
    private JTabbedPane components;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu updateDB;
    private JMenuItem newConfig;
    private JMenuItem exit;
    private JMenuItem logAdmin;
    private JMenuItem recharge;
    private JPanel bckg;
    private JPanel confirmPanel;
    private JButton confirmConfig;

    private JPanel[] panels;

    private JPanel infoBox;
    private JPanel listItem;
    private JTextArea items;
    private JScrollPane scroll;
    private JTextField price;
    private JLabel total;
    private JPanel totPanel;
    private JPanel checkPane;
    private JTextArea checkMessage;
    
    private GestoreScelte gs;

    public Piattaforma() {
        super("Configuratore di PC");
        gs = new GestoreScelte();

        kit = Toolkit.getDefaultToolkit();
        dim = kit.getScreenSize();

        // Inizializzazione
        c = getContentPane();
        components = new JTabbedPane();
        bckg = new JPanel(new BorderLayout());
        panels = new JPanel[CATEGORIES];
        for (int i = 0; i < CATEGORIES; i++) {
            panels[i] = new JPanel();
        }
        infoBox = new JPanel(new GridLayout(2, 1));
        listItem = new JPanel(new BorderLayout());
        items = new JTextArea();
        scroll = new JScrollPane(items, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.getHorizontalScrollBar().setUnitIncrement(10);
        totPanel = new JPanel(new GridLayout(1, 2));
        confirmPanel = new JPanel(new GridLayout(2,1));
        confirmConfig = new JButton("Confirm configuration");
        total = new JLabel("Totale:");
        price = new JTextField();
        price.setText("0 €");
        price.setEditable(false);
        price.setHorizontalAlignment(SwingConstants.RIGHT);
        listItem.setPreferredSize(new Dimension(300, getHeight() / 2));
        listItem.setBorder(BorderFactory.createLineBorder(Color.black));
        listItem.setBackground(Color.lightGray);
        items.setEditable(false);
        checkPane = new JPanel();
        checkMessage = new JTextArea();
        checkMessage.setEditable(false);
        checkPane.setPreferredSize(new Dimension(300, getHeight() / 2));
        checkPane.setBorder(BorderFactory.createLineBorder(Color.black));
        checkPane.setBackground(Color.LIGHT_GRAY);
        checkMessage.setBackground(Color.LIGHT_GRAY);
        checkMessage.setText("Compatibilità delle componenti");
        checkPane.add(checkMessage);
        menuBar = new JMenuBar();
        file = new JMenu("File");
        updateDB = new JMenu("Connection");
        newConfig = new JMenuItem("New configuration");
        exit = new JMenuItem("Exit");
        logAdmin = new JMenuItem("Login as administrator");
        recharge = new JMenuItem("Refresh inventory");

        // Aggiunta componenti
        file.add(newConfig);
        file.add(exit);
        updateDB.add(logAdmin);
        updateDB.add(recharge);
        menuBar.add(file);
        menuBar.add(updateDB);

        components.addTab("Mother Board", panels[0]);
        components.addTab("CPU", panels[1]);
        components.addTab("RAM", panels[2]);
        components.addTab("Storage", panels[3]);
        components.addTab("GPU", panels[4]);
        components.addTab("Power Supply", panels[5]);
        components.addTab("Cooler CPU", panels[6]);
        components.addTab("Operating System", panels[7]);
        components.addTab("Case", panels[8]);
        components.addTab("Other", panels[9]);


        totPanel.add(total);
        totPanel.add(price);
        confirmPanel.add(totPanel);
        confirmPanel.add(confirmConfig);
        listItem.add(confirmPanel, BorderLayout.SOUTH);
        listItem.add(scroll, BorderLayout.CENTER);
        infoBox.add(listItem);
        infoBox.add(checkPane);

        bckg.add(infoBox, BorderLayout.EAST);
        bckg.add(components, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        c.add(bckg);

        loginListener();
        newConfigListener();
        rechargeListener();
        exitListener();
        obtainParts();

        // Opzioni frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1050, 500);
        setResizable(false);
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        setVisible(true);
    }

    private void addComp(CompRadio button) {
        gs.addComp(button.getAbs());
    }

    private void displayOnPanel(JTextArea textArea) {
        textArea.setText(gs.getListAbs());
    }

    private void obtainParts(){
            ArrayList<AbstractComponent> arr;
            CompRadio[] c;

            for(int z = 0; z < CMP.length; z++) {
                arr = gs.obtainParts(CMP[z]);
                if(arr==null) {
                    JOptionPane.showMessageDialog(null, "Errore lettura componenti.\nIl programma verrà terminato.", "Errore", JOptionPane.ERROR_MESSAGE);
                    System.exit(10);
                }
                c = new CompRadio[arr.size()];
                for(int i = 0; i<arr.size(); i++) {
                    c[i] = new CompRadio("",arr.get(i));
                    radioButtonListener(c[i]);
                }
                JTable table = createTable(c);
                JScrollPane scroll = new JScrollPane(
                        table,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                panels[z].add(scroll);
                panels[z].setLayout(new GridLayout());
            }
    }

    private void radioButtonListener(CompRadio comp) {
        comp.addActionListener(e -> {
            addComp(comp);
            displayOnPanel(items);
            price.setText(gs.getPrice() + " €");
        });
    }

    private void loginListener() {
        logAdmin.addActionListener(e -> {
            Login l = new Login(this);
            l.setLocationRelativeTo(this);
        });
    }

    private void newConfigListener() {
        newConfig.addActionListener(e -> {
            price.setText("0 €");
            items.setText("");
            gs.newScp();
        });
    }

    private void rechargeListener() {
        recharge.addActionListener(e -> {
            refresh();
        });
    }

    private void exitListener() {
        exit.addActionListener(e -> System.exit(0));
    }

    public void refresh(){
        for (JPanel p : panels)
            p.removeAll();
        price.setText("0 €");
        items.setText("");
        gs.newScp();
        obtainParts();
    }

    private JTable createTable(CompRadio[] cr){
        ButtonGroup bg = new ButtonGroup();
        DefaultTableModel dm = new DefaultTableModel();
        Object[][] data = new Object[cr.length][];
        String[] column = {"SCELTA","NOME", "QUANTITÁ", "PREZZO", "RANKING"};

        for(int i = 0; i<cr.length; i++){
            bg.add(cr[i]);
            AbstractComponent abs = cr[i].getAbs();
            data[i] = new Object[COLUMNS];
            if(abs.getQuantity() == 0)
                cr[i].setEnabled(false);
            data[i][0] = cr[i];
            data[i][1] = abs.getName();
            data[i][2] = abs.getQuantity();
            data[i][3] = abs.getPrice()+" €";
            data[i][4] = abs.getPerformance();
        }

        dm.setDataVector(data, column);
        JTable table = new JTable(dm) {
            public void tableChanged(TableModelEvent e) {
                super.tableChanged(e);
                repaint();
            }
        };
        table.getColumn("SCELTA").setCellRenderer(new RadioButtonRenderer());
        table.getColumn("SCELTA").setCellEditor(new RadioButtonEditor(new JCheckBox()));

        int[] dim = {15,250,15,15,15};
        for(int i = 0; i<dim.length;i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(dim[i]);
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        return table;
    }
}

