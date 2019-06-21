package InterfacingDB;

import java.net.URL;
import java.net.URLConnection;

/**
 * Classe usata per verificare se è possibile connettersi
 * a internet e al sito di quelli che ci forniscono il
 * Server SQL
 *
 * @author Francesco Rocchetti
 * @author Matteo Lucchini
 * @author Fabio Riganti
 *
 */

public class CheckInternet {

    public static boolean check() {
        try {
            URL url = new URL("https://remotemysql.com/");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //public static void main(String[] args){ System.out.println(check()); }
}
