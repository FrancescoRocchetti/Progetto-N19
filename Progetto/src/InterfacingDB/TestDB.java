package InterfacingDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestDB {
    public static void main(String[] args) throws IOException, SQLException {
        Reading r = new Reading();
        Writing w = new Writing();
        ArrayList<String[]> list;

        list = r.read(PCParts.CPU);

        System.out.println("-----------------");
        for(String[] arr1: list) {
            for (String arr2 : arr1) {
                System.out.println(arr2);
            }
            System.out.println("-----------------");
        }
        /*
        list = r.read(null);

        System.out.println("-----------------");
        for(String[] arr1: list) {
            for (String arr2 : arr1) {
                System.out.println(arr2);
            }
            System.out.println("-----------------");
        }

        w.write(PCParts.CPU,"BEPIS",5,999,5);
        */
    }
}
