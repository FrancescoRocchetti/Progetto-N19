package InterfacingDB;

import java.net.URL;
import java.net.URLConnection;

public class CheckInternet {

    public static boolean check(){
        try
        {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public static void main(String[] args){ System.out.println(check()); }
}
