package Interface.StandardInterface;

import Logica.Facade;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.util.ArrayList;

/**
 * Interfaccia che permette di inserire nuovi componenti nel database
 *
 * @author Matteo Lucchini
 */

public class AddProduct extends JFrame {
    private Container c;
    private JPanel bckg;
    private JPanel title;
    private JPanel body;
    private JPanel btns;
    private JPanel checkBoxPanel;
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JLabel ratingLabel;
    private JLabel typeLabel;
    private JLabel constraintLabel;
    private JTextField  name;
    private SpinnerNumberModel spinnerPriceModel;
    private SpinnerNumberModel spinnerQuantityModel;
    private SpinnerNumberModel spinnerRankModel;
    private JSpinner price;
    private JSpinner quantity;
    private JSpinner rank;
    private JComboBox type;
    private JList<Object> constraint;
    private JButton back;
    private JButton confirm;
    private JButton checkBoxButton;

    private Facade f;

    public AddProduct() {
        c = getContentPane();
        bckg = new JPanel(new BorderLayout());
        title = new JPanel(new GridLayout(1,1));
        body = new JPanel(new GridLayout(6,2));
        btns = new JPanel(new GridLayout(1,2));
        f = new Facade();

        titleLabel = new JLabel("Inserimento prodotto");
        nameLabel = new JLabel("Nome");
        priceLabel = new JLabel("Price");
        quantityLabel = new JLabel("Quantità");
        ratingLabel = new JLabel("Rating");
        typeLabel = new JLabel("Tipo");
        constraintLabel = new JLabel("Vincolo");

        name = new JTextField();
        spinnerPriceModel = new SpinnerNumberModel(1, 1, null, 1);
        price = new JSpinner(spinnerPriceModel);
        setSpinnerNotWritable(price);
        spinnerQuantityModel = new SpinnerNumberModel(0, 0, null, 1);
        quantity = new JSpinner(spinnerQuantityModel);
        setSpinnerNotWritable(quantity);
        spinnerRankModel = new SpinnerNumberModel(1, 1, 5, 1);
        rank = new JSpinner(spinnerRankModel);
        setSpinnerNotWritable(rank);
        type = new JComboBox(); // Qui ci vanno i tipi già presenti nel DB
        for(int i = 0; i < f.getAllTypes().size(); i++)
            type.addItem(f.getAllTypes().get(i));
        ArrayList<String> vincoli = new ArrayList<>();
        for(int i = 0; i < f.getAllVincoli().size(); i++)
            vincoli.add(f.getAllVincoli().get(i));
        Object[] obj = vincoli.toArray();
        constraint = new JList<>(obj); // Vincoli già presenti nel DB
        constraint.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        constraint.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scp = new JScrollPane(constraint);

        back = new JButton("Annulla");
        confirm = new JButton("Aggiungi");
        checkBoxButton = new JButton("Scegli");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        title.add(titleLabel);

        body.add(nameLabel);
        body.add(name);
        body.add(priceLabel);
        body.add(price);
        body.add(quantityLabel);
        body.add(quantity);
        body.add(ratingLabel);
        body.add(rank);
        body.add(typeLabel);
        body.add(type);
        body.add(constraintLabel);
        body.add(scp);

        btns.add(back);
        btns.add(confirm);

        bckg.add(title, BorderLayout.NORTH);
        bckg.add(body, BorderLayout.CENTER);
        bckg.add(btns, BorderLayout.SOUTH);

        c.add(bckg);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,350);
        setResizable(false);
        setLocationRelativeTo(c);
    }

    private void setSpinnerNotWritable(JSpinner spinner) {
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }
}
