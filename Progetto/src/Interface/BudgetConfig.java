package Interface;

import Components.AbstractComponent;

import java.util.ArrayList;

public class BudgetConfig {
    int budget;
    public BudgetConfig(int budget) {
        this.budget = budget;
        getAutoConfiguration(this.budget);
    }

    /*
     * IDEA: ad ogni componente viene assegnata una percentuale del budget totale
     * in base all'importanza che ha il componente stesso
     */
    public void getAutoConfiguration(int b) {

    }
}
