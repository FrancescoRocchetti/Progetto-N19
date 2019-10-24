package Database;

import java.sql.SQLException;
import java.util.ArrayList;

public class Reading extends AbConnection{

    private int NumeroProdotti=7;

    public Reading(){
        super();
    }

    public ArrayList<ArrayList<String>> readProdotti(String type){
        try {
            connectToDB();
            if (type == null) rs = stmt.executeQuery("SELECT * from Prodotti");
            else rs = stmt.executeQuery("select * from Prodotti where TIPO='" + type + "'");

            ArrayList<ArrayList<String>> list = new ArrayList<>();
            while (rs.next()) {
                ArrayList<String> temp = new ArrayList<>();
                for(int i=1; i<=NumeroProdotti; i++){
                    temp.add(rs.getString(i));
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
}
