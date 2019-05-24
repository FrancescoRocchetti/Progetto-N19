package Interface;

import Components.AbstractComponent;
import InterfacingDB.Reading;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompList extends JFrame {

    private ArrayList<AbstractComponent> comp;

    public CompList(InserimentoSpecifiche ins){
        super("Inventario");
        try {
            ins.setEnabled(false);
            ins.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            setLocationRelativeTo(ins);
            Container c = getContentPane();
            Reading r = new Reading();
            comp = r.read(null);
            String data[][] = getString();
            String column[] = {"ID", "TIPO", "NOME", "PREZZO"};
            JTable table = new JTable(data, column);
            table.setDefaultEditor(Object.class, null);
            JScrollPane sp = new JScrollPane(table);
            JPanel panel = new JPanel();
            JButton btn = new JButton("Ok");
            panel.add(sp);
            c.setLayout(new BorderLayout());
            c.add(sp, BorderLayout.CENTER);
            c.add(btn, BorderLayout.SOUTH);

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);
            setSize(300,400);
            setResizable(false);
            setVisible(true);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ins.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    ins.setEnabled(true);
                    CompList.super.dispose();
                }
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Errore lettura inventario", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String[][] getString(){
        String data[][] = new String[comp.size()][];
        AbstractComponent abs;
        for(int i = 0; i < comp.size(); i++){
            data[i] = new String[4];
            abs = comp.get(i);
            data[i][0] = String.valueOf(abs.getID());
            data[i][1] = abs.getType();
            data[i][2] = abs.getName();
            data[i][3] = String.valueOf(abs.getPrice()+"€");
        }
        return data;
    }

    public static void main(String[] args) {
        try {
            new CompList(new InserimentoSpecifiche(null, "FABBBBIO"));
        } catch (Exception e) {
            System.err.println("asd");
        }
    }
}
