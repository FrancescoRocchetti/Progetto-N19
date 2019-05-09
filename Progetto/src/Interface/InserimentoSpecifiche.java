package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InserimentoSpecifiche extends JFrame {
    private Container c;
    private JPanel background;
    private JPanel data;
    private JPanel descPanel;

    private JLabel title;
    private JLabel component;
    private JLabel description;
    private JLabel howToDesc;


    private JTextField descrizione;
    private JComboBox componente;

    private String componentsName[];


    public InserimentoSpecifiche() {
        super("Aggiunta componente");
        c = getContentPane();
        componentsName = new String[]{"Case", "Cooler CPU", "CPU", "GPU", "MOBO", "OS", "PSU", "RAM", "Storage"};
        background = new JPanel(new BorderLayout());
        title = new JLabel("Inserisci le informazioni richieste");
        background.add(title, BorderLayout.NORTH);
        data = new JPanel(new GridLayout(2,2));
        component = new JLabel("Componente");
        componente = new JComboBox();
        for(String s : componentsName)
            componente.addItem(s);
        componente.setEditable(false);
        description = new JLabel("Descrizione");
        howToDesc = new JLabel("(NOME_COGNOME_ETA_DATA_CLASSE_ID_CODFISC_CITTA_PAESE_NASCITA)");
        howToDesc.setFont(new Font("Arial", Font.ITALIC, 10));
        descrizione = new JTextField();
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

        data.add(component);
        data.add(componente);
        data.add(description);
        data.add(descPanel);

        background.add(data, BorderLayout.CENTER);

        c.add(background);


        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        InserimentoSpecifiche ins = new InserimentoSpecifiche();
    }
}
