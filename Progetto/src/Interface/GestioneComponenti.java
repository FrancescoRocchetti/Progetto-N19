package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GestioneComponenti extends JFrame {
    private Container c;
    private JPanel buttonsPanel;
    private String[] componentsName;
    public GestioneComponenti(Piattaforma p, Login login) {
        super("Gestione componenti");
        componentsName = new String[]{"Case", "Cooler CPU", "CPU", "GPU", "MOBO", "OS", "PSU", "RAM", "Storage", "Other"};
        c = getContentPane();
        buttonsPanel = new JPanel(new GridLayout(2,5));
        addButtonsToPanel(buttonsPanel);
        c.add(buttonsPanel);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                p.setVisible(true);
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

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void addButtonsToPanel(JPanel p) {
        for(int i = 0; i < componentsName.length; i++) {
            p.add(new JButton(componentsName[i]));
        }
    }
}
