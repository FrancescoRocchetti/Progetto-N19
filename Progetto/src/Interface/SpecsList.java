package Interface;

import Components.*;
import Gestione.GestoreScelte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

public class SpecsList extends JFrame{
    private static final String NOSUPPORT = "Not supported yet";

    private JButton okBtn;

    private GestoreScelte gs;

    private JPanel p;
    private JLabel nome;
    private JTextField name;
    private JLabel sock;
    private JTextField socket;
    private JLabel watt;
    private JTextField power;
    private AbstractComponent comp;

    public SpecsList(AbstractComponent comp, GestoreScelte gs, Piattaforma p) {
        super("Components Specs");
        this.comp = comp;
        this.gs = gs;
        p.setEnabled(false);
        p.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container c = getContentPane();
        JPanel bckg;
        JPanel compPanel;
        JPanel btnPanel;
        okBtn = new JButton("Ok");
        bckg = new JPanel(new BorderLayout());
        compPanel = buildPanel(PCParts.valueOf(comp.getType()));
        btnPanel = new JPanel(new GridLayout());
        btnPanel.add(okBtn);
        bckg.add(compPanel, BorderLayout.CENTER);
        bckg.add(btnPanel, BorderLayout.SOUTH);

        c.add(bckg);

        okBtn.addActionListener(e -> {
            dispose();
        });

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                p.setEnabled(true);
                p.toFront();
                p.requestFocus();
                p.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        pack();
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(p);
        setVisible(true);
    }

    /**
     * Costruisce il pannello in base al
     * componente che si vuole aggiungere
     * al DB
     *
     * @param part
     *
     * @return JPanel
     */
    private JPanel buildPanel(PCParts part) {
        switch(part){
            case CPU: {
                return panelCPU();
            }
            case MOBO: {
                return panelMOBO();
            }
            case GPU: {
                return panelGPU();
            }
            case COOLER: {
                return panelCooler();
            }
            case RAM: {
                return panelRAM();
            }
            case PSU: {
                return panelPSU();
            }
            case STORAGE: {
                return panelStorage();
            }
            case CASE: {
                return panelCASE();
            }
            case OS: {
                return panelOS();
            }
            case ALTRO: {
                return panelALTRO();
            }
            default: return null;
        }
    }

    private JPanel panelCPU() {
        CPU c = (CPU) comp;
        String[] car = c.getCaratteristiche();
        p = new JPanel(new GridLayout(10, 2));
        nome = new JLabel("Name:");
        name = createJTextField(c.getName());
        JLabel freq = new JLabel("Frequency (GHz):");
        JTextField frequency = createJTextField(car[1]);
        JLabel core = new JLabel("Core:");
        JTextField nCore = createJTextField(car[2]);
        JLabel thrd = new JLabel("Thread:");
        JTextField thread = createJTextField(car[3]);
        JLabel memtype = new JLabel("RAM type:");
        JTextField ram = createJTextField(car[4]);
        watt = new JLabel("TDP:");
        JTextField power = createJTextField(car[5]);
        JLabel bit = new JLabel("Bit:");
        JTextField nBit = createJTextField(car[6]);
        JLabel gpu = new JLabel("Integrated GPU:");
        JTextField hasGpu  = createJTextField(car[7]);
        sock = new JLabel("Socket:");
        socket = createJTextField(car[8]);
        JLabel cool = new JLabel("Cooler:");
        JTextField cooler = createJTextField(car[9]);

        Component[] cmp = {nome, name, freq, frequency, core, nCore, thrd, thread, memtype, ram, watt, power, bit, nBit, gpu, hasGpu, sock, socket, cool, cooler};
        hasGpu.setHorizontalAlignment(SwingConstants.RIGHT);
        cooler.setHorizontalAlignment(SwingConstants.RIGHT);
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelMOBO() {
        MOBO m = (MOBO) comp;
        String[] car = m.getCaratteristiche();
        p = new JPanel(new GridLayout(9, 2));
        nome = new JLabel("Name:");
        name = createJTextField(m.getName());
        sock = new JLabel("Socket:");
        socket = createJTextField(car[1]);
        JLabel banchi = new JLabel("Slot RAM:");
        JTextField nBanchi = createJTextField(car[2]);
        JLabel memtype = new JLabel("RAM type:");
        JTextField ram = createJTextField(car[3]);
        JLabel pciE16 = new JLabel("Slot PCIE16x:");
        JTextField nPci16 = createJTextField(car[4]);
        JLabel pciE = new JLabel("Slot PCIE:");
        JTextField nPciE = createJTextField(car[5]);
        JLabel dim = new JLabel("Dimension:");
        JTextField dimension = createJTextField(car[6]);
        JLabel sata = new JLabel("Slot SATA:");
        JTextField nSata = createJTextField(car[7]);
        watt = new JLabel("Power:");
        power = createJTextField(car[8]);

        Component[] cmp = {nome, name, sock, socket, banchi, nBanchi, memtype, ram, pciE16, nPci16, pciE, nPciE, dim, dimension, sata, nSata, watt, power};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelGPU(){
        GPU g = (GPU) comp;
        String[] car = g.getCaratteristiche();
        p = new JPanel(new GridLayout(3, 2));
        nome = new JLabel("Name:");
        name = createJTextField(g.getName());
        JLabel capacity = new JLabel("Capacità GB:");
        JTextField cap = createJTextField(car[1]);
        watt = new JLabel("TDP:");
        power = createJTextField(car[2]);

        Component[] cmp = {nome, name, capacity, cap, watt, power};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelCooler() {
        COOLER c = (COOLER) comp;
        String[] car = c.getCaratteristiche();
        p = new JPanel(new GridLayout(3,2));
        nome = new JLabel("Name:");
        name = createJTextField(c.getName());
        JLabel liquido = new JLabel("Liquid:");
        JTextField liquid = createJTextField(car[1]);
        sock = new JLabel("Socket :");
        String str = getStringFromArray(car[2].split(";"));
        socket = createJTextField(str);

        Component[] cmp = {nome, name, liquido, liquid, sock, socket};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelRAM() {
        RAM r = (RAM) comp;
        String[] car = r.getCaratteristiche();
        p = new JPanel(new GridLayout(6,2));
        nome = new JLabel("Nome:");
        name = createJTextField(r.getName());
        watt = new JLabel("Watt:");
        power = createJTextField(car[1]);
        JLabel memtype = new JLabel("RAM type:");
        JTextField ram = createJTextField(car[2]);
        JLabel dimensione = new JLabel("Dimension (GB):");
        JTextField dimension = createJTextField(car[3]);
        JLabel frequenza = new JLabel("Frequency (MHz):");
        JTextField frequency = createJTextField(car[4]);
        JLabel nModuli = new JLabel("# of modules:");
        JTextField modules = createJTextField(car[5]);

        Component[] cmp = {nome, name, watt, power, memtype, ram, dimensione, dimension, frequenza, frequency, nModuli, modules};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelPSU() {
        PSU psu = (PSU) comp;
        String[] car = psu.getCaratteristiche();
        p = new JPanel(new GridLayout(4,2));
        nome = new JLabel("Nome:");
        name = createJTextField(psu.getName());
        watt = new JLabel("Watt:");
        power = createJTextField(car[1]);
        JLabel dimensione = new JLabel("Dimensione:");
        JTextField dimension = createJTextField(car[2]);
        JLabel certificazione = new JLabel("Certification:");
        JTextField certification = createJTextField(car[3]);

        Component[] cmp = {nome, name, watt, power, dimensione, dimension, certificazione, certification};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelStorage() {
        STORAGE s = (STORAGE) comp;
        String[] car = s.getCaratteristiche();
        p = new JPanel(new GridLayout(4,2));
        nome = new JLabel("Nome:");
        name = createJTextField(s.getName());
        JLabel size = new JLabel("Size:");
        JTextField dim = createJTextField(car[1]);
        JLabel dimensione = new JLabel("Storage (GB):");
        JTextField storage = createJTextField(car[2]);
        watt = new JLabel("Watt:");
        power = createJTextField(car[3]);


        Component[] cmp = {nome, name, size, dim, dimensione, storage, watt, power};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelCASE() {
        CASE c = (CASE) comp;
        String[] car = c.getCaratteristiche();
        p = new JPanel(new GridLayout(4,2));
        nome = new JLabel("Nome:");
        name = createJTextField(c.getName());
        JLabel dimensione = new JLabel("Size:");
        JTextField dimension = createJTextField(car[1]);
        JLabel slot35 = new JLabel("# slot 3.5:");
        JTextField nSlot35 = createJTextField(car[2]);
        JLabel slot25 = new JLabel("# slot 2.5:");
        JTextField nSlot25 = createJTextField(car[3]);

        Component[] cmp = {nome, name, dimensione, dimension, slot35, nSlot35, slot25, nSlot25};

        addCmp(cmp,p);

        return p;
    }

    private JPanel panelOS() {
        OS o = (OS) comp;
        String[] car = o.getCaratteristiche();
        p = new JPanel(new GridLayout(2,2));
        nome = new JLabel("Nome:");
        name = createJTextField(o.getName());
        JLabel bit = new JLabel("Bit:");
        JTextField nBit = createJTextField(car[1]);

        Component[] cmp = {nome, name, bit, nBit};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelALTRO() {
        ALTRO a = (ALTRO) comp;
        String[] car = a.getCaratteristiche();
        p = new JPanel(new GridLayout(2,2));
        nome = new JLabel("Nome:");
        name = createJTextField(a.getName());
        JLabel descrizione = new JLabel("Description:");
        JTextField description = createJTextField(car[1]);

        Component[] cmp = {nome, name, descrizione, description};
        addCmp(cmp, p);
        return p;
    }

    private void addCmp(Component[] arr, JPanel pan) {
        for(Component c : arr)
            pan.add(c);
    }

    private JTextField createJTextField(String s){
        JTextField txt = new JTextField(s);
        txt.setEditable(false);
        return txt;
    }

    private String getStringFromArray(String[] scks) {
        StringBuilder str = new StringBuilder();
        for (String s : scks)
            str.append(s+" ");
        return str.toString();
    }

}
