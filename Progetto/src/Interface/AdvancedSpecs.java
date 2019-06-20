package Interface;

import Gestione.GestoreOperazioni;
import Components.PCParts;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AdvancedSpecs extends JFrame {
    private JButton okBtn;

    private GestoreOperazioni go;

    private JPanel p;
    private JLabel nome;
    private JTextField name;
    private JLabel sock;
    private JTextField socket;
    private JLabel watt;
    private JSpinner power;
    private String s;

    public AdvancedSpecs(PCParts part, GestoreOperazioni go, InserimentoSpecifiche ins) {
        super(part.name());
        go.setDescrizione(null);
        ins.setEnabled(false);
        ins.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.go = go;
        Container c = getContentPane();
        JButton cancBtn;
        JPanel bckg;
        JPanel compPanel;
        JPanel btnPanel;
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

        cancBtnListener(cancBtn);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                ins.enableConfirmButton();
                ins.setEnabled(true);
                ins.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        setLocationRelativeTo(ins);
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
        JSpinner nCore = initializeSpinner(spinnerCoreModel);
        JLabel thrd = new JLabel("Thread:");
        SpinnerNumberModel spinnerThreadModel = new SpinnerNumberModel(1, 1, 36, 1);
        JSpinner thread = initializeSpinner(spinnerThreadModel);
        JLabel memtype = new JLabel("RAM type:");
        JComboBox ram = new JComboBox();
        ram.addItem("DDR2");
        ram.addItem("DDR3");
        ram.addItem("DDR4");
        watt = new JLabel("TDP:");
        SpinnerNumberModel spinnerPowerModel = new SpinnerNumberModel(1,1,300,1);
        power = initializeSpinner(spinnerPowerModel);
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
            if(isNotEmpty(name.getText()) && isNotEmpty(socket.getText())){
                String s = "N";
                String t = "N";

                if(hasGpu.isSelected())
                    s = "Y";
                if (cooler.isSelected())
                    t = "Y";

                s = name.getText() + "_" + frequency.getValue() + "_" + nCore.getValue() + "_" + thread.getValue() + "_" + ram.getSelectedItem() + "_" + power.getValue() + "_" + nBit.getSelectedItem() + "_" + s + "_" + socket.getText() + "_" + t;
                go.setDescrizione(s);
                dispose();
            }
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
        SpinnerNumberModel spinnerPCIE16Model = new SpinnerNumberModel(1,0,null,1);
        JSpinner nPci16 = initializeSpinner(spinnerPCIE16Model);
        JLabel pciE = new JLabel("Slot PCIE:");
        SpinnerNumberModel spinnerPCIEModel = new SpinnerNumberModel(1,0,null,1);
        JSpinner nPciE = initializeSpinner(spinnerPCIEModel);
        JLabel dim = new JLabel("Dimension:");
        JComboBox dimension = new JComboBox();
        String[] str = {"EATX", "ATX", "MICROATX", "MINIITX"};
        for(String i : str)
            dimension.addItem(i);
        JLabel sata = new JLabel("Slot SATA:");
        SpinnerNumberModel spinnerSataModel = new SpinnerNumberModel(1,1,6,1);
        JSpinner nSata = initializeSpinner(spinnerSataModel);
        watt = new JLabel("Power:");
        SpinnerNumberModel spinnerPowerModel = new SpinnerNumberModel(1,1,null,1);
        power = initializeSpinner(spinnerPowerModel);

        Component[] cmp = {nome, name, sock, socket, banchi, nBanchi, memtype, ram, pciE16, nPci16, pciE, nPciE, dim, dimension, sata, nSata, watt, power};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            if(isNotEmpty(name.getText()) && isNotEmpty(socket.getText())) {
                s = name.getText() + "_" + socket.getText() + "_" + nBanchi.getSelectedItem() + "_" + ram.getSelectedItem() + "_" + nPci16.getValue() + "_" + nPciE.getValue() + "_" + dimension.getSelectedItem() + "_" + nSata.getValue() + "_" + power.getValue();
                go.setDescrizione(s);
                dispose();
            }
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
        power = initializeSpinner(spinnerWattModel);

        Component[] cmp = {nome, name, capacity, cap, watt, power};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            if(isNotEmpty(name.getText())) {
                s = name.getText() + "_" + cap.getDouble() + "_" + power.getValue();
                go.setDescrizione(s);
                dispose();
            }
        });

        return p;
    }

    private JPanel panelCooler() {
        p = new JPanel(new GridLayout(3,2));
        nome = new JLabel("Name:");
        name = new JTextField();
        JLabel liquido = new JLabel("Liquid:");
        JCheckBox liquid = new JCheckBox();
        sock = new JLabel("Socket :");
        socket = new HintTextField("se multi-socket, separare con ';'");

        Component[] cmp = {nome, name, liquido, liquid, sock, socket};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            if(isNotEmpty(name.getText()) && isNotEmpty(socket.getText())) {
                String isLiquid = "N";
                if (liquid.isSelected())
                    isLiquid = "Y";
                s = name.getText() + "_" + isLiquid+"_"+socket.getText();
                go.setDescrizione(s);
                dispose();
            }
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
        String[] type = {"DDR2", "DDR3", "DDR4"};
        for(String s : type)
            ram.addItem(s);
        JLabel dimensione = new JLabel("Dimension (GB):");
        JComboBox dimension = new JComboBox();
        int dim[] = {2, 4, 8, 16};
        for(int i : dim)
            dimension.addItem(i);
        JLabel frequenza = new JLabel("Frequency (MHz):");
        JComboBox frequency = new JComboBox();
        int freq[] = {800, 1066, 1600, 2133, 2400, 2666, 3000};
        for(int i : freq)
            frequency.addItem(i);
        JLabel nModuli = new JLabel("# of modules:");
        JComboBox modules = new JComboBox();
        for(int i = 1; i <= 8; i++)
            modules.addItem(i);

        Component[] cmp = {nome, name, watt, power, memtype, ram, dimensione, dimension, frequenza, frequency, nModuli, modules};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            if(isNotEmpty(name.getText())) {
                s = name.getText() + "_" + power.getValue() + "_" + ram.getSelectedItem() + "_" + dimension.getSelectedItem() + "_" + frequency.getSelectedItem() + "_" + modules.getSelectedItem();
                go.setDescrizione(s);
                dispose();
            }
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
        String[] cert = {"None", "80+", "80+ Bronze", "80+ Silver", "80+ Gold", "80+ Platinum", "80+ Titanium"};
        for(String s : cert)
            certification.addItem(s);

        Component[] cmp = {nome, name, watt, power, dimensione, dimension, certificazione, certification};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            if(isNotEmpty(name.getText())){
                s = name.getText() + "_" + power.getValue() + "_" + dimension.getSelectedItem() + "_" + certification.getSelectedItem();
                go.setDescrizione(s);
                dispose();
            }
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
        int[] dimension = {128, 256, 512, 1024, 2048, 4096};
        for(int i : dimension)
            storage.addItem(i);
        watt = new JLabel("Watt:");
        SpinnerNumberModel spinnerWattModel = new SpinnerNumberModel(50, 0, 350, 5);
        power = new JSpinner(spinnerWattModel);

        Component[] cmp = {nome, name, size, dim, dimensione, storage, watt, power};
        addCmp(cmp, p);

        okBtn.addActionListener(e -> {
            if(isNotEmpty(name.getText())) {
                s = name.getText() + "_" + dim.getSelectedItem() + "_" + storage.getSelectedItem() + "_" + power.getValue();
                go.setDescrizione(s);
                dispose();
            }
        });

        return p;
    }

    private JPanel panelCASE() {
        p = new JPanel(new GridLayout(4,2));
        nome = new JLabel("Nome:");
        name = new JTextField();
        JLabel dimensione = new JLabel("Size:");
        JComboBox dimension = new JComboBox();
        String[] str = {"EATX", "ATX", "MICROATX", "MINIITX"};
        for(String i : str)
            dimension.addItem(i);
        JLabel slot35 = new JLabel("# slot 3.5:");
        SpinnerNumberModel spinnerNSlot35Model = new SpinnerNumberModel(1, 0, 20, 1);
        JSpinner nSlot35 = initializeSpinner(spinnerNSlot35Model);
        JLabel slot25 = new JLabel("# slot 2.5:");
        SpinnerNumberModel spinnerNSlot25Model = new SpinnerNumberModel(1, 0, 20, 1);
        JSpinner nSlot25 = initializeSpinner(spinnerNSlot25Model);

        Component[] cmp = {nome, name, dimensione, dimension, slot35, nSlot35, slot25, nSlot25};

        addCmp(cmp,p);

        okBtn.addActionListener(e -> {
            if(isNotEmpty(name.getText())) {
                s = name.getText() + "_" + dimension.getSelectedItem()+"_"+nSlot35.getValue()+"_"+nSlot25.getValue();
                go.setDescrizione(s);
                dispose();
            }
        });

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
            if(isNotEmpty(name.getText())) {
                s = name.getText() + "_" + nBit.getSelectedItem();
                go.setDescrizione(s);
                dispose();
            }
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
            if(isNotEmpty(name.getText())) {
                s = name.getText() + "_" + description.getText();
                go.setDescrizione(s);
                dispose();
            }
        });

        return p;
    }

    private void setSpinnerNotWritable(JSpinner spinner) {
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }

    private void addCmp(Component[] arr, JPanel pan) {
        for(Component c : arr)
            pan.add(c);
    }

    private void cancBtnListener(JButton btn) {
        btn.addActionListener(e -> dispose());
    }

    private JSpinner initializeSpinner(SpinnerNumberModel model){
        JSpinner s = new JSpinner(model);
        setSpinnerNotWritable(s);
        return s;
    }

    private boolean isNotEmpty(String name){
        if (!name.contains("_") && !name.trim().isEmpty())
            return true;
        else {
            JOptionPane.showMessageDialog(this, "Controlla di aver inserito tutto", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
