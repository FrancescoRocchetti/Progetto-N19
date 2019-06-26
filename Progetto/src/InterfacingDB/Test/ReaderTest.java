package InterfacingDB.Test;

import Components.AbstractComponent;
import InterfacingDB.Reader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class ReaderTest {
    @Test
    public void testReadFromDB(){
        Reader r = new Reader();
        ArrayList<AbstractComponent> abs = r.read(null);
        abs.get(0);
    }
}
