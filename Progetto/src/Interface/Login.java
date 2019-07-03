package Interface;

import Gestione.GestoreOperazioni;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/**
 * Interfaccia usata per effettuare il login alla
 * pagina della'Admin
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 */

public class Login extends AbstractInterface {
    private Piattaforma p;
    private JTextField username;
    private JPanel loginPanel;
    private GestoreOperazioni go;


    public Login(Piattaforma p, GestoreOperazioni go) {
        super("Login");
        this.p = p;
        this.go = go;
        p.setVisible(false);
        go.setLogin(this);
        Container c = getContentPane();
        bckg = new JPanel(new BorderLayout());
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JPanel formPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton access = new JButton("Accedi");
        JButton cancel = new JButton("Annulla");
        JLabel user = new JLabel("Username");
        JLabel psw = new JLabel("Password");
        username = new JTextField();
        loginPanel = new JPanel(new BorderLayout());
        JPasswordField password = new JPasswordField();
        formPanel.add(username);
        formPanel.add(password);
        labelPanel.add(user);
        labelPanel.add(psw);
        buttonPanel.add(cancel);
        buttonPanel.add(access);
        loginPanel.add(buttonPanel, BorderLayout.SOUTH);
        loginPanel.add(labelPanel, BorderLayout.WEST);
        loginPanel.add(formPanel, BorderLayout.CENTER);
        bckg.add(loginPanel, BorderLayout.CENTER);
        bckg.setBorder(new EmptyBorder(5, 5, 5, 5));
        c.add(bckg);

        ActionListener accesso = e -> {
            loading();
            String userStr = username.getText();
            String passwordStr = String.valueOf(password.getPassword());
            go.accessToDB(userStr, passwordStr);
        };

        access.addActionListener(accesso);
        username.addActionListener(accesso);
        password.addActionListener(accesso);

        cancel.addActionListener(e -> dispose());

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (!go.isLoggedIn()) {
                    p.setLocationRelativeTo(Login.this);
                    p.setVisible(true);
                    p.toFront();
                    p.requestFocus();
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
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Funzione richiamata da GestoreOperazioni che indica quando
     * la procedura di login ha avuto successo
     */
    public void successLogin() {
        new InserimentoSpecifiche(p, go, username.getText());
        dispose();
    }

    /**
     * Funzione richiamata da GestoreOperazioni che indica quando
     * la procedura di login non ha avuto successo
     */
    public void failureLogin() {
        JOptionPane.showMessageDialog(this, "Errore connessione DB", "Errore", JOptionPane.ERROR_MESSAGE);
        bckg.remove(loadingPanel);
        bckg.add(loginPanel);
        bckg.repaint();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void loading() {
        bckg.remove(loginPanel);
        super.loading("Sto effettuando l'accesso...");
    }
}
