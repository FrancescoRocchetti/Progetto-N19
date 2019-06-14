package Interface;

import Components.AbstractComponent;
import Gestione.GestoreOperazioni;
import Components.PCParts;
import Gestione.GestoreScelte;
import InterfacingDB.Reader;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InserimentoSpecifiche extends JFrame {
    private Container c;
    private JPanel background;
    private JPanel data;
    private JPanel descPanel;
    private Toolkit kit;
    private Dimension dim;

    private JLabel title;
    private JLabel component;
    private JLabel description;
    private JLabel howToDesc;
    private JLabel quantity;
    private JLabel price;
    private JLabel ranking;
    private JLabel loggedAs;

    private JTextField descrizione;
    private JComboBox componente;
    private JSpinner quantita;
    private SpinnerNumberModel spinnerModel;
    private JSpinner prezzo;
    private SpinnerNumberModel spinnerPriceModel;
    private JSpinner valutazione;
    private SpinnerNumberModel spinnerRankModel;
    private JButton goBack;
    private JButton confirm;
    private JPanel btnPanel;
    private JPanel northPanel;
    private JButton update;
    private JButton remove;
    private JButton check;
    private JPanel fourButtons;
    private JPanel checkButton;
    private JLabel title1;
    private JButton advanced;

    private String[] componentsName;

    private final static int QTA = 99;
    private GestoreOperazioni go;

    public InserimentoSpecifiche(Piattaforma p, GestoreOperazioni go, String user) {
        super("Pagina admin");
        this.go = go;
        go.setIns(this);
        kit = Toolkit.getDefaultToolkit();
        dim = kit.getScreenSize();
        c = getContentPane();
        componentsName = new String[]{"CASE", "COOLER", "CPU", "GPU", "MOBO", "PSU", "RAM", "STORAGE", "OS", "ALTRO"};
        background = new JPanel(new BorderLayout());
        title = new JLabel("Inserisci le informazioni richieste");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        loggedAs = new JLabel("Accesso effettuato come: " + user);
        loggedAs.setFont(new Font("Arial", Font.BOLD, 14));
        loggedAs.setForeground(Color.GRAY);
        title1 = new JLabel("Aggiungi nuovo componente");
        title1.setFont(new Font("Arial", Font.BOLD, 16));
        northPanel = new JPanel(new GridLayout(3, 1));
        northPanel.add(title);
        northPanel.add(loggedAs);
        northPanel.add(title1);
        data = new JPanel(new GridLayout(5, 2));
        component = new JLabel("Componente");
        componente = new JComboBox();
        for (int i = 0; i < PCParts.values().length; i++)
            componente.addItem(PCParts.valueOf(componentsName[i]));
        componente.setEditable(false);
        description = new JLabel("Descrizione");
        advanced = new JButton("Inserisci opzioni");
        descPanel = new JPanel(new GridLayout(1, 1));
        descPanel.add(advanced);
        quantity = new JLabel("Quantità");
        spinnerModel = new SpinnerNumberModel(1, 1, QTA, 1);
        quantita = new JSpinner(spinnerModel);
        setSpinnerNotWritable(quantita);
        price = new JLabel("Prezzo");
        spinnerPriceModel = new SpinnerNumberModel(1, 1, null, 1);
        prezzo = new JSpinner(spinnerPriceModel);
        setSpinnerNotWritable(prezzo);
        ranking = new JLabel("Valutazione");
        spinnerRankModel = new SpinnerNumberModel(1, 1, 5, 1);
        valutazione = new JSpinner(spinnerRankModel);
        setSpinnerNotWritable(valutazione);
        goBack = new JButton("Logout");
        goBack.setForeground(Color.RED);
        confirm = new JButton("Conferma");
        confirm.setForeground(Color.GREEN);
        confirm.setEnabled(false);
        update = new JButton("Update component...");
        remove = new JButton("Remove component...");
        check = new JButton("Show stored components...");
        btnPanel = new JPanel(new BorderLayout());
        fourButtons = new JPanel(new GridLayout(3, 2));
        checkButton = new JPanel(new GridLayout(1, 1));

        remove.addActionListener(e -> {
            Remove comp = new Remove(this, go);
        });

        update.addActionListener(e -> {
            Update update = new Update(this, go);
        });

        goBack.addActionListener(e -> {
            dispose();
        });

        confirm.addActionListener(e -> {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            confirm.setEnabled(false);
            goBack.setEnabled(false);
            go.insertComponent(
                    (PCParts) componente.getSelectedItem(),
                    (int) quantita.getValue(),
                    (int) prezzo.getValue(),
                    (int) valutazione.getValue());
        });

        check.addActionListener(e -> {
            new CompList(this, getString());
        });

        advanced.addActionListener(e -> {
            AdvancedSpecs adv = new AdvancedSpecs((PCParts) componente.getSelectedItem(),go,this);
        });

        data.add(component);
        data.add(componente);
        data.add(description);
        data.add(descPanel);
        data.add(quantity);
        data.add(quantita);
        data.add(price);
        data.add(prezzo);
        data.add(ranking);
        data.add(valutazione);

        fourButtons.add(goBack);
        fourButtons.add(confirm);
        fourButtons.add(new JLabel("Aggiornamento componenti"));
        fourButtons.add(new JLabel());
        fourButtons.add(remove);
        fourButtons.add(update);

        checkButton.add(check);

        btnPanel.add(fourButtons, BorderLayout.NORTH);
        btnPanel.add(checkButton, BorderLayout.SOUTH);

        background.add(northPanel, BorderLayout.NORTH);
        background.add(data, BorderLayout.CENTER);
        background.add(btnPanel, BorderLayout.SOUTH);

        c.add(background);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                p.setVisible(true);
                if (go.isModified())
                    p.refresh();
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
        setSize(600, 380);
        setResizable(false);
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        setVisible(true);
    }

    private void setSpinnerNotWritable(JSpinner spinner) {
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }

    private Object[][] getString() {
        ArrayList<AbstractComponent> comp = go.read(null);
        if (comp == null) {
            return null;
        }
        String[][] data = new String[comp.size()][];
        AbstractComponent abs;
        for (int i = 0; i < comp.size(); i++) {
            data[i] = new String[5];
            abs = comp.get(i);
            data[i][0] = String.valueOf(abs.getID());
            data[i][1] = abs.getType();
            data[i][2] = abs.getName();
            data[i][3] = String.valueOf(abs.getQuantity());
            data[i][4] = abs.getPrice() + " €";
        }
        return data;
    }

    public void enableConfirmButton(){
        Boolean t = go.canAdd();
        confirm.setEnabled(t);
    }

    public void updateAdd(boolean status){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        if(status){
            Object[] options = {"YES", "NO"};
            int inserimento = JOptionPane.showOptionDialog(this, "Inserito oggetto con successo\nNuovo inserimento?", "Inserimento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "YES");
            if (inserimento == 0) {
                componente.setSelectedItem(PCParts.CASE);
                go.setDescrizione(null);
                quantita.setValue(1);
                prezzo.setValue(1);
                valutazione.setValue(1);
            } else {
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Errore inserimento specifiche", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        confirm.setEnabled(true);
        goBack.setEnabled(true);
    }
}
