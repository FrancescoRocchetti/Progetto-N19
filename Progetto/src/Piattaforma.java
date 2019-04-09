import javax.swing.*;
import java.awt.*;

public class Piattaforma extends JFrame {
    Container c;
    Toolkit kit;
    Dimension dim;
    JTabbedPane components;
    JMenuBar menuBar;
    JMenu file;
    JMenu help;
    JMenuItem newConfig;
    JMenuItem guide;
    JPanel bckg;
    JPanel menuPanel;
    JPanel componentsPanel;
    JPanel mobo;
    JPanel cpu;
    JPanel ram;
    JPanel storage;
    JPanel videoCard;
    JPanel powerSupply;

    int x_disp;
    int y_disp;

    public Piattaforma() {
        super("Configuratore di PC");
        kit = Toolkit.getDefaultToolkit();
        dim = kit.getScreenSize();
        c = getContentPane();
        components = new JTabbedPane();

        bckg = new JPanel(new BorderLayout());
        menuPanel = new JPanel();
        componentsPanel = new JPanel();
        mobo = new JPanel();
        cpu = new JPanel();
        ram = new JPanel();
        storage = new JPanel();
        videoCard = new JPanel();
        powerSupply = new JPanel();

        menuBar = new JMenuBar();
        file = new JMenu("File");
        help = new JMenu("?");
        newConfig = new JMenuItem("New configuration");
        guide = new JMenuItem("Guide");
        file.add(newConfig);
        help.add(guide);
        menuBar.add(file);
        menuBar.add(help);
        menuPanel.add(menuBar);

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

        bckg.add(components, BorderLayout.CENTER);

        this.setJMenuBar(menuBar);
        c.add(bckg);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500,500);
        setResizable(false);
        this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
    }
}
