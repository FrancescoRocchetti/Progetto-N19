package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class Loading extends JFrame {
    public Loading() {
        super("Caricamento...");
        Container c = getContentPane();
        URL url = getClass().getResource("Resources/loading.gif");
        ImageIcon img = new ImageIcon(url);
        JLabel label = new JLabel(img);
        JLabel txt = new JLabel("Sto scaricando i dati...", SwingConstants.CENTER);
        txt.setBorder(new EmptyBorder(0, 0, 30, 0));
        c.setLayout(new BorderLayout());
        c.add(label, BorderLayout.CENTER);
        c.add(txt, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*public static void main(String[] args) {
        new Loading();
    }*/
}
