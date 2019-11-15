package Interface;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;

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
    private JComboBox constraint;
    private JButton back;
    private JButton confirm;

    public AddProduct() {
        c = getContentPane();
        bckg = new JPanel(new BorderLayout());
        title = new JPanel(new GridLayout(1,1));
        body = new JPanel(new GridLayout(6,2));
        btns = new JPanel(new GridLayout(1,2));

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
        constraint = new JComboBox(); // Vincoli già presenti nel DB
        back = new JButton("Annulla");
        confirm = new JButton("Aggiungi");

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
        body.add(constraint);

        btns.add(back);
        btns.add(confirm);

        bckg.add(title, BorderLayout.NORTH);
        bckg.add(body, BorderLayout.CENTER);
        bckg.add(btns, BorderLayout.SOUTH);

        c.add(bckg);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,250);
        setLocationRelativeTo(c);
    }

    private void setSpinnerNotWritable(JSpinner spinner) {
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }
}
