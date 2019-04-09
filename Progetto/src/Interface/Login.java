package Progetto.src.Interface;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame { //danese
    public Login() {
        super("Progetto.src.Interface.Login");
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

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(250, 125);
    }
}
