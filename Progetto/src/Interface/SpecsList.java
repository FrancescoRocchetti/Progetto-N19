package Interface;

import Components.*;
import Gestione.GestoreScelte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
        super(comp.getName());
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
        p = new JPanel(new GridLayout(10, 2));
        nome = new JLabel("Name:");
        name = createJTextField(c.getName());
        JLabel freq = new JLabel("Frequency (GHz):");
        JTextField frequency = createJTextField(NOSUPPORT);
        JLabel core = new JLabel("Core:");
        JTextField nCore = createJTextField(NOSUPPORT);
        JLabel thrd = new JLabel("Thread:");
        JTextField thread = createJTextField(NOSUPPORT);
        JLabel memtype = new JLabel("RAM type:");
        JTextField ram = createJTextField(c.getTypeRAM());
        watt = new JLabel("TDP:");
        int pw = c.getResource().getPower();
        JTextField power = createJTextField(String.valueOf(pw));
        JLabel bit = new JLabel("Bit:");
        int b = c.getResource().getnBit();
        JTextField nBit = createJTextField(String.valueOf(b));
        JLabel gpu = new JLabel("Integrated GPU:");
        JTextField hasGpu  = createJTextField(NOSUPPORT);
        sock = new JLabel("Socket:");
        socket = createJTextField(c.getSocket());
        JLabel cool = new JLabel("Cooler:");
        JTextField cooler = createJTextField(NOSUPPORT);

        Component[] cmp = {nome, name, freq, frequency, core, nCore, thrd, thread, memtype, ram, watt, power, bit, nBit, gpu, hasGpu, sock, socket, cool, cooler};
        hasGpu.setHorizontalAlignment(SwingConstants.RIGHT);
        cooler.setHorizontalAlignment(SwingConstants.RIGHT);
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelMOBO() {
        MOBO m = (MOBO) comp;
        p = new JPanel(new GridLayout(9, 2));
        nome = new JLabel("Name:");
        name = createJTextField(m.getName());
        sock = new JLabel("Socket:");
        socket = createJTextField(m.getSocket());
        JLabel banchi = new JLabel("Slot RAM:");
        int b = m.getResource().getModulesRAM();
        JTextField nBanchi = createJTextField(String.valueOf(b));
        JLabel memtype = new JLabel("RAM type:");
        JTextField ram = createJTextField(m.getTypeRAM());
        JLabel pciE16 = new JLabel("Slot PCIE16x:");
        int np16 = m.getResource().getnPcie();
        JTextField nPci16 = createJTextField(String.valueOf(np16));
        JLabel pciE = new JLabel("Slot PCIE:");
        int np = m.getResource().getnPci();
        JTextField nPciE = createJTextField(String.valueOf(np));
        JLabel dim = new JLabel("Dimension:");
        String dimMobo = m.getResource().getDimensionMOBO();
        JTextField dimension = createJTextField(dimMobo);
        JLabel sata = new JLabel("Slot SATA:");
        int ns = m.getResource().getnSATA();
        JTextField nSata = createJTextField(String.valueOf(ns));
        watt = new JLabel("Power:");
        int pw = m.getResource().getPower();
        power = createJTextField(String.valueOf(pw));

        Component[] cmp = {nome, name, sock, socket, banchi, nBanchi, memtype, ram, pciE16, nPci16, pciE, nPciE, dim, dimension, sata, nSata, watt, power};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelGPU(){
        GPU g = (GPU) comp;
        p = new JPanel(new GridLayout(3, 2));
        nome = new JLabel("Name:");
        name = createJTextField(g.getName());
        JLabel capacity = new JLabel("Capacità GB:");
        JTextField cap = createJTextField(NOSUPPORT);
        watt = new JLabel("TDP:");
        int pw = g.getResource().getPower();
        power = createJTextField(String.valueOf(pw));

        Component[] cmp = {nome, name, capacity, cap, watt, power};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelCooler() {
        COOLER c = (COOLER) comp;
        p = new JPanel(new GridLayout(3,2));
        nome = new JLabel("Name:");
        name = createJTextField(c.getName());
        JLabel liquido = new JLabel("Liquid:");
        JTextField liquid = createJTextField(NOSUPPORT);
        sock = new JLabel("Socket :");
        String[] scks = c.getResource().getSupportedSocketC();
        String str = getStringFromArray(scks);
        socket = createJTextField(str);

        Component[] cmp = {nome, name, liquido, liquid, sock, socket};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelRAM() {
        RAM r = (RAM) comp;
        p = new JPanel(new GridLayout(6,2));
        nome = new JLabel("Nome:");
        name = createJTextField(r.getName());
        watt = new JLabel("Watt:");
        int pw = r.getResource().getPower();
        power = createJTextField(String.valueOf(pw));
        JLabel memtype = new JLabel("RAM type:");
        JTextField ram = createJTextField(r.getTypeRAM());
        JLabel dimensione = new JLabel("Dimension (GB):");
        int dm = r.getResource().getAmountRAM();
        JTextField dimension = createJTextField(String.valueOf(dm));
        JLabel frequenza = new JLabel("Frequency (MHz):");
        JTextField frequency = createJTextField(NOSUPPORT);
        JLabel nModuli = new JLabel("# of modules:");
        int mod = r.getResource().getModulesRAM();
        JTextField modules = createJTextField(String.valueOf(mod));

        Component[] cmp = {nome, name, watt, power, memtype, ram, dimensione, dimension, frequenza, frequency, nModuli, modules};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelPSU() {
        PSU psu = (PSU) comp;
        p = new JPanel(new GridLayout(4,2));
        nome = new JLabel("Nome:");
        name = new JTextField(psu.getName());
        watt = new JLabel("Watt:");
        int pw = psu.getResource().getPower();
        power = createJTextField(String.valueOf(pw));
        JLabel dimensione = new JLabel("Dimensione:");
        JTextField dimension = createJTextField(NOSUPPORT);
        JLabel certificazione = new JLabel("Certification:");
        JTextField certification = createJTextField(NOSUPPORT);

        Component[] cmp = {nome, name, watt, power, dimensione, dimension, certificazione, certification};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelStorage() {
        STORAGE s = (STORAGE) comp;
        p = new JPanel(new GridLayout(4,2));
        nome = new JLabel("Nome:");
        name = new JTextField();
        JLabel size = new JLabel("Size:");
        JTextField dim = createJTextField(NOSUPPORT);
        JLabel dimensione = new JLabel("Storage (GB):");
        JTextField storage = createJTextField(NOSUPPORT);
        int pw = s.getResource().getPower();
        watt = new JLabel("Watt:");
        power = createJTextField(String.valueOf(pw));

        Component[] cmp = {nome, name, size, dim, dimensione, storage, watt, power};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelCASE() {
        CASE c = (CASE) comp;
        p = new JPanel(new GridLayout(4,2));
        nome = new JLabel("Nome:");
        name = createJTextField(c.getName());
        JLabel dimensione = new JLabel("Size:");
        String d = c.getCaseDim();
        JTextField dimension = createJTextField(d);
        JLabel slot35 = new JLabel("# slot 3.5:");
        int ns35 = c.getResource().getnSlot325();
        JTextField nSlot35 = createJTextField(String.valueOf(ns35));
        JLabel slot25 = new JLabel("# slot 2.5:");
        JTextField nSlot25 = createJTextField(NOSUPPORT);

        Component[] cmp = {nome, name, dimensione, dimension, slot35, nSlot35, slot25, nSlot25};

        addCmp(cmp,p);

        return p;
    }

    private JPanel panelOS() {
        OS o = (OS) comp;
        p = new JPanel(new GridLayout(2,2));
        nome = new JLabel("Nome:");
        name = createJTextField(o.getName());
        JLabel bit = new JLabel("Bit:");
        JTextField nBit = createJTextField(String.valueOf(o.getBit()));

        Component[] cmp = {nome, name, bit, nBit};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelALTRO() {
        ALTRO a = (ALTRO) comp;
        p = new JPanel(new GridLayout(2,2));
        nome = new JLabel("Nome:");
        name = createJTextField(a.getName());
        JLabel descrizione = new JLabel("Description:");
        JTextField description = createJTextField(NOSUPPORT);

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
