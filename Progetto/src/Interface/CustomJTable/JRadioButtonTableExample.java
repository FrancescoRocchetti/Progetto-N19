package Interface.CustomJTable;



import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JRadioButtonTableExample extends JFrame {

    public JRadioButtonTableExample() {
        super("JRadioButtonTable Example");
        UIDefaults ui = UIManager.getLookAndFeel().getDefaults();
        UIManager.put("RadioButton.focus", ui.getColor("control"));

        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(
                new Object[][]{
                        {"Group 1",new JRadioButton("A")},
                        {"Group 1",new JRadioButton("B")},
                        {"Group 1",new JRadioButton("C")}/*,
                        {"Group 2",new JRadioButton("a")},
                        {"Group 2",new JRadioButton("b")}*/},
                new Object[]{"String","JRadioButton"});

        JTable table = new JTable(dm) {
            public void tableChanged(TableModelEvent e) {
                super.tableChanged(e);
                repaint();
            }
        };
        ButtonGroup group1 = new ButtonGroup();
        group1.add((JRadioButton)dm.getValueAt(0,1));
        group1.add((JRadioButton)dm.getValueAt(1,1));
        group1.add((JRadioButton)dm.getValueAt(2,1));
        /*ButtonGroup group2 = new ButtonGroup();
        group2.add((JRadioButton)dm.getValueAt(3,1));
        group2.add((JRadioButton)dm.getValueAt(4,1));*/
        table.getColumn("JRadioButton").setCellRenderer(new RadioButtonRenderer());
        table.getColumn("JRadioButton").setCellEditor(new RadioButtonEditor(new JCheckBox()));
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setSize(200, 140);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
