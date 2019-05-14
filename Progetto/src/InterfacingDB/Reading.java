package InterfacingDB;

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
        url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7290902?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "sql7290902";
        password = "9Eb92Yn9qF";
    }

    public ArrayList<String[]> read(PCParts comp) throws SQLException {
        connectToDB();
        if (comp == null) rs = stmt.executeQuery("SELECT * from INVENTARIO");
        else rs = stmt.executeQuery("select * from INVENTARIO where TIPO='"+comp.name()+"'");
        ArrayList<String[]> list = new ArrayList<>();
        String str[];
        while(rs.next()){
            str = new String[ELEMENTS];
            for(int i = 1; i<ELEMENTS+1; i++)
                str[i-1] = rs.getString(i);
            list.add(str);
        }
        conn.close();
        return list;
    }

    public ArrayList<Integer> getNumberOfRows() throws SQLException {
        ArrayList<Integer> cods = new ArrayList<>();
        connectToDB();
        rs = stmt.executeQuery("SELECT CODICE AS cod from INVENTARIO");
        while(rs.next())
            cods.add(rs.getInt("cod"));
        conn.close();
        return cods;
    }

    private void connectToDB() throws SQLException {
        conn = DriverManager.getConnection(url,user,password);
        stmt = conn.createStatement();
    }
    public void forceClose() throws SQLException {
        conn.close();
    }
}
