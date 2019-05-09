package InterfacingDB;

import java.sql.*;

public class Reading {
    private static final int ELEMENTS = 5;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public Reading() throws SQLException {
        String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7290902?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "sql7290902";
        String password = "9Eb92Yn9qF";
        conn = DriverManager.getConnection(url,user,password);
        stmt = conn.createStatement();
    }

    public String[] read() throws SQLException {
        querySQL(null);
        String str[] = new String[ELEMENTS];
        if (rs.next()) {
            for(int i = 1; i<ELEMENTS+1 ;i++)
                str[i-1] = rs.getString(i);
            return str;
        }
        return null;
    }

    public String[] read(PCParts comp) throws SQLException {
        querySQL(comp);
        String str[] = new String[ELEMENTS];
        while(rs.next()){
            for(int i = 1; i<ELEMENTS+1 ;i++)
                str[i-1] = rs.getString(i);
            return str;
        }
        return null;
    }

    private void querySQL(PCParts comp) throws SQLException {
        if (comp == null) rs = stmt.executeQuery("SELECT * from INVENTARIO");
        else rs = stmt.executeQuery("select * from INVENTARIO where TIPO='"+comp.name()+"'");

    }
}
