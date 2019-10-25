package Database;

public class Deleting extends AbConnection {

    public Deleting() {
        super();
    }

    public boolean deleteProdotto(int id){
        try{
            connectToDB();
            String temp="DELETE FROM Prodotti where ID="+id;
            stmt.executeUpdate(temp);
            return true;
        } catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    public boolean deleteCaratteristica(int id, String nome){
        try{
            connectToDB();
            String temp="DELETE FROM Caratteristica where ID="+id+" and NOME like '"+nome+"'";
            stmt.executeUpdate(temp);
            return true;
        } catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

}
