package Database;

public class Writing extends AbConnection {

    public Writing(){
        super();
    }

    public boolean writeProdotti(String nome, int prezzo, int n, int rating, String vincolo, String tipo){
        try{
            connectToDB();
            String temp="insert into Prodotti values(default,'"+nome+"','"+prezzo+"','"+n+"','" +rating+"','"+vincolo+"','"+tipo+"')";
            stmt.executeUpdate(temp);
            return true;
        } catch (Exception e){
            System.err.println(e);
            return false;
        }
    }
}
