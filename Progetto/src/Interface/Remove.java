package Interface;

import Components.AbstractComponent;
import Gestione.GestoreOperazioni;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.ArrayList;

/**
 * Interfaccia usata per rimuovere i componenti
 * dall'inventario
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 *
 */

public class Remove extends JFrame {
    private Container c;
    private JPanel bckg;
    private JPanel choosePanel;
    private JButton rmv;
    private GestoreOperazioni go;
    private JButton close;
    private JPanel southPanel;
    private JPanel loadingPanel;
    private JScrollPane tablePane;

    private int rowRmv;
    private int idRmv;


    public Remove(InserimentoSpecifiche ins, GestoreOperazioni go) {
        super("Remove component");
        ins.setVisible(false);
        this.go = go;
        this.go.setRemoveMode(this);
        JLabel label = new JLabel("Seleziona il componente da rimuovere");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        bckg = new JPanel(new BorderLayout());
        obtainParts("Sto scaricando i dati...");
        c = getContentPane();
        choosePanel = new JPanel(new BorderLayout());
        rmv = new JButton("Remove");
        close = new JButton("Close");
        southPanel = new JPanel(new GridLayout(1,2));
        rmv.setEnabled(false);
        southPanel.add(rmv);
        southPanel.add(close);
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
                ins.setLocation(Remove.super.getLocation());
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

        //ActionListener che fa eliminare un componente e che fa partire ThreadRemove
        rmv.addActionListener(e -> {
            loading("Sto rimuovendo il componente selezionato...");
            rmv.setEnabled(false);
            close.setEnabled(false);
            go.remove(idRmv);
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

    /**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * la rimozione col ThreadRemove è avvenuta correttamente
     */
    public void successRemove(){
        JOptionPane.showMessageDialog(this, "Componente rimosso", "Successo", JOptionPane.INFORMATION_MESSAGE);
        reload();
    }
    /**
    * Funzione che viene richiamata da GestoreOperazioni quando
    * la rimozione col ThreadRemove non è avvenuta correttamente
    */
    public void failureRemove(){
        JOptionPane.showMessageDialog(this, "Errore nella rimozione", "Fallito", JOptionPane.ERROR_MESSAGE);
        reload();
    }

    /**
    * Funzione che viene richiamata da GestoreOperazioni quando
    * la generazione della lista col ThreadList è avvenuta correttamente
    */
    public void successList(ArrayList<AbstractComponent> arr){
        Object[][] obj = getObjectFromComps(arr);
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
                    rowRmv = ((JTable) e.getSource()).getSelectedRow();
                    rmv.setEnabled(true);
                    idRmv = (int) ((JTable) e.getSource()).getValueAt(rowRmv, 0);
                } catch (ArrayIndexOutOfBoundsException o) {
                    rmv.setEnabled(false);
                    idRmv = -1;
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

    private void reload(){
        bckg.remove(loadingPanel);
        close.setEnabled(true);
        obtainParts("Sto scaricando i dati...");
    }
}
