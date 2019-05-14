package Interface;

import InterfacingDB.PCParts;
import InterfacingDB.DeprecatedClasses.Reading;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Piattaforma extends JFrame {

    private static final int CATEGORIES = 9;

    private ButtonGroup bg;
    private Container c;
    private Toolkit kit;
    private Dimension dim;
    private JTabbedPane components;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu help;
    private JMenu updateDB;
    private JMenuItem newConfig;
    private JMenuItem exit;
    private JMenuItem guide;
    private JMenuItem logAdmin;
    private JPanel bckg;
    private GestoreScelte gs;
    private double tot;

    private JPanel[] panels;
    private JScrollPane[] scrollPanes;

    private JPanel infoBox;
    private JPanel listItem;
    private JTextArea items;
    private JScrollPane scroll;
    private JTextField price;
    private JLabel total;
    private JPanel totPanel;
    private JPanel checkPane;
    private JTextArea checkMessage;

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
        newConfigListener();
        exit = new JMenuItem("Exit");
        logAdmin = new JMenuItem("Login");
        loginListener();
        guide = new JMenuItem("Guide");
        gs = new GestoreScelte();

        try {
            obtainParts(PCParts.MOBO, panels[0]);
            obtainParts(PCParts.CPU, panels[1]);
            obtainParts(PCParts.RAM, panels[2]);
            obtainParts(PCParts.STORAGE, panels[3]);
            obtainParts(PCParts.GPU, panels[4]);
            obtainParts(PCParts.PSU, panels[5]);
            obtainParts(PCParts.COOLER, panels[6]);
            obtainParts(PCParts.OS, panels[7]);
            obtainParts(PCParts.CASE, panels[8]);
        } catch (IOException e) {
            System.err.println("Errore nella lettura");
        }

        exit.addActionListener(e -> {
            System.exit(0);
        });

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

        this.setJMenuBar(menuBar);
        c.add(bckg);

        // Opzioni frame
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setResizable(false);
        this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
    }

    private void obtainParts(PCParts components, JPanel panel) throws IOException {
        int i = 0;
        String[] arr;
        Reading dati = new Reading();
        bg = new ButtonGroup();
        while ((arr = dati.read(components)) != null) {
            JRadioButton comp = new JRadioButton(arr[2] + " " + arr[3] + " :" + arr[4]);
            radioButtonListener(comp);
            bg.add(comp);
            panel.add(comp);
            i++;
        }
        panel.setLayout(new GridLayout(i, 1));
    }

    private void radioButtonListener(JRadioButton comp) {
        comp.addActionListener(e -> {
            gs.addForDisplay(comp);
            gs.displayOnPanel(items);
            tot += Double.parseDouble(comp.getText().split(" :")[1]);
            price.setText(String.valueOf(tot) + " €");
        });
    }

    private void loginListener() {
        logAdmin.addActionListener(e -> {
            Login l = new Login(Piattaforma.this);
            Piattaforma.super.setVisible(false);
            l.setLocationRelativeTo(null);
        });
    }

    private void newConfigListener() {
        newConfig.addActionListener(e -> {
            tot = 0;
            price.setText("0 €");
            items.setText("");
            gs.str.clear();
        });
    }
}
