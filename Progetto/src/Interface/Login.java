package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Login extends JFrame {
    private Toolkit kit;
    private Dimension dim;
    private GestoreOperazioni go;
    private boolean set;

    public Login(Piattaforma p) {
        super("Login");
        set = false;
        go = new GestoreOperazioni(p);
        p.setVisible(true);
        Container c = getContentPane();
        kit = Toolkit.getDefaultToolkit();
        dim = kit.getScreenSize();
        JPanel background = new JPanel(new BorderLayout());
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JPanel formPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton access = new JButton("Accedi");
        JButton cancel = new JButton("Annulla");
        JLabel user = new JLabel("Username");
        JLabel psw = new JLabel("Password");
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        formPanel.add(username);
        formPanel.add(password);
        labelPanel.add(user);
        labelPanel.add(psw);
        buttonPanel.add(cancel);
        buttonPanel.add(access);
        background.add(buttonPanel, BorderLayout.SOUTH);
        background.add(labelPanel, BorderLayout.WEST);
        background.add(formPanel, BorderLayout.CENTER);
        background.setBorder(new EmptyBorder(5,5,5,5));
        c.add(background);

        ActionListener accesso = e -> {
            if (!go.accessToDB(username.getText(), String.valueOf(password.getPassword())))
                JOptionPane.showMessageDialog(null, "Errore connessione DB", "Errore", JOptionPane.ERROR_MESSAGE);
            else {
                set = true;
                dispose();
                p.setVisible(false);
            }
        };

        access.addActionListener(accesso);
        password.addActionListener(accesso);

        cancel.addActionListener(e -> {
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
                if (!set) go.unlockPlatform();
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
        //setSize(250, 125);
        pack();
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        setVisible(true);
    }

}
