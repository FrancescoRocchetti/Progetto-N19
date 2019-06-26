package InterfacingDB.DeprecatedClasses;

import Components.AbstractComponent;
import Components.PCParts;
import InterfacingDB.Login;
import InterfacingDB.Reader;

import java.util.ArrayList;

public class TestDB {
    public static void main(String[] args) {
        Reader r = new Reader();
        //Writing w = new Writing();
        Login l = new Login();
        ArrayList<AbstractComponent> list;

        list = r.read(PCParts.CPU);

        for (AbstractComponent arr1 : list) {
            System.out.println(arr1);
        }

        System.out.println(l.login("user", "password"));
        //System.out.println(r.getNumberOfRows());
        /*r.forceClose();
        w.write(PCParts.CPU,"BEPIS",5,999,5);
        w.updateConfirm(24,15);
        w.remove(24);
        */
    }
}