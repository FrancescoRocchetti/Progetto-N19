package InterfacingDB;

import Components.PCParts;

import java.sql.*;

/**
 * Classe che permette di manipolare il DB
 *
 * @author Fabio Riganti
 */

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

    /**
     * Scrive i componenti nel DB
     *
     * @param part: tipo di componente
     * @param d:    descrizione componente
     * @param q:    quantità del componente
     * @param p:    prezzo del componente
     * @param r:    rank del componente
     * @return true se l'inserimento è andato a buon fine,
     * altrimenti false
     * */
    public boolean write(PCParts part, String d, int q, int p, int r) {
        try {

            conn = DriverManager.getConnection(url, user, password);

            if (isCompAlreadyIn(d.split("_")[0])) {
                conn.close();
                return false;
            }

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

    /**
     * Rimuove i componenti dal DB
     *
     * @param cod: codice prodotto
     * @return true se la rimozione è andata a buon fine,
     * altrimenti false
     */
    public boolean remove(int cod) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM INVENTARIO WHERE CODICE = ?;";

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

    /**
     * Aggiorna la quantità dei componenti nel DB
     *
     * @param cod:     codice prodotto
     * @param quantita
     * @return true se l'aggiornamento è andato a buon fine,
     * altrimenti false
     */
    public boolean updateQuantity(int cod, int quantita) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "UPDATE INVENTARIO SET QUANTITA = " + quantita + " WHERE CODICE = ?;";

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

    /**
     * Aggiorna il prezzo dei componenti nel DB
     *
     * @param cod:  codice prodotto
     * @param price
     * @return true se l'aggiornamento è andato a buon fine,
     * altrimenti false
     */
    public boolean updatePrice(int cod, int price) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "UPDATE INVENTARIO SET PREZZO = " + price + " WHERE CODICE = ?;";

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

    /**
     * Aggiorna la quantità dei componenti nel DB
     *
     * @param cod: codice prodotto
     * @return true se l'acquisto è andato a buon fine,
     * altrimenti false
     */
    public boolean buy(int cod) {
        try {
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT QUANTITA AS q FROM INVENTARIO WHERE CODICE = '" + cod + "'");
            rs.next();
            int q = rs.getInt("q");

            String query = "UPDATE INVENTARIO SET QUANTITA = " + (q - 1) + " WHERE CODICE = ?;";

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


    /**
     * Consente di forzare la chiusura della connessione in caso di errore
     * durante la comunicazione
     */
    public void forceClose() {
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println("Già chiuso.");
        }
    }

    private boolean isCompAlreadyIn(String name) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM INVENTARIO WHERE DESCRIZIONE LIKE '" + name + "%'");
        return rs.next();
    }
}
