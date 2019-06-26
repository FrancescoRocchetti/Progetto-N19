package InterfacingDB.Test;

import Components.PCParts;
import InterfacingDB.Reader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReaderTest {
    @Test
    public void testReadFromDB(){
        Reader r = new Reader();
        r.read(null);
    }
}
