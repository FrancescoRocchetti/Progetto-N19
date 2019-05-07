package Interface;

import InterfacingDB.DeprecatedClasses.LoginDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

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

        ActionListener accesso = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginDB logInDB;
                try {
                    logInDB = new LoginDB();
                    System.out.println(username.getText());
                    System.out.println(password.getPassword());
                    if(logInDB.login(username.getText(), String.valueOf(password.getPassword()))) {
                        //codice per la modifica del DB
                        GestioneComponenti gest = new GestioneComponenti(p,Login.this);
                        Login.super.setVisible(false);

                    } else {
                        System.err.println("Accesso non riuscito");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };

        accedi.addActionListener(accesso);
        password.addActionListener(accesso);

        annulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    dispose();
                    p.setVisible(true);
                }
            }
        });

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(250, 125);
        setVisible(true);
    }


}
