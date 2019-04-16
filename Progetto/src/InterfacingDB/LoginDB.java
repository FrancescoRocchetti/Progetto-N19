package InterfacingDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginDB {
    private BufferedReader buffer;

    public LoginDB() throws IOException {
        buffer = new BufferedReader(new FileReader("Progetto/src/InterfacingDB/credenziali.csv"));
    }

    public boolean login(String user, String password) throws IOException {
        String str;
        while ((str = buffer.readLine())!=null){
            if(str.split(";")[0].equals(user) && str.split(";")[1].equals(password)) {
                System.out.println("Login OK");
                return true;
            } else {
                System.err.println("Credenziali errate");
            }
        }
        return false;
    }


}
