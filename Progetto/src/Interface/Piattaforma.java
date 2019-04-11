package Interface;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Piattaforma extends JFrame {
    private Container c;
    private Toolkit kit;
    private Dimension dim;
    private JTabbedPane components;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu help;
    private JMenuItem newConfig;
    private JMenuItem guide;
    private JPanel bckg;
    private JScrollPane mobo;
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

    private JRadioButton mobo1;
    private JRadioButton mobo2;
    private ButtonGroup mobos;

    public Piattaforma() {
        super("Configuratore di PC");
        kit = Toolkit.getDefaultToolkit();
        dim = kit.getScreenSize();

        // Inizializzazione
        c = getContentPane();
        components = new JTabbedPane();
        bckg = new JPanel(new BorderLayout());
        mobo = new JScrollPane(new JPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cpu = new JPanel();
        ram = new JPanel();
        storage = new JPanel();
        videoCard = new JPanel();
        powerSupply = new JPanel();
        infoBox = new JPanel(new GridLayout(2,1));
        listItem = new JPanel(new BorderLayout());
        items = new JTextArea();
        scroll = new JScrollPane(items, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
        help = new JMenu("?");
        newConfig = new JMenuItem("New configuration");
        guide = new JMenuItem("Guide");

        mobo1 = new JRadioButton();
        mobo1.setText("Mobo 1");
        mobo2 = new JRadioButton();
        mobo2.setText("Mobo 2");
        mobos = new ButtonGroup();
        mobos.add(mobo1);
        mobos.add(mobo2);
        mobo.add(mobo1);
        mobo.add(mobo2);

        // Aggiunta componenti
        file.add(newConfig);
        help.add(guide);
        menuBar.add(file);
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
}
