package Database;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * classe con tutti i metodi di update
 * @author Francesco Rocchetti
 */
public class Updating extends AbConnection {

    public Updating() {
        super();
    }

    /**
     * aggiorna la quantità dei componenti acquistati
     * @param ids
     * @return ArrayList dei componenti che non sono disponibili al momento
     */
    public ArrayList<Integer> buy(ArrayList<Integer> ids){
        ArrayList<Integer> temp = new ArrayList<>();

        for(Integer id:ids){
            if(!buySingle(id))
                temp.add(id);
        }
        return temp;
    }

    private boolean buySingle(int id){
        try {
            connectToDB();
            rs = stmt.executeQuery("select * from Prodotti where ID=" + id );

            rs.next();
            int n = Integer.parseInt(rs.getString(4)) - 1;
            if(n<0){
                return false;
            }

            stmt.executeUpdate("UPDATE Prodotti SET NUMERO = "+n+" WHERE ID ="+id);
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            forceClose();
            return false;
        }
    }

    public boolean updateN(int id, int n){
        try {
            connectToDB();
            stmt.executeUpdate("UPDATE Prodotti SET NUMERO = "+n+" WHERE ID ="+id);
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
            stmt.executeUpdate("UPDATE Prodotti SET PREZZO = "+price+" WHERE ID ="+id);
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
            stmt.executeUpdate("UPDATE Prodotti SET RATING = "+rating+" WHERE ID ="+id);
            conn.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            forceClose();
            return false;
        }
    }

}
