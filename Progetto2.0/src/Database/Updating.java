package Database;

import java.sql.SQLException;
import java.util.ArrayList;

public class Updating extends AbConnection {

    public Updating() {
        super();
    }

    public boolean buy(ArrayList<Integer> ids){
        for(Integer id:ids){
            if(!buySingle(id))
                return false;
        }
        return true;
    }

    private boolean buySingle(int id){
        try {
            connectToDB();
            rs = stmt.executeQuery("select * from Prodotti where ID=" + id );

            int n = Integer.parseInt(rs.getString(4)) - 1;

            stmt.executeUpdate("UPDATE 'Prodotti' SET 'NUMERO' = "+n+" WHERE 'ID' ="+id);
            conn.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            forceClose();
            return false;
        }
    }

    public boolean updateN(int id, int n){
        try {
            connectToDB();
            stmt.executeUpdate("UPDATE 'Prodotti' SET 'NUMERO' = "+n+" WHERE 'ID' ="+id);
            conn.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            forceClose();
            return false;
        }
    }

    public boolean updatePrice(int id, int price){
        try {
            connectToDB();
            stmt.executeUpdate("UPDATE 'Prodotti' SET 'PREZZO' = "+price+" WHERE 'ID' ="+id);
            conn.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            forceClose();
            return false;
        }
    }

    public boolean updateRating(int id, int rating){
        try {
            connectToDB();
            stmt.executeUpdate("UPDATE 'Prodotti' SET 'RATING' = "+rating+" WHERE 'ID' ="+id);
            conn.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            forceClose();
            return false;
        }
    }

}
