package InterfacingDB;

import java.sql.*;
import java.util.ArrayList;

public class Reading {
    private static final int ELEMENTS = 5;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String url;
    private String user;
    private String password;


    public Reading() throws SQLException {
        url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7290902?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "sql7290902";
        password = "9Eb92Yn9qF";
    }

    public ArrayList<String[]> read(PCParts comp) throws SQLException {
        connectToDB(comp);
        ArrayList<String[]> list = new ArrayList<>();
        String str[];
        while(rs.next()){
            str = new String[ELEMENTS];
            for(int i = 1; i<ELEMENTS+1 ;i++)
                str[i-1] = rs.getString(i);
            list.add(str);
        }
        conn.close();
        return list;
    }

    private void connectToDB(PCParts comp) throws SQLException {
        conn = DriverManager.getConnection(url,user,password);
        stmt = conn.createStatement();
        if (comp == null) rs = stmt.executeQuery("SELECT * from INVENTARIO");
        else rs = stmt.executeQuery("select * from INVENTARIO where TIPO='"+comp.name()+"'");
    }
}
