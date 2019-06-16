package Interface;

import Components.AbstractComponent;
import Constraints.ConsistencyConstraint;
import Gestione.GestoreScelte;
import Components.PCParts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.ArrayList;

public class Piattaforma extends JFrame{

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
    private JButton noBudgetConfig;
    private JButton budgetConfig;

    private GestoreScelte gs;

    private int index;
    private int rowAdd;
    private int rowRmv;
    private int idAdd;
    private int idRmv;

    public Piattaforma() {
        super("Configuratore di PC");
        gs = new GestoreScelte(this);

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
        confirmConfig.setEnabled(false);
        btnpanel = new JPanel(new GridLayout(5, 1));
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
        noBudgetConfig = new JButton("Configuration without budget");
        budgetConfig = new JButton("Configuration with budget");
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
        confirmConfigListener(confirmConfig);

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
        components.addTab("Storage", panels[3]);
        components.addTab("GPU", panels[4]);
        components.addTab("Power Supply", panels[5]);
        components.addTab("Cooler CPU", panels[6]);
        components.addTab("Operating System", panels[7]);
        components.addTab("Case", panels[8]);
        components.addTab("Other", panels[9]);

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
        btnpanel.add(noBudgetConfig);
        btnpanel.add(budgetConfig);
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
        budgetConfigListener(budgetConfig);
        noBudgetConfigListener(noBudgetConfig);
        loginListener();
        newConfigListener();
        rechargeListener();
        exitListener();

        obtainParts(components.getSelectedIndex());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setResizable(false);
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        setVisible(true);

    }

    private void addComp(int id) {
        gs.addComp(id);
    }

    private void obtainParts(int i) {
        index = i;
        components.setEnabled(false);
        panels[index].removeAll();
        URL url = getClass().getResource("Resources/loading.gif");
        ImageIcon img = new ImageIcon(url);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(img);
        JLabel txt = new JLabel("Sto scaricando i dati...");
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setBorder(new EmptyBorder(0, 0, 30, 0));
        panel.add(label, BorderLayout.CENTER);
        panel.add(txt, BorderLayout.SOUTH);
        panels[index].add(panel);
        panels[index].setLayout(new GridLayout());
        gs.obtainParts(CMP[index]);
    }

    private void confirmConfigListener(JButton btn) {
        btn.addActionListener(e -> {
            if(ConsistencyConstraint.checkRes(gs.getScp())) {
                ConfirmList c = new ConfirmList(this, getCart(), gs);
            } else
                JOptionPane.showMessageDialog(this, "Non hai ancora inserito tutti i componenti necessari.", "Errore", JOptionPane.ERROR_MESSAGE);
        });
    }

    private void budgetConfigListener(JButton btn) {
        btn.addActionListener(e -> {
            int budget = Integer.parseInt(JOptionPane.showInputDialog("Budget:"));
            BudgetConfig b = new BudgetConfig(budget);
        });
    }

    private void noBudgetConfigListener(JButton btn) {
        btn.addActionListener(e -> {
        });
    }

    private void addButtonListener(JButton btn) {
        btn.addActionListener(e -> {
            rmv.setEnabled(false);
            addComp(idAdd);
            Object[][] data = getCart();
            DefaultTableModel model = (DefaultTableModel) chooseTable.getModel();
            model.setRowCount(0);
            for (Object[] str : data) {
                model.addRow(str);
            }
            price.setText(gs.getPrice() + " €");
            watt.setText(gs.getWatt() + " W");
            checkMessage.setText(gs.getWarningTxt());
            confirmConfig.setEnabled(gs.canOrder());
        });
    }

    private void rmvButtonListener(JButton btn) {
        btn.addActionListener(e -> {
            btn.setEnabled(false);
            gs.rmvComp(idRmv);
            DefaultTableModel model = (DefaultTableModel) chooseTable.getModel();
            int index = chooseTable.getSelectedRow();
            model.removeRow(index);
            obtainParts(components.getSelectedIndex());
            panels[components.getSelectedIndex()].revalidate();
            price.setText(gs.getPrice() + " €");
            watt.setText(gs.getWatt() + " W");
            checkMessage.setText(gs.getWarningTxt());
            confirmConfig.setEnabled(gs.canOrder());
        });
    }

    private void loginListener() {
        logAdmin.addActionListener(e -> {
            Login l = new Login(this);
        });
    }

    private void newConfigListener() {
        newConfig.addActionListener(e -> {
            newConfiguration();
        });
    }

    public void newConfiguration(){
        for(int i = chooseTable.getRowCount() - 1; i >=0; i--)
            ((DefaultTableModel)chooseTable.getModel()).removeRow(i);
        price.setText("0 €");
        watt.setText("0 W");
        rmv.setEnabled(false);
        gs.newScp();
        checkMessage.setText(gs.getWarningTxt());
        confirmConfig.setEnabled(gs.canOrder());
        components.setSelectedIndex(0);
    }

    private void rechargeListener() {
        recharge.addActionListener(e -> refresh());
    }

    private void exitListener() {
        exit.addActionListener(e -> System.exit(0));
    }

    public void refresh() {
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
        addListTableMouseListener(table);

        int[] dim = {15, 250, 15, 15, 15};
        for (int i = 0; i < dim.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(dim[i]);
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(20);
        table.setDefaultEditor(Object.class, null);
        return table;
    }

    private JTable createTable() {
        DefaultTableModel dm = new DefaultTableModel();
        String[] column = {"ID", "TIPO", "NOME", "QUANTITÀ", "PREZZO"};
        dm.setDataVector(getCart(), column);
        JTable table = new JTable(dm);
        TableColumn col = table.getColumnModel().getColumn(3);
        table.removeColumn(col);

        int[] dim = {1, 7, 110, 5};
        for (int i = 0; i < dim.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(dim[i]);
            table.getColumnModel().getColumn(i).setResizable(false);
        }

        addCartTableMouseListener(table);

        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        return table;
    }


    public void updateListTable(ArrayList<AbstractComponent> arr) {
        if (arr == null) {
            JOptionPane.showMessageDialog(this, "Errore lettura componenti.", "Errore", JOptionPane.ERROR_MESSAGE);
            panels[index].removeAll();
            components.setEnabled(true);
            return;
        }
        compTable = createTable(arr);
        JScrollPane scroll = new JScrollPane(
                compTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panels[index].removeAll();
        panels[index].add(scroll);
        panels[index].setLayout(new GridLayout());
        components.setEnabled(true);
    }

    private void addCartTableMouseListener(JTable table){
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    rmv.setEnabled(true);
                    add.setEnabled(false);
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
    }

    private void addListTableMouseListener(JTable table){
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    rowAdd = ((JTable) e.getSource()).getSelectedRow();
                    if((int) table.getValueAt(rowAdd, 2) > 0) {
                        add.setEnabled(true);
                        rmv.setEnabled(false);
                        idAdd = (int) ((JTable) e.getSource()).getValueAt(rowAdd, 0);
                    } else {
                        add.setEnabled(false);
                        checkMessage.setForeground(Color.RED);
                        checkMessage.setText(checkMessage.getText()+"Disponibilità insufficiente");
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
    }

    private Object[][] getCart() {
        ArrayList<AbstractComponent> comp = gs.getSelectedComponents();
        if (comp == null) {
            return null;
        }
        Object[][] data = new Object[comp.size()][];
        AbstractComponent abs;
        for (int i = 0; i < comp.size(); i++) {
            data[i] = new Object[5];
            abs = comp.get(i);
            data[i][0] = abs.getID();
            data[i][1] = abs.getType();
            data[i][2] = abs.getName();
            data[i][3] = abs.getQuantity();
            data[i][4] = abs.getPrice() + " €";
        }
        return data;
    }
}

