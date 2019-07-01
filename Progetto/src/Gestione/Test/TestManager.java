package Gestione.Test;

import Gestione.Manager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestManager {

    @Test
    public void testCreateLogin() {
        Manager m = new Manager();

        assertThrows(NullPointerException.class, () -> {
            m.createLogin(null);
        });
    }
}
