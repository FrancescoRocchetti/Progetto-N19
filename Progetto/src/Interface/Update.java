package Interface;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Update extends JFrame {
    private final static int QTA = 99;

    public Update(InserimentoSpecifiche ins, GestoreOperazioni go){
        super("Aggiornamento");
        ins.setEnabled(false);
        ins.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        Container c = getContentPane();
        JButton add = new JButton("Aggiungi");
        JButton close = new JButton("Indietro");
        JLabel labi = new JLabel("Codice articolo");
        JLabel labq = new JLabel("Quantità");
        JPanel panel = new JPanel(new GridLayout(2,2));
        JPanel btnpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel mainpanel = new JPanel(new BorderLayout());
        SpinnerNumberModel spinnerIndexModel = new SpinnerNumberModel(1, 1, QTA, 1);
        JSpinner index = new JSpinner(spinnerIndexModel);
        SpinnerNumberModel spinnerQtyModel = new SpinnerNumberModel(1,1,null,1);
        JSpinner qty = new JSpinner(spinnerQtyModel);
        setSpinnerNotWritable(index);
        setSpinnerNotWritable(qty);
        panel.add(labi);
        panel.add(index);
        panel.add(labq);
        panel.add(qty);
        btnpanel.add(add);
        btnpanel.add(close);
        mainpanel.add(panel, BorderLayout.CENTER);
        mainpanel.add(btnpanel, BorderLayout.SOUTH);
        c.add(mainpanel);

        close.addActionListener(e -> {
            dispose();
        });

        add.addActionListener(e -> {
            if(!go.updateComponent((int) index.getValue(), (int) qty.getValue())){
                JOptionPane.showMessageDialog(null, "Componente inesistente\no errore di accesso al DB", "Errore", JOptionPane.ERROR_MESSAGE);
            } else JOptionPane.showMessageDialog(null, "Quantità aggiornata", "Aggiunto", JOptionPane.INFORMATION_MESSAGE);
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

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        pack();
        setVisible(true);
    }

    private void setSpinnerNotWritable(JSpinner spinner) {
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }
}
