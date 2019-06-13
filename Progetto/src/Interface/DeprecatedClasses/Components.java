package Interface.DeprecatedClasses;

import Gestione.GestoreOperazioni;
import Interface.InserimentoSpecifiche;
import InterfacingDB.PCParts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Components extends JFrame {

    private final static int NCOMP = 10;
    private final static String[] SCOMP = {"CASE", "COOLER", "CPU", "GPU", "MOBO", "OS", "PSU", "RAM", "STORAGE", "ALTRO"};
    private GestoreOperazioni go;
    private JComboBox comp;
    private JComboBox qta;

    public Components(InserimentoSpecifiche ins, GestoreOperazioni go) {
        super("Remove component");
        this.go = go;
        ins.setEnabled(false);
        ins.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container c = getContentPane();
        JPanel bckg = new JPanel(new BorderLayout());
        JPanel mainchoose = new JPanel(new BorderLayout());
        JPanel gridpanel = new JPanel(new GridLayout(3, 3));
        JPanel btnpanel = new JPanel(new BorderLayout());
        JPanel choosepanel = new JPanel(new GridLayout(0, 1));
        JPanel btnchoose = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton[] btnarray = new JButton[NCOMP];
        for (int i = 0; i < NCOMP - 1; i++) {
            btnarray[i] = new JButton(SCOMP[i]);
            gridpanel.add(btnarray[i]);
            //addActionListenerBtnComp(btnarray[i]);
        }
        btnarray[NCOMP - 1] = new JButton(SCOMP[NCOMP - 1]);
        btnpanel.add(gridpanel, BorderLayout.CENTER);
        btnpanel.add(btnarray[NCOMP - 1], BorderLayout.SOUTH);
        JButton rmv = new JButton("Remove");
        JButton back = new JButton("Back");
        rmv.setEnabled(false);
        btnchoose.add(rmv);
        btnchoose.add(back);
        comp = new JComboBox();
        qta = new JComboBox();
        choosepanel.add(comp);
        choosepanel.add(qta);
        mainchoose.add(choosepanel, BorderLayout.CENTER);
        mainchoose.add(btnchoose, BorderLayout.SOUTH);
        bckg.add(btnpanel, BorderLayout.CENTER);
        bckg.add(mainchoose, BorderLayout.SOUTH);
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
                ins.setEnabled(true);
                ins.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /*private void addActionListenerBtnComp(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comp.removeAllItems();
                String[][] data = go.getString(PCParts.valueOf(btn.getText()));
                for (int i = 0; i < data.length; i++)
                    comp.addItem(data[i]);
            }
        });
    }*/

}
