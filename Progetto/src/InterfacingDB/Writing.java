package InterfacingDB;

import java.sql.*;

public class Writing {
    private Connection conn;
    private Statement stmt;

    public Writing() throws SQLException {
        String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7290902?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "sql7290902";
        String password = "9Eb92Yn9qF";
        conn = DriverManager.getConnection(url,user,password);
        stmt = conn.createStatement();
    }
    public void write(PCParts part, String d, int q, int p, int r) throws SQLException {
        ResultSet rs = stmt.executeQuery
                ("INSERT INTO INVENTARIO (TIPO, DESCRIZIONE, QUANTITA, PREZZO, RANK)\n" +
                "VALUES ("+part.name()+","+d+","+q+","+p+","+r+");");
    }
}
