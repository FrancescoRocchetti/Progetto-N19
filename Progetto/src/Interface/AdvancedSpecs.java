package Interface;

import Gestione.GestoreOperazioni;
import InterfacingDB.PCParts;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;

public class AdvancedSpecs extends JFrame {
    private JButton okBtn;
    private JButton cancBtn;
    private JPanel bckg;
    private JPanel compPanel;
    private JPanel btnPanel;
    private GestoreOperazioni go;

    private JPanel p;
    private JLabel nome;
    private JTextField name;
    private JLabel sock;
    private JTextField socket;
    private JLabel watt;
    private JSpinner power;
    private String s;

    public AdvancedSpecs(PCParts part) {
        super(part.name());
        Container c = getContentPane();
        okBtn = new JButton("Conferma");
        cancBtn = new JButton("Annulla");
        bckg = new JPanel(new BorderLayout());
        compPanel = buildPanel(part);
        btnPanel = new JPanel(new GridLayout(1,2));
        btnPanel.add(cancBtn);
        btnPanel.add(okBtn);
        bckg.add(compPanel, BorderLayout.CENTER);
        bckg.add(btnPanel, BorderLayout.SOUTH);

        c.add(bckg);

        pack();
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
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
        p = new JPanel(new GridLayout(10, 2));
        nome = new JLabel("Name:");
        name = new JTextField();
        JLabel freq = new JLabel("Frequency (GHz):");
        DoubleSpinner frequency = new DoubleSpinner(3,1, 6);
        setSpinnerNotWritable(frequency);
        JLabel core = new JLabel("Core:");
        SpinnerNumberModel spinnerCoreModel = new SpinnerNumberModel(1, 1, 18, 1);
        JSpinner nCore = new JSpinner(spinnerCoreModel);
        setSpinnerNotWritable(nCore);
        JLabel thrd = new JLabel("Thread:");
        SpinnerNumberModel spinnerThreadModel = new SpinnerNumberModel(1, 1, 36, 1);
        JSpinner thread = new JSpinner(spinnerThreadModel);
        setSpinnerNotWritable(thread);
        JLabel memtype = new JLabel("RAM type:");
        JComboBox ram = new JComboBox();
        ram.addItem("DDR2");
        ram.addItem("DDR3");
        ram.addItem("DDR4");
        watt = new JLabel("TDP:");
        SpinnerNumberModel spinnerPowerModel = new SpinnerNumberModel(1,1,300,1);
        power = new JSpinner(spinnerPowerModel);
        setSpinnerNotWritable(power);
        JLabel bit = new JLabel("Bit:");
        JComboBox nBit = new JComboBox();
        nBit.addItem("32");
        nBit.addItem("64");
        JLabel gpu = new JLabel("Integrated GPU:");
        JCheckBox hasGpu  =new JCheckBox();
        sock = new JLabel("Socket:");
        socket = new JTextField();
        JLabel cool = new JLabel("Cooler:");
        JCheckBox cooler = new JCheckBox();

        Component[] cmp = {nome, name, freq, frequency, core, nCore, thrd, thread, memtype, ram, watt, power, bit, nBit, gpu, hasGpu, sock, socket, cool, cooler};
        hasGpu.setHorizontalAlignment(SwingConstants.RIGHT);
        cooler.setHorizontalAlignment(SwingConstants.RIGHT);
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            String s;
            String t;
            if(hasGpu.isSelected() && cooler.isSelected()) {
                s = "Y";
                t = "Y";
            } else if(!hasGpu.isSelected() && cooler.isSelected()) {
                s = "N";
                t = "Y";
            } else if(hasGpu.isSelected() && !cooler.isSelected()) {
                s = "Y";
                t = "N";
            } else {
                s = "N";
                t = "N";
            }
            s = name.getText() + "_" + frequency.getValue() + "_" + nCore.getValue() + "_" + thread.getValue() + "_" + ram.getSelectedItem() + "_" + power.getValue() + "_" + nBit.getSelectedItem() + "_" + s + "_" + socket.getText() + "_" + t;
            System.out.println(s);
        });

        return p;
    }

    private JPanel panelMOBO() {
        p = new JPanel(new GridLayout(9, 2));
        nome = new JLabel("Name:");
        name = new JTextField();
        sock = new JLabel("Socket:");
        socket = new JTextField();
        JLabel banchi = new JLabel("Slot RAM:");
        JComboBox nBanchi = new JComboBox();
        nBanchi.addItem("2");
        nBanchi.addItem("4");
        nBanchi.addItem("8");
        JLabel memtype = new JLabel("RAM type:");
        JComboBox ram = new JComboBox();
        ram.addItem("DDR2");
        ram.addItem("DDR3");
        ram.addItem("DDR4");
        JLabel pciE16 = new JLabel("Slot PCIE16x:");
        SpinnerNumberModel spinnerPCIE16Model = new SpinnerNumberModel(1,1,null,1);
        JSpinner nPci16 = new JSpinner(spinnerPCIE16Model);
        setSpinnerNotWritable(nPci16);
        JLabel pciE = new JLabel("Slot PCIE:");
        SpinnerNumberModel spinnerPCIEModel = new SpinnerNumberModel(1,1,null,1);
        JSpinner nPciE = new JSpinner(spinnerPCIEModel);
        setSpinnerNotWritable(nPciE);
        JLabel dim = new JLabel("Dimension:");
        JComboBox dimension = new JComboBox();
        dimension.addItem("ATX");
        dimension.addItem("Micro-ATX");
        dimension.addItem("Mini-ITX");
        JLabel sata = new JLabel("Slot SATA:");
        SpinnerNumberModel spinnerSataModel = new SpinnerNumberModel(1,1,6,1);
        JSpinner nSata = new JSpinner(spinnerSataModel);
        setSpinnerNotWritable(nSata);
        watt = new JLabel("Power:");
        SpinnerNumberModel spinnerPowerModel = new SpinnerNumberModel(1,1,null,1);
        power = new JSpinner(spinnerPowerModel);
        setSpinnerNotWritable(power);

        Component[] cmp = {nome, name, sock, socket, banchi, nBanchi, memtype, ram, pciE16, nPci16, pciE, nPciE, dim, dimension, sata, nSata, watt, power};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            s = name.getText() + "_" + socket.getText() + "_" + nBanchi.getSelectedItem() + "_" + ram.getSelectedItem() + "_" + nPci16.getValue() + "_" + nPciE.getValue() + "_" + dimension.getSelectedItem() + "_" + nSata.getValue() + "_" + power.getValue();
            System.out.println(s);
        });

        return p;
    }

    private JPanel panelGPU(){
        p = new JPanel(new GridLayout(3, 2));
        nome = new JLabel("Name:");
        name = new JTextField();
        JLabel capacity = new JLabel("Capacità GB:");
        DoubleSpinner cap = new DoubleSpinner(4,0, 16);
        setSpinnerNotWritable(cap);
        watt = new JLabel("TDP:");
        SpinnerNumberModel spinnerWattModel = new SpinnerNumberModel(50, 0, 350, 5);
        power = new JSpinner(spinnerWattModel);
        setSpinnerNotWritable(power);

        Component[] cmp = {nome, name, capacity, cap, watt, power};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            s = name.getText()+ "_" + cap.getDouble() + "_" + power.getValue();
            System.out.println(s);
        });

        return p;
    }

    private JPanel panelCooler() {
        p = new JPanel(new GridLayout(2,2));
        nome = new JLabel("Name:");
        name = new JTextField();
        JLabel liquido = new JLabel("Liquid:");
        JCheckBox liquid = new JCheckBox();

        Component[] cmp = {nome, name, liquido, liquid};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            String isLiquid;
            if(liquid.isSelected())
                isLiquid = "Y";
            else
                isLiquid = "N";
            s = name.getText() + "_" + isLiquid;
            System.out.println(s);
        });

        return p;
    }

    private JPanel panelRAM() {
        p = new JPanel(new GridLayout(6,2));
        nome = new JLabel("Nome:");
        name = new JTextField();
        watt = new JLabel("Watt:");
        SpinnerNumberModel spinnerWattModel = new SpinnerNumberModel(50, 0, 350, 5);
        power = new JSpinner(spinnerWattModel);
        setSpinnerNotWritable(power);
        JLabel memtype = new JLabel("RAM type:");
        JComboBox ram = new JComboBox();
        ram.addItem("DDR2");
        ram.addItem("DDR3");
        ram.addItem("DDR4");
        JLabel dimensione = new JLabel("Dimension (GB):");
        JComboBox dimension = new JComboBox();
        dimension.addItem("2");
        dimension.addItem("4");
        dimension.addItem("8");
        dimension.addItem("16");
        JLabel frequenza = new JLabel("Frequency (MHz):");
        JComboBox frequency = new JComboBox();
        frequency.addItem("800");
        frequency.addItem("1066");
        frequency.addItem("1600");
        frequency.addItem("2133");
        frequency.addItem("2400");
        frequency.addItem("2666");
        frequency.addItem("3000");
        JLabel nModuli = new JLabel("# of modules:");
        JComboBox modules = new JComboBox();
        modules.addItem("1");
        modules.addItem("2");
        modules.addItem("3");
        modules.addItem("4");
        modules.addItem("5");
        modules.addItem("6");
        modules.addItem("7");
        modules.addItem("8");

        Component[] cmp = {nome, name, watt, power, memtype, ram, dimensione, dimension, frequenza, frequency, nModuli, modules};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            s = name.getText() + "_" + power.getValue() + "_" + ram.getSelectedItem() + "_" + dimension.getSelectedItem() + "_" + frequency.getSelectedItem() + "_" + modules.getSelectedItem();
            System.out.println(s);
        });

        return p;
    }

    private JPanel panelPSU() {
        p = new JPanel(new GridLayout(4,2));
        nome = new JLabel("Nome:");
        name = new JTextField();
        watt = new JLabel("Watt:");
        SpinnerNumberModel spinnerPSUModel = new SpinnerNumberModel(50, 0, 3000, 10);
        power = new JSpinner(spinnerPSUModel);
        setSpinnerNotWritable(power);
        JLabel dimensione = new JLabel("Dimensione:");
        JComboBox dimension = new JComboBox();
        dimension.addItem("ATX");
        dimension.addItem("SFX");
        JLabel certificazione = new JLabel("Certification:");
        JComboBox certification = new JComboBox();
        certification.addItem("None");
        certification.addItem("80+");
        certification.addItem("80+ Bronze");
        certification.addItem("80+ Silver");
        certification.addItem("80+ Gold");
        certification.addItem("80+ Platinum");
        certification.addItem("80+ Titanium");

        Component[] cmp = {nome, name, watt, power, dimensione, dimension, certificazione, certification};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            s = name.getText() + "_" + power.getValue() + "_" + dimension.getSelectedItem() + "_" + certification.getSelectedItem();
            System.out.println(s);
        });

        return p;
    }

    private JPanel panelStorage() {
        p = new JPanel(new GridLayout(4,2));
        nome = new JLabel("Nome:");
        name = new JTextField();
        JLabel size = new JLabel("Size:");
        JComboBox dim = new JComboBox();
        dim.addItem("2.5");
        dim.addItem("3.5");
        JLabel dimensione = new JLabel("Storage (GB):");
        JComboBox storage = new JComboBox();
        storage.addItem("120");
        storage.addItem("250");
        storage.addItem("480");
        storage.addItem("1000");
        storage.addItem("2000");
        watt = new JLabel("Watt:");
        SpinnerNumberModel spinnerWattModel = new SpinnerNumberModel(50, 0, 350, 5);
        power = new JSpinner(spinnerWattModel);
        setSpinnerNotWritable(power);

        Component[] cmp = {nome, name, size, dim, dimensione, storage, watt, power};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            s = name.getText() + "_" + dim.getSelectedItem() + "_" + storage.getSelectedItem() + "_" + power.getValue();
            System.out.println(s);
        });

        return p;
    }

    private JPanel panelCASE() {
        p = new JPanel(new GridLayout());

        return p;
    }

    private JPanel panelOS() {
        p = new JPanel(new GridLayout(2,2));
        nome = new JLabel("Nome:");
        name = new JTextField();
        JLabel bit = new JLabel("Bit:");
        JComboBox nBit = new JComboBox();
        nBit.addItem("32");
        nBit.addItem("64");

        Component[] cmp = {nome, name, bit, nBit};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            s = name.getText() + "_" + nBit.getSelectedItem();
            System.out.println(s);
        });

        return p;
    }

    private JPanel panelALTRO() {
        p = new JPanel(new GridLayout(2,2));
        nome = new JLabel("Nome:");
        name = new JTextField();
        JLabel descrizione = new JLabel("Description:");
        JTextField description = new JTextField();

        Component[] cmp = {nome, name, descrizione, description};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            s = name.getText() + "_" + description.getText();
            System.out.println(s);
        });

        return p;
    }

    public static void main(String[] args) {
        AdvancedSpecs a = new AdvancedSpecs(PCParts.ALTRO);
    }

    private void setSpinnerNotWritable(JSpinner spinner) {
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }

    private void addCmp(Component[] arr, JPanel pan) {
        for(Component c : arr)
            pan.add(c);
    }
}
