package Interface;

import InterfacingDB.PCParts;
import InterfacingDB.Reading;

import javax.swing.*;
import java.awt.*;
import Components.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Piattaforma extends JFrame {

    private static final int CATEGORIES = 9;
    private static final PCParts[] CMP = new PCParts[]{PCParts.MOBO, PCParts.CPU, PCParts.RAM, PCParts.STORAGE, PCParts.GPU, PCParts.PSU, PCParts.COOLER, PCParts.OS, PCParts.CASE};

    protected ButtonGroup[] bg;
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
    protected JMenuItem recharge;
    protected JPanel bckg;

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

    private int row;
    private int nr;
    private GestoreScelte gs;

    public Piattaforma() {
        super("Configuratore di PC");
        gs = new GestoreScelte();
        //pnl = new JPanel[]{panels[0], panels[1], panels[2], panels[3], panels[4], panels[5], panels[6], panels[7], panels[8]};

        nr = 0;
        row = 100;
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
        updateDB = new JMenu("Connection");
        help = new JMenu("?");
        newConfig = new JMenuItem("New configuration");
        exit = new JMenuItem("Exit");
        logAdmin = new JMenuItem("Login");
        recharge = new JMenuItem("Try new connection");
        guide = new JMenuItem("Guide");

        // Aggiunta componenti
        file.add(newConfig);
        file.add(exit);
        updateDB.add(logAdmin);
        updateDB.add(recharge);
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

        loginListener();
        newConfigListener();
        rechargeListener();
        exitListener();
        obtainParts();

        // Opzioni frame
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setResizable(false);
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
    }


    private void addComp(CompRadio button) {
        gs.addComp(button.getAbs());
    }

    private void displayOnPanel(JTextArea textArea) {
        textArea.setText(gs.getListAbs());
    }

    private void obtainParts(){
        try{
            bg = new ButtonGroup[CMP.length];
            CompRadio comp;
            ArrayList<AbstractComponent> arr;

            for(int z = 0; z < CMP.length; z++) {
                    bg[z] = new ButtonGroup();
                    arr = gs.obtainParts(CMP[z]);
                for(AbstractComponent x : arr) {
                    comp = new CompRadio(x.getType() + " " + x.getName() + " - " + x.getPrice()+" €",x);
                    radioButtonListener(comp);
                    bg[z].add(comp);
                    panels[z].add(comp);
                    nr++;
                    if(row <= nr)
                        row++;
                }
                panels[z].setLayout(new GridLayout(row, 1));
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore: impossibile connettersi al DB.\nIl programma terminerà la sua esecuzione.", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(10);
        }
    }

    private void radioButtonListener(CompRadio comp) {
        comp.addActionListener(e -> {
            if (!gs.isAlreadyIn(comp.getAbs())){
                addComp(comp);
                displayOnPanel(items);
                price.setText(gs.getPrice() + " €");
            } else {
                JOptionPane.showMessageDialog(null, comp.getAbs().getName() + " già presente", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void loginListener() {
        logAdmin.addActionListener(e -> {
            Login l = new Login(this);
            setVisible(false);
            l.setLocationRelativeTo(null);
        });
    }

    private void newConfigListener() {
        newConfig.addActionListener(e -> {
            price.setText("0 €");
            items.setText("");
            gs.newScp();
        });
    }

    private void rechargeListener() {
        recharge.addActionListener(e -> {
            for (JPanel p : panels)
                p.removeAll();
            obtainParts();
        });
    }

    private void exitListener() {
        exit.addActionListener(e -> System.exit(0));
    }
}

