package InterfacingDB;

import java.sql.*;

public class Writing {
    private String url;
    private String user;
    private String password;

    public Writing() throws SQLException {
        url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7290902?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "sql7290902";
        password = "9Eb92Yn9qF";
    }

    public void write(PCParts part, String d, int q, int p, int r) throws SQLException {
        Connection conn = DriverManager.getConnection(url,user,password);
        String query = "INSERT INTO INVENTARIO (TIPO, DESCRIZIONE, QUANTITA, PREZZO, RANK)\n" +
                "VALUES (?,?,?,?,?);";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, part.name());
        preparedStmt.setString(2, d);
        preparedStmt.setInt(3, q);
        preparedStmt.setInt(4, p);
        preparedStmt.setInt(5, r);

        preparedStmt.execute();

        conn.close();
    }
}
