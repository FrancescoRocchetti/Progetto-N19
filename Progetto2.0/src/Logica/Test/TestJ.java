package Logica.Test;

import Database.Facade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJ {

    Facade fdb;

    @BeforeEach
    void before(){
        fdb = Facade.getInstance();
    }

    @Test
    void test1(){
        assertNotNull(fdb.readComp());
    }

    @Test
    void test2(){
        assertTrue(fdb.login("admin","test"));
    }

    @Test
    void test3(){
        assertFalse(fdb.login("a","t"));
    }
}
