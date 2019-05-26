package Interface;

import Interface.DeprecatedClasses.GestioneComponenti;
import InterfacingDB.LoginDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;


public class Login extends JFrame {
    private Toolkit kit;
    private Dimension dim;
    private boolean loggedin;

    public Login(Piattaforma p) {
        super("Login");
        loggedin = false;
        p.setEnabled(false);
        p.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container c = getContentPane();
        kit = Toolkit.getDefaultToolkit();
        dim = kit.getScreenSize();
        JPanel background = new JPanel(new BorderLayout());
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JPanel formPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton accedi = new JButton("Accedi");
        JButton annulla = new JButton("Annulla");
        JLabel user = new JLabel("Username");
        JLabel psw = new JLabel("Password");
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        formPanel.add(username);
        formPanel.add(password);
        labelPanel.add(user);
        labelPanel.add(psw);
        buttonPanel.add(annulla);
        buttonPanel.add(accedi);
        background.add(buttonPanel, BorderLayout.SOUTH);
        background.add(labelPanel, BorderLayout.WEST);
        background.add(formPanel, BorderLayout.CENTER);
        c.add(background);

        ActionListener accesso = e -> {
            LoginDB logInDB;
            try {
                logInDB = new LoginDB();
                if(logInDB.login(username.getText(), String.valueOf(password.getPassword()))) {
                    //codice per la modifica del DB
                    //GestioneComponenti gest = new GestioneComponenti(p,Login.this);
                    //System.out.println("Username: " + username.getText());
                    //System.out.println("Password: " + password.getPassword());
                    InserimentoSpecifiche ins = new InserimentoSpecifiche(p, username.getText());
                    loggedin = true;
                    dispose();
                } else {
                    System.err.println("Accesso non riuscito");
                }
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "Errore connessione DB", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        };

        accedi.addActionListener(accesso);
        password.addActionListener(accesso);

        annulla.addActionListener(e -> {
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
                if(!loggedin){
                    p.setEnabled(true);
                    p.setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
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

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(250, 125);
        setVisible(true);
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
    }

}
