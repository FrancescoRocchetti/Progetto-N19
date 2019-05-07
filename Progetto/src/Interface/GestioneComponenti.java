package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GestioneComponenti extends JFrame {
    private Container c;
    private JPanel buttonsPanel, background;
    private JButton logout;
    private JLabel label;
    private String[] componentsName;
    public GestioneComponenti(Piattaforma p, Login login) {
        super("Gestione componenti");
        componentsName = new String[]{"Case", "Cooler CPU", "CPU", "GPU", "MOBO", "OS", "PSU", "RAM", "Storage", "Other"};
        background = new JPanel(new BorderLayout());
        logout = new JButton("Logout");
        label = new JLabel("Choose the component to update...");
        logout.setBackground(Color.RED);
        logout.setForeground(Color.WHITE);
        c = getContentPane();
        buttonsPanel = new JPanel(new GridLayout(5,2));
        addButtonsToPanel(buttonsPanel);
        background.add(label, BorderLayout.NORTH);
        background.add(buttonsPanel, BorderLayout.CENTER);
        background.add(logout, BorderLayout.SOUTH);
        c.add(background);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) { }

            @Override
            public void windowClosed(WindowEvent e) {
                p.setVisible(true);
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

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                p.setEnabled(true);
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
