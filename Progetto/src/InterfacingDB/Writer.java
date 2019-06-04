package InterfacingDB;

import java.sql.*;

public class Writer {
    private String url;
    private String user;
    private String password;
    private Connection conn;

    public Writer() {
        url = "jdbc:mysql://34.65.95.40:3306/Progetto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "utente";
        password = "prova";
    }

    public boolean write(PCParts part, String d, int q, int p, int r){
        try{
            conn = DriverManager.getConnection(url, user, password);
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
            return true;
        } catch (SQLException e) {
            forceClose();
            return false;
        }
    }


    /*public void remove(int cod) throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        String query = "DELETE FROM INVENTARIO WHERE CODICE = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1,cod);

        preparedStmt.execute();

        conn.close();
    }*/

    public boolean update(int cod, int quantità){
        try{
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT QUANTITA AS q FROM INVENTARIO WHERE CODICE = '" + cod + "'");
            rs.next();
            int q = rs.getInt("q");
            q += quantità;

            String query = "UPDATE INVENTARIO SET QUANTITA = " + q + " WHERE CODICE = ?;";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, cod);

            preparedStmt.execute();

            conn.close();
            return true;
        } catch (SQLException e) {
            forceClose();
            return false;
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
