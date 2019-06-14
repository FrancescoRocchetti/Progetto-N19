package Interface;

import Gestione.GestoreOperazioni;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ConfirmList extends JFrame{

    public ConfirmList(Piattaforma p, Object[][] data) {
        super("Inventario");

        GestoreOperazioni go = new GestoreOperazioni();

        Container c = getContentPane();
        p.setEnabled(false);
        p.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        if (data == null) {
            JOptionPane.showMessageDialog(null, "Errore lettura inventario", "Errore", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
        String[] column = {"ID", "TIPO", "NOME", "QUANTITÁ", "PREZZO"};
        JTable table = new JTable(data, column);
        int[] arr = {40,55,250,80,70};
        for(int i = 0; i < 5; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(arr[i]);
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        table.setDefaultEditor(Object.class, null);
        JScrollPane sp = new JScrollPane(table);
        JPanel panel = new JPanel();
        JButton btnOk = new JButton("Conferma");
        JLabel label = new JLabel("Sicuro di voler effettuare questo ordine?");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JButton btnCancel = new JButton("Annulla");
        JPanel pnBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(sp);
        c.setLayout(new BorderLayout());
        c.add(sp, BorderLayout.CENTER);

        pnBtn.add(btnOk);
        pnBtn.add(btnCancel);
        c.add(label, BorderLayout.NORTH);
        c.add(pnBtn, BorderLayout.SOUTH);

        btnOk.addActionListener(e -> {
            Loading l = new Loading(this);
            int[] i = getCodesOfComps(data);
            for(int cod : i){
                go.updateComponent(cod, -1);
            }
            l.dispose();
            JOptionPane.showMessageDialog(this, "Acquisto effettuato con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
            p.newConfiguration();
            p.refresh();
            dispose();
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

    private int[] getCodesOfComps(Object[][] obj){
        int[] i = new int[obj.length];

        for(int c = 0; c< obj.length; c++){
            Object[] comp = obj[c];
            i[c] = (int) comp[0];
        }
        return i;
    }
}
