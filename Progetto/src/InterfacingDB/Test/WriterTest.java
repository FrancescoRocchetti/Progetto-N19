package InterfacingDB.Test;

import InterfacingDB.Writer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class WriterTest {
    @Test
    public void testWriteToDB() {
        Writer w = new Writer();
        assertThrows(NullPointerException.class, () -> {
            w.write(null, "BEPIS", 4, 3, 56);
        });
    }

}
