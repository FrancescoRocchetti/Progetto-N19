package Interface;

import InterfacingDB.PCParts;
import InterfacingDB.DeprecatedClasses.Reading;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Piattaforma extends JFrame {

    protected static final int CATEGORIES = 9;

    protected ButtonGroup bg;
    protected Container c;
    protected Toolkit kit;
    protected Dimension dim;
    protected JTabbedPane components;
    protected JMenuBar menuBar;
    protected JMenu file;
    protected JMenu help;
    protected JMenu updateDB;
    protected JMenuItem newConfig;
    protected JMenuItem exit;
    protected JMenuItem guide;
    protected JMenuItem logAdmin;
    protected JPanel bckg;
    protected double tot;

    protected JPanel[] panels;
    protected JScrollPane[] scrollPanes;

    protected JPanel infoBox;
    protected JPanel listItem;
    protected JTextArea items;
    protected JScrollPane scroll;
    protected JTextField price;
    protected JLabel total;
    protected JPanel totPanel;
    protected JPanel checkPane;
    protected JTextArea checkMessage;

    public Piattaforma() {
        super("Configuratore di PC");
        kit = Toolkit.getDefaultToolkit();
        dim = kit.getScreenSize();

        // Inizializzazione
        c = getContentPane();
        components = new JTabbedPane();
        bckg = new JPanel(new BorderLayout());
        panels = new JPanel[CATEGORIES];
        scrollPanes = new JScrollPane[CATEGORIES];
        for (int i = 0; i < CATEGORIES; i++) {
            panels[i] = new JPanel();
            scrollPanes[i] = new JScrollPane(panels[i], JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPanes[i].getHorizontalScrollBar().setUnitIncrement(10);
            scrollPanes[i].getVerticalScrollBar().setUnitIncrement(10);
        }
        infoBox = new JPanel(new GridLayout(2, 1));
        listItem = new JPanel(new BorderLayout());
        items = new JTextArea();
        scroll = new JScrollPane(items, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.getHorizontalScrollBar().setUnitIncrement(10);
        totPanel = new JPanel(new GridLayout(1, 2));
        total = new JLabel("Totale:");
        price = new JTextField();
        price.setText("0 €");
        price.setEditable(false);
        price.setHorizontalAlignment(SwingConstants.RIGHT);
        listItem.setPreferredSize(new Dimension(300, getHeight() / 2));
        listItem.setBorder(BorderFactory.createLineBorder(Color.black));
        listItem.setBackground(Color.lightGray);
        items.setEditable(false);
        checkPane = new JPanel();
        checkMessage = new JTextArea();
        checkMessage.setEditable(false);
        checkPane.setPreferredSize(new Dimension(300, getHeight() / 2));
        checkPane.setBorder(BorderFactory.createLineBorder(Color.black));
        checkPane.setBackground(Color.LIGHT_GRAY);
        checkMessage.setBackground(Color.LIGHT_GRAY);
        checkMessage.setText("Compatibilità delle componenti");
        checkPane.add(checkMessage);
        menuBar = new JMenuBar();
        file = new JMenu("File");
        updateDB = new JMenu("Update DB");
        help = new JMenu("?");
        newConfig = new JMenuItem("New configuration");
        exit = new JMenuItem("Exit");
        logAdmin = new JMenuItem("Login");
        guide = new JMenuItem("Guide");

        // Aggiunta componenti
        file.add(newConfig);
        file.add(exit);
        updateDB.add(logAdmin);
        help.add(guide);
        menuBar.add(file);
        menuBar.add(updateDB);
        menuBar.add(help);

        components.addTab("Scheda madre", scrollPanes[0]);
        components.addTab("CPU", scrollPanes[1]);
        components.addTab("RAM", scrollPanes[2]);
        components.addTab("Storage", scrollPanes[3]);
        components.addTab("GPU", scrollPanes[4]);
        components.addTab("Power Supply", scrollPanes[5]);
        components.addTab("Cooler CPU", scrollPanes[6]);
        components.addTab("Operating System", scrollPanes[7]);
        components.addTab("Case", scrollPanes[8]);

        totPanel.add(total);
        totPanel.add(price);
        listItem.add(totPanel, BorderLayout.SOUTH);
        listItem.add(scroll, BorderLayout.CENTER);
        infoBox.add(listItem);
        infoBox.add(checkPane);

        bckg.add(infoBox, BorderLayout.EAST);
        bckg.add(components, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        c.add(bckg);

        // Opzioni frame
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setResizable(false);
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
    }
}
