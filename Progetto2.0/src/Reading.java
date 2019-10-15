import java.sql.*;
import java.util.ArrayList;

public class Reading {

    private final static int NP = 7;
    protected String DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String url;

    public Reading() {
        url = "jdbc:mysql://192.168.1.105:3306/q?useSSL=false&allowPublicKeyRetrieval=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Rome";
    }

    public ArrayList<String> read(String type){
        try {
            connectToDB();
            if (type == null) rs = stmt.executeQuery("SELECT * from PRODOTTI");
            else rs = stmt.executeQuery("select * from PRODOTTI where TIPO='" + type + "'");
            ArrayList<String> list = new ArrayList<>();
            String[] str;
            while (rs.next()) {

                //list.add(rs.getString(2));
                String temp ="";
                for(int i=1; i<=NP; i++){
                    temp = temp.concat(rs.getString(i)+" ");
                }
                list.add(temp);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            System.err.println(e);
            forceClose();
            return null;
        }
    }

    private void connectToDB() throws SQLException {
        try {
            conn = DriverManager.getConnection(url,"finley","password");
            stmt = conn.createStatement();
        }
        catch(Exception e){
            System.err.println(e);
        }
    }

    public void forceClose() {
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Già chiuso.");
        }
    }
}
