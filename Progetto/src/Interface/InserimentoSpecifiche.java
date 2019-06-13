package Interface;

import Components.AbstractComponent;
import Gestione.GestoreOperazioni;
import InterfacingDB.PCParts;
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

    public InserimentoSpecifiche(GestoreOperazioni go, String user) {
        super("Pagina admin");
        this.go = go;
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
        for (int i = 0; i < PCParts.values().length; i++) // length - 1 perchè non considero "ALTRO" per ora
            componente.addItem(PCParts.valueOf(componentsName[i]));
        componente.setEditable(false);
        description = new JLabel("Descrizione");
        /*howToDesc = new JLabel("(La descrizione varia con la componente)");
        howToDesc.setFont(new Font("Arial", Font.ITALIC, 10));
        howToDesc.setForeground(Color.RED);*/
        advanced = new JButton("Avanzate");
        /*descrizione = new JTextField();
        descrizione.setFont(new Font("Arial", Font.PLAIN, 10));
        descrizione.setText("Rispettare il formato di inserimento proposto sotto");*/
        descPanel = new JPanel(new GridLayout(1, 1));
        descPanel.add(advanced);
        /*descPanel.add(descrizione);
        descPanel.add(howToDesc);*/
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
        update = new JButton("Update component...");
        remove = new JButton("Remove component...");
        check = new JButton("Show stored components...");
        btnPanel = new JPanel(new BorderLayout());
        fourButtons = new JPanel(new GridLayout(3, 2));
        checkButton = new JPanel(new GridLayout(1, 1));

        /*componente.addActionListener(e -> {
            if (componente.getSelectedItem() == PCParts.CPU) {
                howToDesc.setText("NOME_FREQ_CORE_THREAD_TDP_BIT_GPUINTEGRATA_SOCKET_COOLER");
                howToDesc.setFont(new Font("Arial", Font.ITALIC, 6));
            } else if (componente.getSelectedItem() == PCParts.COOLER)
                howToDesc.setText("NOME_LIQUIDO");
            else if (componente.getSelectedItem() == PCParts.RAM)
                howToDesc.setText("NOME_WATT_TIPO_GB_FREQUENZA_NMODULI");
            else if (componente.getSelectedItem() == PCParts.PSU)
                howToDesc.setText("NOME_WATT_DIMENSIONE_CERTIFICAZIONE");
            else if (componente.getSelectedItem() == PCParts.GPU)
                howToDesc.setText("NOME_GB_TDP");
            else if (componente.getSelectedItem() == PCParts.MOBO) {
                howToDesc.setText("NOME_CPUSOCKET_NBANCHI_RAMMODEL_NPCIE_NPCI_DIMENSIONE_NSATA_WATT");
                howToDesc.setFont(new Font("Arial", Font.ITALIC, 6));
            } else if (componente.getSelectedItem() == PCParts.STORAGE)
                howToDesc.setText("NOME_DIMENSIONE_GB");
            else if (componente.getSelectedItem() == PCParts.CASE)
                howToDesc.setText("NOME_DIMENSIONE_NSLOT525_NSLOT325");
            else if (componente.getSelectedItem() == PCParts.OS)
                howToDesc.setText("NOME_BIT");
            else if (componente.getSelectedItem() == PCParts.ALTRO)
                howToDesc.setText("NOME_DESCRIZIONE");
        });*/

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
            // codice per la scrittura su DB
            if (go.insertComponent(
                    (PCParts) componente.getSelectedItem(),
                    descrizione.getText().toUpperCase(),
                    (int) quantita.getValue(),
                    (int) prezzo.getValue(),
                    (int) valutazione.getValue())) {
                Object[] options = {"YES", "NO"};
                int inserimento = JOptionPane.showOptionDialog(null, "Nuovo inserimento?", "Inserimento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "YES");
                if (inserimento == 0) {
                    componente.setSelectedItem(PCParts.CASE);
                    descrizione.setText("");
                    quantita.setValue(1);
                    prezzo.setValue(1);
                    valutazione.setValue(1);
                } else {
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Errore inserimento specifiche", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        check.addActionListener(e -> {
            Reader reading = new Reader();
            ArrayList<AbstractComponent> components;
            String s = "";
            new CompList(this, go);
        });

        advanced.addActionListener(e -> {
            AdvancedSpecs adv = new AdvancedSpecs((PCParts) componente.getSelectedItem());
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
                go.unlockPlatform();
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

    /*public static void main(String[] args) {
        InserimentoSpecifiche ins = new InserimentoSpecifiche(null, "prova");
    }*/
}
