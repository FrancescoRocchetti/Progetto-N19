package Interface;

import Components.AbstractComponent;
import Gestione.GestoreOperazioni;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.ArrayList;

/**
 * Interfaccia usata per effettuare aggiornamenti
 * sulla quantità di un componente nell'inventario
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 *
 */

public class Update extends JFrame {
    private Container c;
    private JPanel bckg;
    private JPanel choosePanel;
    private JButton add;
    private GestoreOperazioni go;
    private JButton close;
    private JPanel southPanel;
    private JPanel loadingPanel;
    private JSpinner spinner;
    private JScrollPane tablePane;

    private int rowAdd;
    private int idAdd;


    public Update(InserimentoSpecifiche ins, GestoreOperazioni go) {
        super("Update component");
        ins.setVisible(false);
        this.go = go;
        this.go.setUpdateMode(this);
        spinner = initializeSpinner();
        JLabel label = new JLabel("Seleziona il componente da aggiornare");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        bckg = new JPanel(new BorderLayout());
        obtainParts("Sto scaricando i dati...");
        c = getContentPane();
        choosePanel = new JPanel(new BorderLayout());
        add = new JButton("Update");
        close = new JButton("Close");
        southPanel = new JPanel(new GridLayout(1,3));
        add.setEnabled(false);
        southPanel.add(add);
        southPanel.add(close);
        southPanel.add(spinner);
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
                ins.setLocation(Update.super.getLocation());
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
        add.addActionListener(e -> {
            loading("Sto aggiornando il componente selezionato...");
            int qty = (int) spinner.getValue();
            add.setEnabled(false);
            close.setEnabled(false);
            go.updateComponent(idAdd, qty);
        });

        close.addActionListener(e -> {
            dispose();
        });

        setResizable(false);
        setSize(600,400);
        setLocation(ins.getLocation());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    //Funzione che viene richiamata da GestoreOperazioni quando
    //la rimozione col ThreadUpdate è avvenuta correttamente
    public void successUpdate(){
        JOptionPane.showMessageDialog(this, "Componente aggiornato", "Successo", JOptionPane.INFORMATION_MESSAGE);
        bckg.remove(loadingPanel);
        close.setEnabled(true);
        obtainParts("Sto scaricando i dati...");
    }

    //Funzione che viene richiamata da GestoreOperazioni quando
    //la rimozione col ThreadUpdate non è avvenuta correttamente
    public void failureUpdate(){
        JOptionPane.showMessageDialog(this, "Errore nell'aggiornamento", "Fallito", JOptionPane.ERROR_MESSAGE);
        bckg.remove(loadingPanel);
        close.setEnabled(true);
        obtainParts("Sto scaricando i dati...");
    }

    //Funzione che viene richiamata da GestoreOperazioni quando
    //la generazione della lista col ThreadList è avvenuta correttamente
    public void successList(ArrayList<AbstractComponent> arr){
        Object[][] obj = getObjectFromComps(arr);
        JTable table = createTable(obj);
        tablePane = new JScrollPane(table);
        bckg.remove(loadingPanel);
        bckg.add(tablePane, BorderLayout.CENTER);
        bckg.revalidate();
    }

    //Funzione che viene richiamata da GestoreOperazioni quando
    //la generazione della lista col ThreadList non è avvenuta correttamente
    public void failureList(){
        JOptionPane.showMessageDialog(this, "Errore acquisizione dati", "Fallito", JOptionPane.ERROR_MESSAGE);
        dispose();
    }

    //Funzione che ottiene i componenti e che fa partire il ThreadList
    private void obtainParts(String str) {
        loading(str);
        go.getListComponents();
    }

    private Object[][] getObjectFromComps(ArrayList<AbstractComponent> comp) {
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

    private JTable createTable(Object[][] data) {
        DefaultTableModel dm = new DefaultTableModel();
        String[] column = {"ID", "TIPO", "NOME", "QUANTITÁ", "PREZZO"};

        dm.setDataVector(data, column);
        JTable table = new JTable(dm);
        addListTableMouseListener(table);

        int[] dim = {40,55,250,80,70};
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

    private void addListTableMouseListener(JTable table){
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    rowAdd = ((JTable) e.getSource()).getSelectedRow();
                    add.setEnabled(true);
                    idAdd = (int) ((JTable) e.getSource()).getValueAt(rowAdd, 0);
                } catch (ArrayIndexOutOfBoundsException o) {
                    add.setEnabled(false);
                    idAdd = -1;
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

    private JSpinner initializeSpinner(){
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, null, 1);
        JSpinner s = new JSpinner(model);
        setSpinnerNotWritable(s);
        return s;
    }

    private void setSpinnerNotWritable(JSpinner spinner) {
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }

    private void loading(String str){
        if (tablePane != null) bckg.remove(tablePane);
        URL url = getClass().getResource("Resources/loading.gif");
        ImageIcon img = new ImageIcon(url);
        loadingPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(img);
        JLabel txt = new JLabel(str);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setBorder(new EmptyBorder(0, 0, 30, 0));
        loadingPanel.add(label, BorderLayout.CENTER);
        loadingPanel.add(txt, BorderLayout.SOUTH);
        bckg.add(loadingPanel, BorderLayout.CENTER);
        bckg.revalidate();
    }
}
