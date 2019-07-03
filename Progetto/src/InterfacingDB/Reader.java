package InterfacingDB;

import Components.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe che permette di leggere il DB
 *
 * @author Fabio Riganti
 */

public class Reader {
    private static final int ELEMENTS = 6;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String url;
    private String user;
    private String password;


    public Reader() {
        url = "jdbc:mysql://37.59.55.185:3306/ViPqoAojwM";
        user = "ViPqoAojwM";
        password = "dmHj8vdaCo";
    }

    /**
     * Restituisce in una lista i componenti del DB in base al tipo di
     * componente interessato
     *
     * @param comp: se nullo, restituisce tutti i componenti
     * @return Lista di AbstractComponent se la lettura è andata a buon fine,
     * altrimenti nullo
     */
    public ArrayList<AbstractComponent> read(PCParts comp) {
        try {
            connectToDB();
            if (comp == null) rs = stmt.executeQuery("SELECT * from INVENTARIO");
            else rs = stmt.executeQuery("select * from INVENTARIO where TIPO='" + comp.name() + "'");
            ArrayList<AbstractComponent> list = new ArrayList<>();
            String[] str;
            while (rs.next()) {
                str = new String[ELEMENTS];
                for (int i = 1; i < ELEMENTS + 1; i++)
                    str[i - 1] = rs.getString(i);
                list.add(getComponent(str));
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            forceClose();
            return null;
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

    private void connectToDB() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
    }


    private AbstractComponent getComponent(String[] str) {
        switch (str[1].toUpperCase()) {
            case "CASE":
                return new CASE(str);
            case "COOLER":
                return new COOLER(str);
            case "CPU":
                return new CPU(str);
            case "GPU":
                return new GPU(str);
            case "MOBO":
                return new MOBO(str);
            case "OS":
                return new OS(str);
            case "PSU":
                return new PSU(str);
            case "RAM":
                return new RAM(str);
            case "STORAGE":
                return new STORAGE(str);
            case "ALTRO":
                return new ALTRO(str);
            default:
                return null;
        }
    }
}
