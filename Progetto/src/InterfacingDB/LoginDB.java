package InterfacingDB;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

public class LoginDB {
    private String url;
    private String user;
    private String password;
    private Connection conn;


    public LoginDB(){
        url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7290902?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "sql7290902";
        password = "9Eb92Yn9qF";
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

    public void forceClose() throws SQLException {
        conn.close();
    }
}
