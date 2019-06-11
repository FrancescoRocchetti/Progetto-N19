package Interface;

import InterfacingDB.PCParts;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;

public class AdvancedSpecs extends JFrame {
    private JButton okBtn;
    private JButton cancBtn;
    private JPanel background;

    public AdvancedSpecs(PCParts part) {
        super("Inserimento specifiche");
        Container c = getContentPane();
        okBtn = new JButton("Conferma");
        cancBtn = new JButton("Annulla");
        background = buildPanel(part);

        c.add(background);

        setVisible(true);
        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JPanel buildPanel(PCParts part) {
        JPanel panel;
        switch(part){
            case CPU: {
                panel = panelCPU();
                return panel;
            }
            default: return null;
        }
    }

    private JPanel panelCPU() {
        JPanel p = new JPanel(new GridLayout(10, 2));
        //nome, freq, core, thread, memtype, tdp, bit, gpu, socket, cooler
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
        SpinnerNumberModel spinnerPowerModel = new SpinnerNumberModel(1,1,65,1);
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
        JCheckBox cooler  =new JCheckBox();

        p.add(nome);
        p.add(name);
        p.add(freq);
        p.add(frequency);
        p.add(frequency);
        p.add(core);
        p.add(nCore);
        p.add(thrd);
        p.add(thread);
        p.add(memtype);
        p.add(ram);
        p.add(tdp);
        p.add(power);
        p.add(bit);
        p.add(nBit);
        p.add(gpu);
        p.add(hasGpu);
        p.add(sock);
        p.add(socket);
        p.add(cool);
        p.add(cooler);
        return p;
    }

    public static void main(String[] args) {
        AdvancedSpecs a = new AdvancedSpecs(PCParts.CPU);
    }

    private void setSpinnerNotWritable(JSpinner spinner) {
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }
}
