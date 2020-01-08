package Interface.WebInterface.Bean;

import java.io.Serializable;

public class TestBean implements Serializable {

    private int i;

    public int getI() {
        return i;
    }

    public void setI(String i) {
        this.i = Integer.parseInt(i);
    }
}
