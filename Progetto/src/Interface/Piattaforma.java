package Interface;

import InterfacingDB.PCParts;
import InterfacingDB.Reading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

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
    private JMenu login;
    private JMenuItem newConfig;
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
    private JPanel imgPane;

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
        for(int i = 0; i < CATEGORIES; i++) {
            panels[i] = new JPanel();
            scrollPanes[i] = new JScrollPane(panels[i], JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPanes[i].getHorizontalScrollBar().setUnitIncrement(10);
            scrollPanes[i].getVerticalScrollBar().setUnitIncrement(10);
        }
        infoBox = new JPanel(new GridLayout(2,1));
        listItem = new JPanel(new BorderLayout());
        items = new JTextArea();
        scroll = new JScrollPane(items, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.getHorizontalScrollBar().setUnitIncrement(10);
        totPanel = new JPanel(new GridLayout(1, 2));
        total = new JLabel("Totale:");
        price = new JTextField();
        price.setEditable(false);
        price.setHorizontalAlignment(SwingConstants.RIGHT);
        listItem.setPreferredSize(new Dimension(300, getHeight() / 2));
        listItem.setBorder(BorderFactory.createLineBorder(Color.black));
        listItem.setBackground(Color.lightGray);
        //items.setText("Qui lista componenti");
        items.setEditable(false);
        imgPane = new JPanel();
        imgPane.setPreferredSize(new Dimension(300, getHeight() / 2));
        imgPane.setBorder(BorderFactory.createLineBorder(Color.black));
        imgPane.setBackground(Color.lightGray);
        imgPane.add(new JLabel("Foto componente scelto"));
        menuBar = new JMenuBar();
        file = new JMenu("File");
        login = new JMenu("Login");
        help = new JMenu("?");
        newConfig = new JMenuItem("New configuration");
        logAdmin = new JMenuItem("Admin privileges");
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
        } catch (IOException e) {
            System.err.println("Errore nella lettura");
        }

        // Aggiunta componenti
        file.add(newConfig);
        login.add(logAdmin);
        help.add(guide);
        menuBar.add(file);
        menuBar.add(login);
        menuBar.add(help);

        components.addTab("Scheda madre", scrollPanes[0]);
        components.addTab("CPU", scrollPanes[1]);
        components.addTab("RAM", scrollPanes[2]);
        components.addTab("Storage", scrollPanes[3]);
        components.addTab("Video card", scrollPanes[4]);
        components.addTab("Power Supply", scrollPanes[5]);

        /*
        components.setEnabledAt(1, true);
        components.setBackgroundAt(1, Color.GRAY);
        components.setEnabledAt(2, true);
        components.setBackgroundAt(2, Color.GRAY);
        components.setEnabledAt(3, true);
        components.setBackgroundAt(3, Color.GRAY);
        components.setEnabledAt(4, true);
        components.setBackgroundAt(4, Color.GRAY);
        components.setEnabledAt(5, true);
        components.setBackgroundAt(5, Color.GRAY);
        */

        totPanel.add(total);
        totPanel.add(price);
        listItem.add(totPanel, BorderLayout.SOUTH);
        listItem.add(scroll, BorderLayout.CENTER);
        infoBox.add(listItem);
        infoBox.add(imgPane);

        bckg.add(infoBox, BorderLayout.EAST);
        bckg.add(components, BorderLayout.CENTER);

        this.setJMenuBar(menuBar);
        c.add(bckg);

        // Opzioni frame
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000,500);
        setResizable(false);
        this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
    }

    private void obtainParts(PCParts components, JPanel panel) throws IOException {
        int i = 0;
        String[] arr;
        Reading dati = new Reading();
        bg = new ButtonGroup();
        while((arr = dati.read(components)) != null){
            JRadioButton comp = new JRadioButton(arr[2] + " " + arr[3] + " :" + arr[4]);
            newActionListener(comp);
            bg.add(comp);
            panel.add(comp);
            i++;
        }
        panel.setLayout(new GridLayout(i, 1, 0, 0));
    }

    private void newActionListener(JRadioButton comp) {
        comp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gs.addForDisplay(comp);
                gs.displayOnPanel(items);
                tot += Double.parseDouble(comp.getText().split(" :")[1]);
                price.setText(String.valueOf(tot) + " €");
            }
        });
    }

    private void loginListener() {
        logAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login l = new Login(Piattaforma.this);
                Piattaforma.super.setVisible(false);
                l.setLocationRelativeTo(null);
            }
        });
    }


}
