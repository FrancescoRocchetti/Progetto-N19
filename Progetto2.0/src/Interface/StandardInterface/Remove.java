/*
package Interface;

import Components.AbstractComponent;
import Gestione.GestoreOperazioni;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

*/
/**
 * Interfaccia usata per rimuovere i componenti
 * dall'inventario
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 *//*


public class Remove extends AbstractInterface {
    private Container c;
    private JPanel choosePanel;
    private JButton rmv;
    private GestoreOperazioni go;
    private JButton close;
    private JPanel southPanel;
    private JScrollPane tablePane;

    private int[] rowRmv;
    private int[] idRmv;


    public Remove(InserimentoSpecifiche ins, GestoreOperazioni go) {
        super("Remove component");
        ins.setVisible(false);
        this.go = go;
        this.go.setRemoveMode(this);
        JLabel label = new JLabel("Seleziona il/i componente/i da rimuovere");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        bckg = new JPanel(new BorderLayout());
        obtainParts("Sto scaricando i dati...");
        c = getContentPane();
        choosePanel = new JPanel(new BorderLayout());
        rmv = new JButton("Remove");
        close = new JButton("Close");
        southPanel = new JPanel(new GridLayout(1, 2));
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
                ins.setLocationRelativeTo(Remove.this);
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
            loadTime("Sto rimuovendo il/i componente/i selezionato/i...");
            rmv.setEnabled(false);
            close.setEnabled(false);
            go.remove(idRmv);
        });

        close.addActionListener(e -> dispose());

        setResizable(false);
        setSize(600, 400);
        setLocationRelativeTo(ins);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    */
/**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * la rimozione col ThreadRemove è avvenuta correttamente
     *//*

    public void successRemove() {
        JOptionPane.showMessageDialog(this, "Componente/i rimosso/i", "Successo", JOptionPane.INFORMATION_MESSAGE);
        reload();
    }

    */
/**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * la rimozione col ThreadRemove non è avvenuta correttamente
     *//*

    public void failureRemove() {
        JOptionPane.showMessageDialog(this, "Errore nella rimozione", "Fallito", JOptionPane.ERROR_MESSAGE);
        reload();
    }

    */
/**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * la generazione della lista col ThreadList è avvenuta correttamente
     *//*

    public void successList(ArrayList<AbstractComponent> arr) {
        Object[][] obj = getObject(arr);
        JTable table = createTable(obj);
        tablePane = new JScrollPane(table);
        bckg.remove(loadingPanel);
        bckg.add(tablePane, BorderLayout.CENTER);
        bckg.revalidate();
    }

    */
/**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * la generazione della lista col ThreadList non è avvenuta correttamente
     *//*

    public void failureList() {
        JOptionPane.showMessageDialog(this, "Errore acquisizione dati", "Fallito", JOptionPane.ERROR_MESSAGE);
        dispose();
    }

    //Funzione che ottiene i componenti e che fa partire il ThreadList
    private void obtainParts(String str) {
        loadTime(str);
        go.getListComponents();
    }

    private Object[][] getObject(ArrayList<AbstractComponent> comp) {
        return super.getObjectFromComps(comp);
    }

    private JTable createTable(Object[][] data) {
        DefaultTableModel dm = new DefaultTableModel();
        String[] column = {"ID", "TIPO", "NOME", "QUANTITA", "PREZZO"};

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
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(20);
        table.setDefaultEditor(Object.class, null);
        return table;
    }

    private void addListTableMouseListener(JTable table) {
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rowRmv = ((JTable) e.getSource()).getSelectedRows();
                if (rowRmv.length != 0) {
                    rmv.setEnabled(true);
                    idRmv = new int[rowRmv.length];
                    for (int i = 0; i < rowRmv.length; i++)
                        idRmv[i] = (int) ((JTable) e.getSource()).getValueAt(rowRmv[i], 0);
                } else {
                    rmv.setEnabled(false);
                    idRmv = null;
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
*/
