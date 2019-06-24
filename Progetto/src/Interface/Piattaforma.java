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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

/**
 * Interfaccia principale che ti permette di scegliere i componenti
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 *
 */

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
    private JMenuItem noBudget;
    private JMenuItem wBudget;
    private JPanel bckg;
    private JPanel panel;
    private JPanel btnpanel;
    private JButton confirmConfig;
    private JButton add;
    private JButton rmv;
    private JButton show;

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

    private int index;
    private int rowAdd;
    private int[] rowRmv;
    private int idAdd;
    private int[] idRmv;

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
        chooseTable = createTable();
        scroll = new JScrollPane(chooseTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.getHorizontalScrollBar().setUnitIncrement(10);
        totPanel = new JPanel(new GridLayout(1, 2));
        wattPanel = new JPanel(new GridLayout(1, 2));
        confirmConfig = new JButton("Confirm configuration");
        confirmConfig.setEnabled(false);
        btnpanel = new JPanel(new GridLayout(4, 1));
        add = new JButton("Add");
        rmv = new JButton("Remove");
        show = new JButton("Show info");
        panel = new JPanel(new BorderLayout());
        add.setEnabled(false);
        rmv.setEnabled(false);
        show.setEnabled(false);
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
        checkMessage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menuBar = new JMenuBar();
        file = new JMenu("File");
        updateDB = new JMenu("Connection");
        newConfig = new JMenuItem("New configuration");
        exit = new JMenuItem("Exit");
        logAdmin = new JMenuItem("Login as administrator");
        recharge = new JMenuItem("Refresh inventory");
        autoConfig = new JMenu("Auto configuration");
        noBudget = new JMenuItem("Without budget");
        wBudget = new JMenuItem("With budget");

        // Aggiunta componenti
        file.add(newConfig);
        file.add(exit);
        updateDB.add(logAdmin);
        updateDB.add(recharge);
        autoConfig.add(noBudget);
        autoConfig.add(wBudget);
        menuBar.add(file);
        menuBar.add(updateDB);
        menuBar.add(autoConfig);

        String[] names = {"Mother Board", "CPU", "RAM", "Storage", "GPU", "Power Supply", "Cooler CPU", "Operating System", "Case", "Other"};
        for(int i = 0; i < names.length; i++)
            components.addTab(names[i], panels[i]);

        /*components.addTab("Mother Board", panels[0]);
        components.addTab("CPU", panels[1]);
        components.addTab("RAM", panels[2]);
        components.addTab("Storage", panels[3]);
        components.addTab("GPU", panels[4]);
        components.addTab("Power Supply", panels[5]);
        components.addTab("Cooler CPU", panels[6]);
        components.addTab("Operating System", panels[7]);
        components.addTab("Case", panels[8]);
        components.addTab("Other", panels[9]);*/

        components.addChangeListener(e -> {
            add.setEnabled(false);
            show.setEnabled(false);
            rowAdd = -1;
            obtainParts(components.getSelectedIndex());
        });

        show.addActionListener(e -> {
            AbstractComponent abs = gs.getCompByID(idAdd);
            new SpecsList(abs, gs,this);
        });

        totPanel.add(total);
        totPanel.add(price);
        wattPanel.add(power);
        wattPanel.add(watt);
        btnpanel.add(add);
        btnpanel.add(rmv);
        btnpanel.add(show);
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
        budgetConfigListener(wBudget);
        noBudgetConfigListener(noBudget);
        confirmConfigListener(confirmConfig);
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
    /**
     * Permette di partire di nuovo da zero con la configurazione
     */
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
        gs.obtainParts(CMP[index]);
    }

    /**
     * Aggiorna la tabella dei componenti attuale
     */
    public void refresh() {
        obtainParts(components.getSelectedIndex());
    }

    /**
     * Permette di notificare Piattaforma di un tentato aggiornamento
     * della tabella
     *
     * @param arr
     */
    public void updateListTable(ArrayList<AbstractComponent> arr) {
        if (arr == null) {
            JOptionPane.showMessageDialog(this, "Errore lettura componenti.", "Errore", JOptionPane.ERROR_MESSAGE);
            panels[index].removeAll();
            components.setEnabled(true);
            return;
        }
        compTable = createTable(arr);

        if(compTable.getRowCount() > 0){
            JScrollPane scroll = new JScrollPane(
                    compTable,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            panels[index].removeAll();
            panels[index].add(scroll);
            panels[index].setLayout(new GridLayout());
        } else {
            JLabel label = new JLabel("No available components for this configuration.");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panels[index].removeAll();
            panels[index].setLayout(new BorderLayout());
            panels[index].add(label, BorderLayout.CENTER);
        }
        components.setEnabled(true);
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
                new ConfirmList(this, getCart(), gs);
            } else
                JOptionPane.showMessageDialog(this, "Non hai ancora inserito tutti i componenti necessari.", "Errore", JOptionPane.ERROR_MESSAGE);
        });
    }

    private void budgetConfigListener(JMenuItem item) {
        item.addActionListener(e -> {
            /*
            int budget = Integer.parseInt(JOptionPane.showInputDialog("Budget:"));
            new BudgetConfig(budget);*/
            JOptionPane.showMessageDialog(this, "Non supportato ancora.", "Informazione", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void noBudgetConfigListener(JMenuItem item) {
        item.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Non supportato ancora.", "Informazione", JOptionPane.INFORMATION_MESSAGE);
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
            for(int z = 0; z < rowRmv.length; z++) {
                int i = chooseTable.getSelectedRow();
                model.removeRow(i);
            }
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
            new Login(this);
        });
    }

    private void newConfigListener() {
        newConfig.addActionListener(e -> {
            newConfiguration();
        });
    }



    private void rechargeListener() {
        recharge.addActionListener(e -> refresh());
    }

    private void exitListener() {
        exit.addActionListener(e -> System.exit(0));
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

        table.setAutoCreateRowSorter(true);
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

        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        return table;
    }




    private void addCartTableMouseListener(JTable table){
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                add.setEnabled(false);
                show.setEnabled(false);
                rowRmv = ((JTable) e.getSource()).getSelectedRows();
                if(rowRmv.length != 0) {
                    rmv.setEnabled(true);
                    rowRmv = ((JTable) e.getSource()).getSelectedRows();
                    idRmv = new int[rowRmv.length];
                    for(int i = 0; i < rowRmv.length; i++)
                        idRmv[i] = (int) ((JTable) e.getSource()).getValueAt(rowRmv[i], 0);
                } else {
                    rmv.setEnabled(false);
                    rowRmv = null;
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
                rmv.setEnabled(false);
                try {
                    rowAdd = ((JTable) e.getSource()).getSelectedRow();
                    if((int) table.getValueAt(rowAdd, 2) > 0) {
                        add.setEnabled(true);
                        show.setEnabled(true);
                        idAdd = (int) ((JTable) e.getSource()).getValueAt(rowAdd, 0);
                    } else {
                        add.setEnabled(false);
                        //show.setEnabled(false);
                        checkMessage.setForeground(Color.RED);
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

