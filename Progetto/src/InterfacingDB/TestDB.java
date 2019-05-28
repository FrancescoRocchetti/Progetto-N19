package InterfacingDB;

import Components.AbstractComponent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestDB {
    public static void main(String[] args) throws IOException, SQLException {
        Reading r = new Reading();
        //Writing w = new Writing();
        LoginDB l = new LoginDB();
        ArrayList<AbstractComponent> list;

        list = r.read(PCParts.CPU);

        for(AbstractComponent arr1: list) {
            System.out.println(arr1);
        }

        System.out.println(l.login("user","password"));
        //System.out.println(r.getNumberOfRows());
        /*r.forceClose();
        w.write(PCParts.CPU,"BEPIS",5,999,5);
        w.update(24,15);
        w.remove(24);
        */
    }
}