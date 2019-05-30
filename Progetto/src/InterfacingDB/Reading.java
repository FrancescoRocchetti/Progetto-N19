package InterfacingDB;

import Components.*;

import java.sql.*;
import java.util.ArrayList;

public class Reading {
    private static final int ELEMENTS = 6;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String url;
    private String user;
    private String password;


    public Reading(){
        url = "jdbc:mysql://34.65.95.40:3306/Progetto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "utente";
        password = "prova";
    }

    public ArrayList<AbstractComponent> read(PCParts comp) throws SQLException {
        connectToDB();
        if (comp == null) rs = stmt.executeQuery("SELECT * from INVENTARIO");
        else rs = stmt.executeQuery("select * from INVENTARIO where TIPO='"+comp.name()+"'");
        ArrayList<AbstractComponent> list = new ArrayList<>();
        String str[];
        while(rs.next()){
            str = new String[ELEMENTS];
            for(int i = 1; i<ELEMENTS+1; i++)
                str[i-1] = rs.getString(i);
            list.add(getComponent(str));
        }
        conn.close();
        return list;
    }

    /*public ArrayList<Integer> getNumberOfRows() throws SQLException {
        ArrayList<Integer> cods = new ArrayList<>();
        connectToDB();
        rs = stmt.executeQuery("SELECT CODICE AS cod from INVENTARIO");
        while(rs.next())
            cods.add(rs.getInt("cod"));
        conn.close();
        return cods;
    }*/

    private void connectToDB() throws SQLException {
        conn = DriverManager.getConnection(url,user,password);
        stmt = conn.createStatement();
    }

    public void forceClose() {
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println("Già chiuso.");
        }
    }

    public int getQuantityByID(int id) throws SQLException {
        int quantity;
        connectToDB();
        rs = stmt.executeQuery("SELECT QUANTITA AS q FROM INVENTARIO WHERE CODICE = '" + id + "'");
        rs.next();
        quantity = rs.getInt("q");
        conn.close();
        return quantity;
    }

    private AbstractComponent getComponent(String[] str){
        switch(str[1].toUpperCase()){
            case "CASE": return new CASE(str);
            case "COOLER": return new COOLER(str);
            case "CPU": return new CPU(str);
            case "GPU": return new GPU(str);
            case "MOBO": return new MOBO(str);
            case "OS": return new OS(str);
            case "PSU": return new PSU(str);
            case "RAM": return new RAM(str);
            case "STORAGE": return new STORAGE(str);
            case "ALTRO": return new ALTRO(str);
            default: return null;
        }
    }
}
