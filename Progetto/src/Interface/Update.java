package Interface;

import Components.AbstractComponent;
import Gestione.GestoreOperazioni;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 * Interfaccia usata per effettuare aggiornamenti
 * sulla quantità e sul prezzo di un componente nell'inventario
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 */
public class Update extends AbstractInterface {
    private Container c;
    private JPanel choosePanel;
    private JPanel panelQuantity;
    private JPanel panelPrice;
    private JButton updateQuantity;
    private JButton updatePrice;
    private GestoreOperazioni go;
    private JButton close;
    private JPanel southPanel;
    private JSpinner spinnerQuantity;
    private JSpinner spinnerPrice;
    private JScrollPane tablePane;
    private JPanel btnPanel;
    private JPanel fieldPanel;

    private int[] rowAdd;
    private int[] idAdd;

    public Update(InserimentoSpecifiche ins, GestoreOperazioni go) {
        super("Update component");
        ins.setVisible(false);
        this.go = go;
        this.go.setUpdateMode(this);
        spinnerQuantity = initializeSpinner(0);
        spinnerPrice = initializeSpinner(1);
        panelQuantity = new JPanel(new GridLayout());
        panelQuantity.setBorder(new TitledBorder("Quantità"));
        panelPrice = new JPanel(new GridLayout());
        panelPrice.setBorder(new TitledBorder("Prezzo"));
        JLabel label = new JLabel("Seleziona il/i componente/i da aggiornare");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        bckg = new JPanel(new BorderLayout());
        obtainParts("Sto scaricando i dati...");
        c = getContentPane();
        choosePanel = new JPanel(new BorderLayout());
        updateQuantity = new JButton("Update quantity");
        updatePrice = new JButton("Update price");
        close = new JButton("Close");
        southPanel = new JPanel(new BorderLayout());
        btnPanel = new JPanel(new GridLayout(1, 3));
        fieldPanel = new JPanel(new GridLayout(2, 2));
        updateQuantity.setEnabled(false);
        updatePrice.setEnabled(false);

        panelQuantity.add(spinnerQuantity);
        panelPrice.add(spinnerPrice);
        btnPanel.add(updateQuantity);
        btnPanel.add(updatePrice);
        btnPanel.add(close);
        southPanel.add(btnPanel, BorderLayout.SOUTH);
        fieldPanel.add(new JLabel("Quantità"));
        fieldPanel.add(new JLabel("Prezzo"));
        fieldPanel.add(spinnerQuantity);
        fieldPanel.add(spinnerPrice);
        southPanel.add(fieldPanel, BorderLayout.NORTH);
        choosePanel.add(southPanel, BorderLayout.SOUTH);
        bckg.add(label, BorderLayout.NORTH);
        bckg.add(choosePanel, BorderLayout.SOUTH);
        c.add(bckg);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                ins.setLocationRelativeTo(Update.this);
                ins.setVisible(true);
                ins.toFront();
                ins.requestFocus();
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

        //ActionListener che aggiorna un componente e che fa partire ThreadUpdate
        updateQuantity.addActionListener(e -> {
            loadTime("Sto aggiornando il/i componente/i selezionato/i...");
            int qty = (int) spinnerQuantity.getValue();
            updateQuantity.setEnabled(false);
            updatePrice.setEnabled(false);
            close.setEnabled(false);
            go.updateQuantity(idAdd, qty);
        });

        updatePrice.addActionListener(e -> {
            loadTime("Sto aggiornando il/i componente/i selezionato/i...");
            int price = (int) spinnerPrice.getValue();
            updateQuantity.setEnabled(false);
            updatePrice.setEnabled(false);
            close.setEnabled(false);
            go.updatePrice(idAdd, price);
        });

        close.addActionListener(e -> dispose());

        setResizable(false);
        setSize(600, 400);
        setLocationRelativeTo(ins);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * l'aggiornamento è avvenuta correttamente
     */
    public void successUpdate() {
        JOptionPane.showMessageDialog(this, "Componente/i aggiornato/i", "Successo", JOptionPane.INFORMATION_MESSAGE);
        spinnerPrice.setValue(0);
        spinnerQuantity.setValue(0);
        reload();
    }

    /**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * l'aggiornamento non è avvenuta correttamente
     */
    public void failureUpdate() {
        JOptionPane.showMessageDialog(this, "Errore nell'aggiornamento", "Fallito", JOptionPane.ERROR_MESSAGE);
        spinnerPrice.setValue(0);
        spinnerQuantity.setValue(0);
        reload();
    }

    /**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * la generazione della lista è avvenuta correttamente
     *
     * @param arr
     */
    public void successList(ArrayList<AbstractComponent> arr) {
        Object[][] obj = getObjects(arr);
        JTable table = createTable(obj);
        tablePane = new JScrollPane(table);
        bckg.remove(loadingPanel);
        bckg.add(tablePane, BorderLayout.CENTER);
        bckg.revalidate();
    }

    /**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * la generazione della lista col ThreadList non è avvenuta correttamente
     */
    public void failureList() {
        JOptionPane.showMessageDialog(this, "Errore acquisizione dati", "Fallito", JOptionPane.ERROR_MESSAGE);
        dispose();
    }

    //Funzione che ottiene i componenti e che fa partire il ThreadList
    private void obtainParts(String str) {
        loadTime(str);
        go.getListComponents();
    }

    private Object[][] getObjects(ArrayList<AbstractComponent> comp) {
        return super.getObjectFromComps(comp);
    }

    private JTable createTable(Object[][] data) {
        DefaultTableModel dm = new DefaultTableModel();
        String[] column = {"ID", "TIPO", "NOME", "QUANTITÁ", "PREZZO"};

        dm.setDataVector(data, column);
        JTable table = new JTable(dm);
        addListTableMouseListener(table);

        int[] dim = {40, 55, 250, 80, 70};
        for (int i = 0; i < dim.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(dim[i]);
            table.getColumnModel().getColumn(i).setResizable(false);
        }

        //table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(20);
        table.setDefaultEditor(Object.class, null);
        return table;
    }

    private void addListTableMouseListener(JTable table) {
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rowAdd = ((JTable) e.getSource()).getSelectedRows();
                if (rowAdd.length != 0) {
                    updateQuantity.setEnabled(true);
                    updatePrice.setEnabled(true);
                    idAdd = new int[rowAdd.length];
                    for (int i = 0; i < rowAdd.length; i++)
                        idAdd[i] = (int) ((JTable) e.getSource()).getValueAt(rowAdd[i], 0);
                } else {
                    updateQuantity.setEnabled(false);
                    updatePrice.setEnabled(false);
                    idAdd = null;
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

    private JSpinner initializeSpinner(int min) {
        SpinnerNumberModel model = new SpinnerNumberModel(min, min, null, 1);
        JSpinner s = new JSpinner(model);
        setSpinnerNotWritable(s);
        return s;
    }

    private void setSpinnerNotWritable(JSpinner spinner) {
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }

    private void loadTime(String str) {
        if (tablePane != null) bckg.remove(tablePane);
        super.loading(str);
    }

    private void reload() {
        bckg.remove(loadingPanel);
        close.setEnabled(true);
        obtainParts("Sto scaricando i dati...");
    }
}
