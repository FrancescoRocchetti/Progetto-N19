package Interface;

import Components.AbstractComponent;
import InterfacingDB.PCParts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private JMenu autoConfig;
    private JMenuItem newConfig;
    private JMenuItem exit;
    private JMenuItem logAdmin;
    private JMenuItem recharge;
    private JMenuItem getAutoConfig;
    private JPanel bckg;
    private JPanel panel;
    private JPanel btnpanel;
    private JButton confirmConfig;
    private JButton add;
    private JButton rmv;

    private JPanel[] panels;

    private JPanel infoBox;
    private JPanel listItem;
    private JScrollPane scroll;
    private JTextField price;
    private JTextField watt;
    private JLabel total;
    private JLabel power;
    private JPanel totPanel;
    private JPanel checkPane;
    private JTextArea checkMessage;
    private JTable chooseTable;
    private JTable compTable;
    private JPanel wattPanel;

    private GestoreScelte gs;

    private int rowAdd;
    private int rowRmv;
    private int idAdd;
    private int idRmv;

    public Piattaforma() {
        super("Configuratore di PC");
        gs = new GestoreScelte();

        Loading l = new Loading();
        if (!gs.checkInternet()) {
            JOptionPane.showMessageDialog(null, "Impossibile stabilire una connessione a Internet.\nIl programma verrà terminato.", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(10);
        }

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
        //compTable = new JTable();
        chooseTable = createTable();
        scroll = new JScrollPane(chooseTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.getHorizontalScrollBar().setUnitIncrement(10);
        totPanel = new JPanel(new GridLayout(1, 2));
        wattPanel = new JPanel(new GridLayout(1, 2));
        confirmConfig = new JButton("Confirm configuration");
        btnpanel = new JPanel(new GridLayout(3, 1));
        add = new JButton("Add");
        rmv = new JButton("Remove");
        panel = new JPanel(new BorderLayout());
        add.setEnabled(false);
        rmv.setEnabled(false);
        total = new JLabel("Totale:");
        price = new JTextField();
        power = new JLabel("Power:");
        watt = new JTextField();
        price.setText("0 €");
        price.setEditable(false);
        watt.setText("0 W");
        watt.setEditable(false);
        price.setHorizontalAlignment(SwingConstants.RIGHT);
        watt.setHorizontalAlignment(SwingConstants.RIGHT);
        listItem.setPreferredSize(new Dimension(300, getHeight() / 2));
        listItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        listItem.setBackground(Color.LIGHT_GRAY);
        checkPane = new JPanel(new BorderLayout());
        checkMessage = new JTextArea();
        checkMessage.setEditable(false);
        checkPane.setPreferredSize(new Dimension(300, getHeight() / 2));
        checkPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        checkPane.setBackground(Color.LIGHT_GRAY);
        checkMessage.setBackground(Color.LIGHT_GRAY);
        //checkMessage.setText("Compatibilità delle componenti");
        checkMessage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menuBar = new JMenuBar();
        file = new JMenu("File");
        updateDB = new JMenu("Connection");
        autoConfig = new JMenu("Auto configuration");
        newConfig = new JMenuItem("New configuration");
        exit = new JMenuItem("Exit");
        logAdmin = new JMenuItem("Login as administrator");
        recharge = new JMenuItem("Refresh inventory");
        getAutoConfig = new JMenuItem("Start");

        // Aggiunta componenti
        file.add(newConfig);
        file.add(exit);
        updateDB.add(logAdmin);
        updateDB.add(recharge);
        autoConfig.add(getAutoConfig);
        menuBar.add(file);
        menuBar.add(updateDB);
        menuBar.add(autoConfig);

        components.addTab("Mother Board", panels[0]);
        components.addTab("CPU", panels[1]);
        components.addTab("RAM", panels[2]);
        components.addTab("STORAGE", panels[3]);
        components.addTab("GPU", panels[4]);
        components.addTab("Power Supply", panels[5]);
        components.addTab("Cooler CPU", panels[6]);
        components.addTab("Operating System", panels[7]);
        components.addTab("CASE", panels[8]);
        components.addTab("ALTRO", panels[9]);

        components.addChangeListener(e -> {
            add.setEnabled(false);
            rowAdd = -1;
            obtainParts(components.getSelectedIndex());
        });

        totPanel.add(total);
        totPanel.add(price);
        wattPanel.add(power);
        wattPanel.add(watt);
        btnpanel.add(add);
        btnpanel.add(rmv);
        btnpanel.add(confirmConfig);
        panel.add(totPanel, BorderLayout.NORTH);
        panel.add(wattPanel, BorderLayout.SOUTH);
        checkPane.add(btnpanel, BorderLayout.NORTH);
        checkPane.add(checkMessage, BorderLayout.CENTER);
        listItem.add(scroll, BorderLayout.CENTER);
        listItem.add(panel, BorderLayout.SOUTH);
        infoBox.add(listItem);
        infoBox.add(checkPane);

        bckg.add(infoBox, BorderLayout.EAST);
        bckg.add(components, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        c.add(bckg);

        addButtonListener(add);
        rmvButtonListener(rmv);
        loginListener();
        newConfigListener();
        rechargeListener();
        exitListener();
        obtainParts(components.getSelectedIndex());
        l.dispose();

        // Opzioni frame
        //setBackground(Color.BLACK);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1050, 500);
        setResizable(false);
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        setVisible(true);
    }

    private void addComp(int id) {
        gs.addComp(id);
    }

    private void obtainParts(int index) {
        ArrayList<AbstractComponent> arr;
        arr = gs.obtainParts(CMP[index]);
        if (arr == null) {
            JOptionPane.showMessageDialog(null, "Errore lettura componenti.\nIl programma verrà terminato.", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(10);
        }
        panels[index].removeAll();
        compTable = createTable(arr);
        JScrollPane scroll = new JScrollPane(
                compTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panels[index].add(scroll);
        panels[index].setLayout(new GridLayout());
    }

    private void addButtonListener(JButton btn) {
        btn.addActionListener(e -> {
            rmv.setEnabled(false);
            addComp(idAdd);
            Object[][] data = gs.getCart();
            DefaultTableModel model = (DefaultTableModel) chooseTable.getModel();
            model.setRowCount(0);
            for (Object[] str : data) {
                model.addRow(str);
            }
            price.setText(gs.getPrice() + " €");
            watt.setText(gs.getWatt() + " W");
        });
    }

    private void rmvButtonListener(JButton btn) {
        btn.addActionListener(e -> {
            btn.setEnabled(false);
            rmvComp(idRmv);
            DefaultTableModel model = (DefaultTableModel) chooseTable.getModel();
            int index = chooseTable.getSelectedRow();
            model.removeRow(index);
            obtainParts(components.getSelectedIndex());
            panels[components.getSelectedIndex()].revalidate();
            price.setText(gs.getPrice() + " €");
            watt.setText(gs.getWatt() + " W");
        });
    }

    private void rmvComp(int id) {
        gs.rmvComp(id);
    }

    private void loginListener() {
        logAdmin.addActionListener(e -> {
            Login l = new Login(this);
            l.setLocationRelativeTo(this);
        });
    }

    private void newConfigListener() {
        newConfig.addActionListener(e -> {
            for(int i = chooseTable.getRowCount() - 1; i >=0; i--)
                ((DefaultTableModel)chooseTable.getModel()).removeRow(i);
            price.setText("0 €");
            watt.setText("0 W");
            rmv.setEnabled(false);
            gs.newScp();
        });
    }

    private void rechargeListener() {
        recharge.addActionListener(e -> refresh());
    }

    private void exitListener() {
        exit.addActionListener(e -> System.exit(0));
    }

    void refresh() {
        for (JPanel p : panels)
            p.removeAll();
        price.setText("0 €");
        DefaultTableModel model = (DefaultTableModel) chooseTable.getModel();
        model.setRowCount(0);
        gs.newScp();
        obtainParts(components.getSelectedIndex());
    }

    private JTable createTable(ArrayList<AbstractComponent> arr) {
        DefaultTableModel dm = new DefaultTableModel();
        Object[][] data = new Object[arr.size()][];
        String[] column = {"ID", "NOME", "QUANTITÁ", "PREZZO", "RANKING"};

        for (int i = 0; i < arr.size(); i++) {
            data[i] = new Object[COLUMNS];
            AbstractComponent abs = arr.get(i);
            data[i][0] = abs.getID();
            data[i][1] = abs.getName();
            data[i][2] = abs.getQuantity();
            data[i][3] = abs.getPrice() + " €";
            data[i][4] = abs.getPerformance();
        }

        dm.setDataVector(data, column);
        JTable table = new JTable(dm);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    rowAdd = ((JTable) e.getSource()).getSelectedRow();
                    if((int) table.getValueAt(rowAdd, 2) > 0) {
                        add.setEnabled(true);
                        checkMessage.setText("");
                        idAdd = (int) ((JTable) e.getSource()).getValueAt(rowAdd, 0);
                    } else {
                        add.setEnabled(false);
                        checkMessage.setForeground(Color.RED);
                        checkMessage.setText("Disponibilità insufficiente");
                    }
                } catch (ArrayIndexOutOfBoundsException o) {
                    add.setEnabled(false);
                    rowAdd = -1;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //tableMouseListener(table);
        //chooseTable.getColumn("ADD").setCellRenderer(new AddButtonColumn(chooseTable, 0, arr));
        //chooseTable.getColumn("REMOVE").setCellRenderer(new RemoveButtonColumn(chooseTable,1, arr));

        int[] dim = {15, 250, 15, 15, 15};
        for (int i = 0; i < dim.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(dim[i]);
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(20);
        table.setDefaultEditor(Object.class, null);
        return table;
    }

    private JTable createTable() {
        DefaultTableModel dm = new DefaultTableModel();
        String[] column = {"ID", "TIPO", "NOME", "QUANTITÁ", "PREZZO"};
        dm.setDataVector(gs.getCart(), column);
        JTable table = new JTable(dm);
        //chooseTable.getColumn("ADD").setCellRenderer(new AddButtonColumn(chooseTable, 0, arr));
        //chooseTable.getColumn("REMOVE").setCellRenderer(new RemoveButtonColumn(chooseTable,1, arr));

        int[] dim = {3, 7, 25, 20, 20};
        for (int i = 0; i < dim.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(dim[i]);
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        //tableMouseListener(table);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    rmv.setEnabled(true);
                    rowRmv = ((JTable) e.getSource()).getSelectedRow();
                    idRmv = (int) ((JTable) e.getSource()).getValueAt(rowRmv, 0);
                } catch (ArrayIndexOutOfBoundsException o) {
                    rmv.setEnabled(false);
                    rowRmv = -1;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        return table;
    }
}

