package Interface;

import Gestione.GestoreOperazioni;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CompList extends JFrame {

    private GestoreOperazioni go;

    public CompList(InserimentoSpecifiche ins, Object[][] data) {
        super("Inventario");

        Container c = getContentPane();
        ins.setEnabled(false);
        ins.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
        JButton btn = new JButton("Ok");
        panel.add(sp);
        c.setLayout(new BorderLayout());
        c.add(sp, BorderLayout.CENTER);
        c.add(btn, BorderLayout.SOUTH);

        btn.addActionListener(e -> {
            dispose();
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(ins);
        setVisible(true);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                ins.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                ins.setEnabled(true);
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
    }
}
