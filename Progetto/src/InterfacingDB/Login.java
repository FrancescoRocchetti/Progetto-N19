package InterfacingDB;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

public class Login {
    private String url;
    private String user;
    private String password;
    private Connection conn;


    public Login(){
        url = "jdbc:mysql://34.65.95.40:3306/Progetto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "utente";
        password = "prova";
    }

    public boolean login(String user, String password) throws SQLException {
        conn = DriverManager.getConnection(url,this.user,this.password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from UTENTI");
        while (rs.next()){
            if(rs.getString(1).equals(user) && rs.getString(2).equals(getHash(password))) {
                conn.close();
                return true;
            }
        }
        conn.close();
        return false;
    }

    private static String getHash(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        } catch(Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public void forceClose() {
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println("Già chiuso.");
        }
    }
}
