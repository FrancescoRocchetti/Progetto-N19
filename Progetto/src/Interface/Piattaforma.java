package Interface;

import InterfacingDB.PCParts;
import InterfacingDB.Reading;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Piattaforma extends JFrame {

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
    private JScrollPane mobo;
    private JPanel moboPanel;
    private JPanel cpu;
    private JPanel ram;
    private JPanel storage;
    private JPanel videoCard;
    private JPanel powerSupply;

    private JPanel infoBox;
    private JPanel listItem;
    private JTextArea items;
    private JScrollPane scroll;
    private JTextField price;
    private JLabel total;
    private JPanel totPanel;
    private JPanel imgPane;

    private ArrayList<JRadioButton> comp;
    private ArrayList<String> letture;
    private String[] elementoCorrente;

    private int nMobo = 0;

    public Piattaforma() {
        super("Configuratore di PC");
        kit = Toolkit.getDefaultToolkit();
        dim = kit.getScreenSize();

        // Inizializzazione
        c = getContentPane();
        components = new JTabbedPane();
        bckg = new JPanel(new BorderLayout());
        moboPanel = new JPanel();
        mobo = new JScrollPane(moboPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mobo.getVerticalScrollBar().setUnitIncrement(10);
        mobo.getHorizontalScrollBar().setUnitIncrement(10);
        cpu = new JPanel();
        ram = new JPanel();
        storage = new JPanel();
        videoCard = new JPanel();
        powerSupply = new JPanel();
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
        listItem.setPreferredSize(new Dimension(300, getHeight() / 2));
        listItem.setBorder(BorderFactory.createLineBorder(Color.black));
        listItem.setBackground(Color.lightGray);
        items.setText("Qui lista componenti");
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
        guide = new JMenuItem("Guide");

        /*Versione di quel brutto gay di Teo
        letture = new ArrayList<>(); // ArrayList di String
        letture = readFileComponents("Progetto/src/InterfacingDB/prova.txt");
        // test funzionamento lettura: OK
        for(String s : letture) {
            System.out.println(s);
        }
        // fine test

        elementoCorrente = new String[6];
        for (String componente : letture) {
            elementoCorrente = componente.split(";");
            if (elementoCorrente[1].equalsIgnoreCase("mobo")) {
                JRadioButton motherBoard = new JRadioButton(elementoCorrente[2] + " " + elementoCorrente[3] + " " + elementoCorrente[4]);
                comp.add(nMobo, motherBoard);
                bg.add(comp.get(nMobo));
                moboPanel.add(comp.get(nMobo));
                nMobo++;
            }
        }*/
        //La mia bella e prosperosa
        try {
            nMobo = obtainParts(PCParts.MOBO, moboPanel);
        } catch (IOException e) {
            System.err.println("Errore nella lettura");
        }


        moboPanel.setLayout(new GridLayout(nMobo, 1, 0, 0));

        // Aggiunta componenti
        file.add(newConfig);
        login.add(logAdmin);
        help.add(guide);
        menuBar.add(file);
        menuBar.add(login);
        menuBar.add(help);

        components.addTab("Scheda madre", mobo);
        components.addTab("CPU", cpu);
        components.addTab("RAM", ram);
        components.addTab("Storage", storage);
        components.addTab("Video card", videoCard);
        components.addTab("Power Supply", powerSupply);

        components.setEnabledAt(1, false);
        components.setBackgroundAt(1, Color.GRAY);
        components.setEnabledAt(2, false);
        components.setBackgroundAt(2, Color.GRAY);
        components.setEnabledAt(3, false);
        components.setBackgroundAt(3, Color.GRAY);
        components.setEnabledAt(4, false);
        components.setBackgroundAt(4, Color.GRAY);
        components.setEnabledAt(5, false);
        components.setBackgroundAt(5, Color.GRAY);

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

    private ArrayList<String> readFileComponents(String fileName) {
        ArrayList<String> dati = new ArrayList<>();
        String s;
        File file = new File(fileName);
        if(file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while((s = br.readLine()) != null)
                    dati.add(s);
                br.close();
            } catch (IOException e) {
                // doSomething
            }
        }
        return dati;
    }

    private int obtainParts(PCParts components, JPanel panel) throws IOException {
        int i = 0;
        String[] arr;
        Reading dati = new Reading();
        comp = new ArrayList<>(); // ArrayList di JRadioButton
        bg = new ButtonGroup();
        while((arr = dati.read(components)) != null){
            JRadioButton motherBoard = new JRadioButton(arr[2] + " " + arr[3] + " " + arr[4]);
            comp.add(i, motherBoard);
            bg.add(comp.get(i));
            panel.add(comp.get(i));
            i++;
        }
        return i;
    }
}
