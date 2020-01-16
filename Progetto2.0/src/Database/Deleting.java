package Database;

/**
 * classe con tutti i metodi per eliminare qualcosa dal db
 * @author Francesco Rocchetti
 */
public class Deleting extends AbConnection {

    public Deleting() {
        super();
    }

    /**
     * cancella un prodotto e eventuali sue caratteristiche dal db
     * @param id
     * @return
     */
    public boolean deleteProdotto(int id){
        try{
            connectToDB();
            String temp="DELETE FROM Prodotti where ID="+id;
            stmt.executeUpdate(temp);
            stmt.executeUpdate("DELETE FROM Caratteristiche where ID="+id);
            conn.close();
            return true;
        } catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    /**
     * elimina una singola caratteristica dal db
     * (cancella tutte le caratteristiche con lo stesso nome di un particolare id)
     * @param id del prodotto
     * @param nome della caratteristica
     * @return
     */
    public boolean deleteCaratteristica(int id, String nome){
        try{
            connectToDB();
            String temp="DELETE FROM Caratteristiche where ID="+id+" and NOME like '"+nome+"'";
            stmt.executeUpdate(temp);
            conn.close();
            return true;
        } catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    /**
     * cancella un tipo di prodotto
     * (cancella solo nella tabella dei tipi i prodotti rimangono)
     * @param nome
     * @return
     */
    public boolean deleteTipo(String nome){
        try{
            connectToDB();
            String temp="DELETE FROM Tipi where Nome='"+nome+"'";
            stmt.executeUpdate(temp);
            conn.close();
            return true;
        } catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

}
