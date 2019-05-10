package InterfacingDB;

import java.io.IOException;
import java.sql.*;

public class LoginDB {
    private String url;
    private String user;
    private String password;


    public LoginDB() throws SQLException {
        url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7290902?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "sql7290902";
        password = "9Eb92Yn9qF";
    }

    public boolean login(String user, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(url,this.user,this.password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from UTENTI");
        while (rs.next()){
            if(rs.getString(1).equals(user) && rs.getString(2).equals(password)) {
                conn.close();
                return true;
            }
        }
        conn.close();
        return false;
    }
}
