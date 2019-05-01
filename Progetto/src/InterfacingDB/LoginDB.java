package InterfacingDB;

import java.io.IOException;
import java.sql.*;

public class LoginDB {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public LoginDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/progetto-n19?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        conn = DriverManager.getConnection(url,user,password);
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * from CREDENZIALI");
    }

    public boolean login(String user, String password) throws SQLException {
        while (rs.next()){
            if(rs.getString(1).equals(user) && rs.getString(2).equals(password)) {
                rs.close();
                stmt.close();
                conn.close();
                return true;
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        return false;
    }
}
