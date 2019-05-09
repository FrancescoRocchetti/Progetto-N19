package InterfacingDB;

import java.sql.*;

public class Reading {
    private static final int ELEMENTS = 5;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private boolean done;

    public Reading() throws SQLException {
        done = true;
        String url = "jdbc:mysql://localhost:3306/progetto-n19?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        conn = DriverManager.getConnection(url,user,password);
        stmt = conn.createStatement();
    }

    public String[] read() throws SQLException {
        isDone(null);
        String str[] = new String[ELEMENTS];
        if (rs.next()) {
            for(int i = 1; i<ELEMENTS+1 ;i++)
                str[i-1] = rs.getString(i);
            return str;
        }
        done = true;
        return null;
    }

    public String[] read(PCParts comp) throws SQLException {
        isDone(comp);
        String str[] = new String[ELEMENTS];
        while(rs.next()){
            for(int i = 1; i<ELEMENTS+1 ;i++)
                str[i-1] = rs.getString(i);
            return str;
        }
        done = true;
        return null;
    }

    private void isDone(PCParts comp) throws SQLException {
        if (done){
            if (comp == null) rs = stmt.executeQuery("SELECT * from INVENTARIO");
            else rs = stmt.executeQuery("select * from INVENTARIO where TIPO='"+comp.name()+"'");
            done = false;
        }
    }
}
