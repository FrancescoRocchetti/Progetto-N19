package Interface;

import Gestione.GestoreOperazioni;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;


/**
 * Interfaccia usata per effettuare il login alla
 * pagina della'Admin
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 *
 */

public class Login extends JFrame {
    private GestoreOperazioni go;
    private Piattaforma p;
    private JPanel background;
    private JTextField username;
    private JPanel loginPanel;
    private JPanel loadingPanel;


    public Login(Piattaforma p) {
        super("Login");
        go = new GestoreOperazioni();
        this.p = p;
        p.setVisible(false);
        go.setLogin(this);
        Container c = getContentPane();
        background = new JPanel(new BorderLayout());
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
        background.add(loginPanel, BorderLayout.CENTER);
        background.setBorder(new EmptyBorder(5,5,5,5));
        c.add(background);

        ActionListener accesso = e -> {
            loading();
            String userStr = username.getText();
            String passwordStr = String.valueOf(password.getPassword());
            go.accessToDB(userStr, passwordStr);
        };

        access.addActionListener(accesso);
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
                    go.stopThreads();
                    p.setLocation(Login.super.getLocation());
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
        setLocation(p.getLocation());
        setVisible(true);
    }

    /**
     * Funzione richiamata da GestoreOperazioni che indica quando
     * la procedura di login ha avuto successo
     */
    public void successLogin(){
        InserimentoSpecifiche ins = new InserimentoSpecifiche(p, go, username.getText());
        ins.setLocation(this.getLocation());
        dispose();
    }

    /**
     * Funzione richiamata da GestoreOperazioni che indica quando
     * la procedura di login non ha avuto successo
     */
    public void failureLogin() {
        JOptionPane.showMessageDialog(this, "Errore connessione DB", "Errore", JOptionPane.ERROR_MESSAGE);
        background.remove(loadingPanel);
        background.add(loginPanel);
        background.repaint();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void loading(){
        background.remove(loginPanel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        URL url = getClass().getResource("Resources/loading.gif");
        ImageIcon img = new ImageIcon(url);
        loadingPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(img);
        JLabel txt = new JLabel("Sto effettuando l'accesso...");
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        loadingPanel.add(label, BorderLayout.CENTER);
        loadingPanel.add(txt, BorderLayout.SOUTH);
        background.add(loadingPanel, BorderLayout.CENTER);
        background.revalidate();
    }
}
