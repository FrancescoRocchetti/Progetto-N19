package InterfacingDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginDB {
    private BufferedReader buffer;

    public LoginDB() throws IOException {
        buffer = new BufferedReader(new FileReader("Progetto/src/InterfacingDB/prova.csv"));
    }

    public void login(String user, String password){
        

    }


}
