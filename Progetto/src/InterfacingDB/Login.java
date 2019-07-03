package InterfacingDB;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

/**
 * Classe usata per effettuare il accedere alla sezione
 * Admin del programma
 *
 * @author Fabio Riganti
 */

public class Login {
    private String url;
    private String user;
    private String password;
    private Connection conn;


    public Login() {
        url = "jdbc:mysql://37.59.55.185:3306/ViPqoAojwM";
        user = "ViPqoAojwM";
        password = "dmHj8vdaCo";
    }

    private static String getHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Funzione che viene richiamata da GestoreOperazioni per
     * effettuare il login alla piattaforma come Admin
     *
     * @param user
     * @param password
     */
    public boolean login(String user, String password) {
        try {
            conn = DriverManager.getConnection(url, this.user, this.password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from UTENTI");
            while (rs.next()) {
                if (rs.getString(1).equals(user) && rs.getString(2).equals(getHash(password))) {
                    conn.close();
                    return true;
                }
            }
            return false;
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


}
