package Database;

/**
 * classe con tutti i metodi per eliminare qualcosa dal db
 */
public class Deleting extends AbConnection {

    public Deleting() {
        super();
    }

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
