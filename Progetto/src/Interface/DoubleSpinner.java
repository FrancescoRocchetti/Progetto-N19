package Interface;

import javax.swing.*;

/**
 * Classe che eredita JSpinner che permette di gestire
 * i numeri in virgola mobile
 *
 * @author Matteo Lucchini
 * @author Fabio Riganti
 */

public class DoubleSpinner extends JSpinner {

    private static final long serialVersionUID = 1L;
    private static final double STEP_RATIO = 0.1;

    private SpinnerNumberModel model;

    public DoubleSpinner(double val, double min, double max) {
        super();
        model = new SpinnerNumberModel(val, min, max, 0.1);
        this.setModel(model);
    }

    /**
     * Restituisce il valore all'interno del JSpinner
     *
     * @return double
     */
    public Double getDouble() {
        return (Double) getValue();
    }

}
