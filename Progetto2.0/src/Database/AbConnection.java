package Database;

import java.sql.*;

/**
 * classe astratta che definisce i metodi standard per la comunicazione con il db
 */
public abstract class AbConnection {

    protected Connection conn;
    protected Statement stmt;
    protected ResultSet rs;
    protected String url;
    protected String user;
    protected String password;
    protected final int NumeroProdotti=7;
    protected final int NumeroCaratteristiche=4;

    public AbConnection() {
        url = "jdbc:mysql://remotemysql.com:3306/D08vqOei7k";
        user = "D08vqOei7k";
        password = "eVWtADjm12";
    }

    protected void connectToDB() throws SQLException {
        try {
            conn = DriverManager.getConnection(url,user,password);
            stmt = conn.createStatement();
        }
        catch(Exception e){
            System.err.println(e);
        }
    }

    protected void forceClose() {
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
