package InterfacingDB.Test;

import InterfacingDB.Reader;
import org.junit.jupiter.api.Test;


public class ReaderTest {
    @Test
    public void testReadFromDB(){
        Reader r = new Reader();
        r.read(null);
    }
}
