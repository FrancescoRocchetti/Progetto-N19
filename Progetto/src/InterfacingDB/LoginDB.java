package InterfacingDB;

import java.io.IOException;
import java.sql.*;

public class LoginDB {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public LoginDB() throws SQLException {
        String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7290902?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "sql7290902";
        String password = "9Eb92Yn9qF";
        conn = DriverManager.getConnection(url,user,password);
        stmt = conn.createStatement();
    }

    public boolean login(String user, String password) throws SQLException {
        rs = stmt.executeQuery("SELECT * from UTENTI");
        while (rs.next()){
            if(rs.getString(1).equals(user) && rs.getString(2).equals(password)) {
                return true;
            }
        }
        return false;
    }
}
