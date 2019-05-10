package InterfacingDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestDB {
    public static void main(String[] args) throws IOException, SQLException {
        Reading r = new Reading();
        //Writing w = new Writing();
        LoginDB l = new LoginDB();
        ArrayList<String[]> list;

        list = r.read(PCParts.CPU);

        for(String[] arr1: list) {
            for (String arr2 : arr1) {
                System.out.print(arr2+"\t");
            }
            System.out.println();
        }

        System.out.print(l.login("user","password"));
        //w.write(PCParts.CPU,"BEPIS",5,999,5);
    }
}
