package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InserimentoSpecifiche extends JFrame {
    private Container c;
    private JPanel background;
    private JPanel data;
    private JPanel descPanel;

    private JLabel title;
    private JLabel component;
    private JLabel description;
    private JLabel howToDesc;
    private JLabel quantity;
    private JLabel price;
    private JLabel ranking;

    private JTextField descrizione;
    private JComboBox componente;
    private JSpinner quantita;
    private SpinnerNumberModel spinnerModel;
    private JSpinner prezzo;
    private SpinnerNumberModel spinnerPriceModel;
    private JSpinner valutazione;
    private SpinnerNumberModel spinnerRankModel;

    private String componentsName[];

    private final int QTA = 99;


    public InserimentoSpecifiche(Piattaforma p) {
        super("Aggiunta componente");
        c = getContentPane();
        componentsName = new String[]{"Case", "Cooler CPU", "CPU", "GPU", "MOBO", "OS", "PSU", "RAM", "Storage"};
        background = new JPanel(new BorderLayout());
        title = new JLabel("Inserisci le informazioni richieste");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        background.add(title, BorderLayout.NORTH);
        data = new JPanel(new GridLayout(5,2));
        component = new JLabel("Componente");
        componente = new JComboBox();
        for(String s : componentsName)
            componente.addItem(s);
        componente.setEditable(false);
        description = new JLabel("Descrizione");
        howToDesc = new JLabel("(La descrizione varia con la componente)");
        howToDesc.setFont(new Font("Arial", Font.ITALIC, 10));
        descrizione = new JTextField();
        descrizione.setFont(new Font("Arial", Font.PLAIN, 10));
        descrizione.setText("Rispettare il formato di inserimento proposto sotto");
        descrizione.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                descrizione.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        descPanel = new JPanel(new GridLayout(2,1));
        descPanel.add(descrizione);
        descPanel.add(howToDesc);
        quantity = new JLabel("Quantità");
        spinnerModel = new SpinnerNumberModel(1, 1, QTA, 1);
        quantita = new JSpinner(spinnerModel);
        price = new JLabel("Prezzo");
        spinnerPriceModel = new SpinnerNumberModel(1, 1, null, 1);
        prezzo = new JSpinner(spinnerPriceModel);
        ranking = new JLabel("Valutazione");
        spinnerRankModel = new SpinnerNumberModel(1, 1, 5, 1);
        valutazione = new JSpinner(spinnerRankModel);

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

        background.add(data, BorderLayout.CENTER);

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
                dispose();
                p.setVisible(true);
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

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 250);
        setResizable(false);
    }

    public static void main(String[] args) {
        InserimentoSpecifiche ins = new InserimentoSpecifiche(null);
    }
}
