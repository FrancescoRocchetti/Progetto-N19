package Interface;

import Gestione.GestoreScelte;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

/**
 * Interfaccia che permette di confermare e procedere
 * con l'ordine
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 *
 */

public class ConfirmList extends JFrame{

    private Object[][] data;
    private Piattaforma p;
    private JButton btnOk;
    private JButton btnCancel;
    private JTable table;
    private JPanel panel;
    private JScrollPane sp;
    private JLabel label;

    public ConfirmList(Piattaforma pf, Object[][] data, GestoreScelte gs) {
        super("Inventario");
        this.data = data;
        this.p = pf;

        Container c = getContentPane();
        JPanel bckg = new JPanel(new BorderLayout());
        p.setEnabled(false);
        p.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        if (data == null) {
            JOptionPane.showMessageDialog(this, "Errore lettura inventario", "Errore", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
        table = createTable();
        sp = new JScrollPane(table);
        panel = new JPanel(new GridLayout());
        btnOk = new JButton("Conferma");
        label = new JLabel("Sicuro di voler effettuare questo ordine?");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancel = new JButton("Annulla");
        JPanel pnBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(sp);
        pnBtn.add(btnOk);
        pnBtn.add(btnCancel);
        bckg.add(panel, BorderLayout.CENTER);
        bckg.add(label, BorderLayout.NORTH);
        bckg.add(pnBtn, BorderLayout.SOUTH);
        c.add(bckg);

        btnOk.addActionListener(e -> {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            loading();
            btnOk.setEnabled(false);
            btnCancel.setEnabled(false);
            label.setText("");
            gs.confirmOrder(this);
        });

        btnCancel.addActionListener(e -> {
            dispose();
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
                p.setDefaultCloseOperation(EXIT_ON_CLOSE);
                p.setEnabled(true);
                p.toFront();
                p.requestFocus();
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
        pack();
        setLocationRelativeTo(p);
        setVisible(true);
    }

    /**
     * Restituisce i codici prodotto del carrello
     * in un vettore di interi
     *
     * @return int[]
     */
    public int[] getCodesOfComps(){
        int[] i = new int[data.length];

        for(int c = 0; c< data.length; c++){
            Object[] comp = data[c];
            i[c] = (int) comp[0];
        }
        return i;
    }

    /**
     * Restituisce il carrello come una tabella
     *
     * @return JTable
     */
    public JTable createTable(){
        String[] column = {"ID", "TIPO", "NOME", "QUANTITÁ", "PREZZO"};
        JTable table = new JTable(data, column);
        int[] arr = {40,55,250,80,70};
        for(int i = 0; i < 5; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(arr[i]);
            table.getColumnModel().getColumn(i).setResizable(false);
        }

        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        table.setDefaultEditor(Object.class, null);
        return table;
    }

    /**
     * Funzione che viene richiamata da GestoreScelte quando
     * l'acquisto è andato a buon fine
     */
    public void success(){
        JOptionPane.showMessageDialog(this, "Acquisto effettuato con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
        p.newConfiguration();
        p.refresh();
        dispose();
    }

    /**
     * Funzione che viene richiamata da GestoreScelte quando
     * l'acquisto è andato male
     */
    public void failure(){
        JOptionPane.showMessageDialog(this, "Errore durante l'acquisto dell'ordine. Riprovare più tardi", "Fallimento", JOptionPane.INFORMATION_MESSAGE);
        label.setText("Sicuro di voler effettuare questo ordine?");
        panel.removeAll();
        panel.setLayout(new GridLayout());
        panel.add(sp);
        btnOk.setEnabled(true);
        btnCancel.setEnabled(true);
        panel.revalidate();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void loading(){
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        URL url = getClass().getResource("Resources/loading.gif");
        ImageIcon img = new ImageIcon(url);
        JLabel label = new JLabel(img);
        JLabel txt = new JLabel("Sto effettuando l'ordine...");
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setBorder(new EmptyBorder(0, 0, 30, 0));
        panel.add(label, BorderLayout.CENTER);
        panel.add(txt, BorderLayout.SOUTH);
        panel.revalidate();
    }
}
