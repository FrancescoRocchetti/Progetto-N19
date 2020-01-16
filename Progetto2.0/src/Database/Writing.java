package Database;

import java.util.ArrayList;

/**
 * classe con tutti metodi di update
 * @author Francesco Rocchetti
 */
public class Writing extends AbConnection {

    public Writing(){
        super();
    }

    public boolean writeProdotti(String nome, int prezzo, int n, int rating, String vincolo, String tipo){
        try{
            connectToDB();
            String temp="insert into Prodotti values(default,'"+nome+"','"+prezzo+"','"+n+"','" +rating+"','"+vincolo+"','"+tipo+"')";
            stmt.executeUpdate(temp);
            conn.close();
            return true;
        } catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    public boolean writeCaratteristica(int id, String tipo, String nome, String val){
        try{
            connectToDB();
            String temp="insert into Caratteristiche values("+id+",'"+tipo+"','"+nome+"','" +val+"')";
            stmt.executeUpdate(temp);
            conn.close();
            return true;
        } catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    public boolean writeTipo(String nome){
        try{
            connectToDB();
            String temp="insert into Tipi values('"+nome+"')";
            stmt.executeUpdate(temp);
            conn.close();
            return true;
        } catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    public void writeSale(ArrayList<Integer> arTot,ArrayList<Integer> arMiss, String info){
        String list = "tot:";
        for(Integer i :arTot){
            list = list + i + "," ;
        }
        list = list.substring(0,list.length()-1);
        list = list + " miss:";
        for(Integer i :arMiss){
            list = list + i + ",";
        }
        list = list.substring(0,list.length()-1);
        try{
            connectToDB();
            String temp="insert into Vendite values(default,now(),'"+list+"','"+ info +"')";
            stmt.executeUpdate(temp);
            conn.close();
        } catch (Exception e){
        System.err.println(e);
        }
    }
}
