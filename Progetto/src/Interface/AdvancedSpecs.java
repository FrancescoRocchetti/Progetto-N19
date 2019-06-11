package Interface;

import InterfacingDB.PCParts;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

public class AdvancedSpecs extends JFrame {
    private JButton okBtn;
    private JButton cancBtn;
    private JPanel background;

    public AdvancedSpecs(PCParts part) {
        super("Inserimento specifiche");
        Container c = getContentPane();
        okBtn = new JButton("Conferma");
        cancBtn = new JButton("Annulla");
        background = BuildPanel(part);

        c.add(background);

        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JPanel BuildPanel(PCParts part) {
        JPanel panel;
        switch(part){
            case CPU: {
                panel = PanelCPU();
                return panel;
            }
            default: return null;
        }
    }

    private JPanel PanelCPU() {
        JPanel p = new JPanel();
        //nome, freq, core, thread, memtype, tdp, bit, gpu, socket, cooler
        JLabel nome = new JLabel("Name:");
        JTextField name = new JTextField();
        JLabel freq = new JLabel("Frequency (GHz):");
        SpinnerNumberModel spinnerFreqModel = new SpinnerNumberModel(1, 1, null, 0.01);
        JSpinner frequency = new JSpinner(spinnerFreqModel);
        setSpinnerNotWritable(frequency);
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
        JSpinner power = new JSpinner();
        setSpinnerNotWritable(power);

        p.add(frequency);
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
