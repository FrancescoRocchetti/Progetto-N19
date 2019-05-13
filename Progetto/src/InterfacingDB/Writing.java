package InterfacingDB;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class Writing {
    private String url;
    private String user;
    private String password;

    public Writing(){
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

    public void update(int cod, int quantità) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        String query = "UPDATE INVENTARIO SET QUANTITA = ? WHERE CODICE = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1,quantità);
        preparedStmt.setInt(2,cod);

        preparedStmt.executeUpdate();

        conn.close();
    }

    public void remove(int cod) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        String query = "DELETE FROM INVENTARIO WHERE CODICE = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1,cod);

        preparedStmt.execute();

        conn.close();
    }

    public int getRowsNumber() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        String query = "SELECT COUNT (CODICE) FROM INVENTARIO;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();
        ResultSet rs = preparedStmt.executeQuery(query);

        conn.close();
        System.out.println(rs);
        return Integer.parseInt(query);
    }
}
