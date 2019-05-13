package Interface;

import Interface.DeprecatedClasses.GestioneComponenti;
import InterfacingDB.LoginDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class Login extends JFrame {
    public Login(Piattaforma p) {
        super("Login");
        Container c = getContentPane();
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
                    System.out.println("Username: " + username.getText());
                    System.out.println("Password: " + password.getPassword());
                    InserimentoSpecifiche ins = new InserimentoSpecifiche(p, username.getText());
                    Login.super.setVisible(false);
                } else {
                    System.err.println("Accesso non riuscito");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        };

        accedi.addActionListener(accesso);
        password.addActionListener(accesso);

        annulla.addActionListener(e -> {
            dispose();
            p.setVisible(true);
        });

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(250, 125);
        setVisible(true);
    }


}
