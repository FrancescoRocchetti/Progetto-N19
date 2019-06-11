package Interface;

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
            case GPU:{
                return panelGPU();
            }
            default: return null;
        }
    }

    private JPanel panelCPU() {
        JPanel p = new JPanel(new GridLayout(10, 2));
        JLabel nome = new JLabel("Name:");
        JTextField name = new JTextField();
        JLabel freq = new JLabel("Frequency (GHz):");
        DoubleSpinner frequency = new DoubleSpinner(3,1, 6);
        setSpinnerNotWritable(frequency);
        JLabel core = new JLabel("Core:");
        SpinnerNumberModel spinnerCoreModel = new SpinnerNumberModel(1, 1, null, 1);
        JSpinner nCore = new JSpinner(spinnerCoreModel);
        setSpinnerNotWritable(nCore);
        JLabel thrd = new JLabel("Thread:");
        SpinnerNumberModel spinnerThreadModel = new SpinnerNumberModel(1, 1, null, 1);
        JSpinner thread = new JSpinner(spinnerThreadModel);
        setSpinnerNotWritable(thread);
        JLabel memtype = new JLabel("RAM type:");
        JComboBox ram = new JComboBox();
        ram.addItem("DDR2");
        ram.addItem("DDR3");
        ram.addItem("DDR4");
        JLabel tdp = new JLabel("TDP:");
        SpinnerNumberModel spinnerPowerModel = new SpinnerNumberModel(1,1,300,1);
        JSpinner power = new JSpinner(spinnerPowerModel);
        setSpinnerNotWritable(power);
        JLabel bit = new JLabel("Bit:");
        JComboBox nBit = new JComboBox();
        nBit.addItem("32");
        nBit.addItem("64");
        JLabel gpu = new JLabel("Integrated GPU:");
        JCheckBox hasGpu  =new JCheckBox();
        JLabel sock = new JLabel("Socket:");
        JTextField socket = new JTextField();
        JLabel cool = new JLabel("Cooler:");
        JCheckBox cooler = new JCheckBox();

        Component[] cmp = {nome, name, freq, frequency, core, nCore, thrd, thread, memtype, ram, tdp, power, bit, nBit, gpu, hasGpu, sock, socket, cool, cooler};
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
            String str = name.getText() + "_" + frequency.getValue() + "_" + nCore.getValue() + "_" + thread.getValue() + "_" + ram.getSelectedItem() + "_" + power.getValue() + "_" + nBit.getSelectedItem() + "_" + s + "_" + socket.getText() + "_" + t;
            System.out.println(str);
        });

        return p;
    }

    private JPanel panelMOBO() {
        JPanel p = new JPanel(new GridLayout(9, 2));
        JLabel nome = new JLabel("Name:");
        JTextField name = new JTextField();
        JLabel sock = new JLabel("Socket:");
        JTextField socket = new JTextField();
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
        JLabel watt = new JLabel("Power:");
        SpinnerNumberModel spinnerPowerModel = new SpinnerNumberModel(1,1,null,1);
        JSpinner power = new JSpinner(spinnerPowerModel);
        setSpinnerNotWritable(power);

        Component[] cmp = {nome, name, sock, socket, banchi, nBanchi, memtype, ram, pciE16, nPci16, pciE, nPciE, dim, dimension, sata, nSata, watt, power};
        addCmp(cmp, p);

        return p;
    }

    private JPanel panelGPU(){
        JPanel p = new JPanel(new GridLayout(3, 2));
        JLabel nome = new JLabel("Name:");
        JTextField name = new JTextField();
        JLabel capacity = new JLabel("Capacità GB:");
        DoubleSpinner cap = new DoubleSpinner(4,0, 16);
        setSpinnerNotWritable(cap);
        JLabel watt = new JLabel("TDP:");
        SpinnerNumberModel spinnerWattModel = new SpinnerNumberModel(50, 0, 350, 5);
        JSpinner nWatt = new JSpinner(spinnerWattModel);
        setSpinnerNotWritable(nWatt);

        Component[] cmp = {nome, name, capacity, cap, watt, nWatt};
        addCmp(cmp, p);

        return p;
    }

    public static void main(String[] args) {
        AdvancedSpecs a = new AdvancedSpecs(PCParts.GPU);
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
