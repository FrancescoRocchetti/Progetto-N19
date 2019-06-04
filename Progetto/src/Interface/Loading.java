package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Loading extends JFrame {
    public Loading() {
        super("Caricamento...");
        Container c = getContentPane();
        URL url = getClass().getResource("Resources/loading3.gif");
        ImageIcon img = new ImageIcon(url);
        Image image = img.getImage();
        Image newImage = image.getScaledInstance(150,150, Image.SCALE_DEFAULT);
        img = new ImageIcon(newImage);
        JLabel label = new JLabel(img);
        JLabel txt = new JLabel();
        String[] loadingStrings = {"Sto contando le stelle...", "Sto allineando i pianeti...", "Ti sto guardando...", "Sto svuotando le nuvole..."};
        Random random = new Random();
        int ran = random.nextInt(loadingStrings.length);
        System.out.println(ran);
        txt.setText(loadingStrings[ran]);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
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
}
