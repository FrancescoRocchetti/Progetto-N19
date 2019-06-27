package Interface;

import Components.AbstractComponent;
import Gestione.GestoreOperazioni;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.ArrayList;

/**
 * Interfaccia usata per semplicemente visualizzare
 * l'inventario con una tabella
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 *
 */

public class CompList extends JFrame {

    private GestoreOperazioni go;
    private JPanel bckg;
    private JPanel panel;
    private JScrollPane pane;

    public CompList(InserimentoSpecifiche ins, GestoreOperazioni go) {
        super("Inventario");
        this.go = go;
        go.setListMode(this);
        Container c = getContentPane();
        ins.setVisible(false);
        bckg = new JPanel(new BorderLayout());
        obtainParts("Sto scaricando i dati...");
        JButton btn = new JButton("Ok");
        bckg.add(btn, BorderLayout.SOUTH);
        c.add(bckg);

        btn.addActionListener(e -> dispose());

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                ins.setLocationRelativeTo(CompList.this);
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

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600,400);
        setLocationRelativeTo(ins);
        setVisible(true);
    }

    /**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * la generazione della lista è avvenuta correttamente
     *
     * @param arr
     */
    public void successList(ArrayList<AbstractComponent> arr){
        Object[][] obj = getObjectFromComps(arr);
        JTable table = createTable(obj);
        pane = new JScrollPane(table);
        bckg.remove(panel);
        bckg.add(pane, BorderLayout.CENTER);
        bckg.revalidate();
    }

    /**
     * Funzione che viene richiamata da GestoreOperazioni quando
     * la generazione della lista non è avvenuta correttamente
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

        int[] dim = {40,55,250,80,70};
        for (int i = 0; i < dim.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(dim[i]);
            table.getColumnModel().getColumn(i).setResizable(false);
        }

        //table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(20);
        table.setDefaultEditor(Object.class, null);
        return table;
    }

    private void loading(String str){
        if (pane != null) bckg.remove(pane);
        URL url = getClass().getResource("Resources/loading.gif");
        ImageIcon img = new ImageIcon(url);
        panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(img);
        JLabel txt = new JLabel(str);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setBorder(new EmptyBorder(0, 0, 30, 0));
        panel.add(label, BorderLayout.CENTER);
        panel.add(txt, BorderLayout.SOUTH);
        bckg.add(panel, BorderLayout.CENTER);
        bckg.revalidate();
    }
}
