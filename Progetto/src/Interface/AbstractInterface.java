package Interface;

import Components.AbstractComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Classe usata per essere estesa dalle JFrame
 * e per contenere degli eventuali metodi duplicati
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 */
public abstract class AbstractInterface extends JFrame {

    JPanel loadingPanel;
    JPanel bckg;

    public AbstractInterface(String str) {
        super(str);
        loadingPanel = new JPanel();
        bckg = new JPanel();
    }


    /**
     * Sostituisce il contenuto del pannello bckg con
     * una schermata di caricamento
     *
     * @param str
     */
    protected void loading(String str) {
        URL url = getClass().getResource("Resources/loading.gif");
        ImageIcon img = new ImageIcon(url);
        loadingPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(img);
        JLabel txt = new JLabel(str);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setBorder(new EmptyBorder(0, 0, 30, 0));
        loadingPanel.add(label, BorderLayout.CENTER);
        loadingPanel.add(txt, BorderLayout.SOUTH);
        bckg.add(loadingPanel, BorderLayout.CENTER);
        bckg.revalidate();
    }

    /**
     * Permette di ottenere una matrice di Object
     * a partire da una lista di AbstractComponent
     *
     * @param comp
     * @return Object[][]
     */
    protected Object[][] getObjectFromComps(ArrayList<AbstractComponent> comp) {
        Object[][] data = new Object[comp.size()][];
        AbstractComponent abs;
        for (int i = 0; i < comp.size(); i++) {
            data[i] = new Object[5];
            abs = comp.get(i);
            data[i][0] = abs.getID();
            data[i][1] = abs.getType();
            data[i][2] = abs.getName();
            data[i][3] = abs.getQuantity();
            data[i][4] = abs.getPrice() + " €";
        }
        return data;
    }
}
