package Interface;

import Components.AbstractComponent;
import InterfacingDB.Reading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompList extends JFrame {

    private Toolkit kit;
    private Dimension dim;

    public CompList(InserimentoSpecifiche ins, GestoreOperazioni go){
        super("Inventario");
        kit = Toolkit.getDefaultToolkit();
        dim = kit.getScreenSize();

        Container c = getContentPane();
        ins.setEnabled(false);
        ins.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        String data[][] = go.getString(null);
        if(data == null){
            JOptionPane.showMessageDialog(null,"Errore lettura inventario", "Errore", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
        String column[] = {"ID", "TIPO", "NOME", "QUANTITÁ", "PREZZO"};
        JTable table = new JTable(data, column);
        table.setDefaultEditor(Object.class, null);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(55);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane sp = new JScrollPane(table);
        JPanel panel = new JPanel();
        JButton btn = new JButton("Ok");
        panel.add(sp);
        c.setLayout(new BorderLayout());
        c.add(sp, BorderLayout.CENTER);
        c.add(btn, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        setVisible(true);

        btn.addActionListener(e -> {
                ins.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                ins.setEnabled(true);
                CompList.super.dispose();
        });

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) { }

            @Override
            public void windowClosed(WindowEvent e) {
                ins.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                ins.setEnabled(true);
            }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }
        });
    }
}
