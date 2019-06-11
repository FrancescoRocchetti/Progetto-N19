package Interface;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DoubleSpinner extends JSpinner {

    private static final long serialVersionUID = 1L;
    private static final double STEP_RATIO = 0.1;

    private SpinnerNumberModel model;

    public DoubleSpinner(double val, double min, double max) {
        super();
        // Model setup
        model = new SpinnerNumberModel(val, min, max, 0.1);
        this.setModel(model);
    }

    /**
     * Returns the current value as a Double
     */
    public Double getDouble() {
        return (Double)getValue();
    }

}
