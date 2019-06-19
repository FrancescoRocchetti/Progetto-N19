package InterfacingDB;

import Components.PCParts;

import java.sql.*;

public class Writer {
    private String url;
    private String user;
    private String password;
    private Connection conn;

    public Writer() {
        url = "jdbc:mysql://37.59.55.185:3306/ViPqoAojwM";
        user = "ViPqoAojwM";
        password = "dmHj8vdaCo";
    }

    public boolean write(PCParts part, String d, int q, int p, int r){
        try{
            conn = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO `INVENTARIO` (`TIPO`, `DESCRIZIONE`, `QUANTITA`, `PREZZO`, `RANK`) " +
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
            System.err.println(e.getMessage());
            return false;
        }
    }


    public boolean remove(int cod){
        try{
            conn = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM INVENTARIO WHERE CODICE = ?;";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,cod);

            preparedStmt.execute();

            conn.close();
            return true;
        } catch (SQLException e) {
            forceClose();
            return false;
        }
    }

    public boolean update(int cod, int quantità){
        try{
            conn = DriverManager.getConnection(url, user, password);
            String query = "UPDATE INVENTARIO SET QUANTITA = " + quantità + " WHERE CODICE = ?;";

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

    public boolean buy(int cod){
        try{
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT QUANTITA AS q FROM INVENTARIO WHERE CODICE = '" + cod + "'");
            rs.next();
            int q = rs.getInt("q");

            String query = "UPDATE INVENTARIO SET QUANTITA = " + (q-1) + " WHERE CODICE = ?;";

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
