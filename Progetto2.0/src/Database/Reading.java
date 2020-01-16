package Database;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * classe con tutti i metodi per leggere dal db
 * @author Francesco Rocchetti
 */
public class Reading extends AbConnection{

    public Reading(){
        super();
    }

    /**
     * legge i prodotti dal db
     * @param type tipo che si vuole leggere (se null legge tutto)
     * @return
     */
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

    public ArrayList<ArrayList<String>> readProdottoSpecifico(String nome){
        try {
            connectToDB();
            rs = stmt.executeQuery("select * from Prodotti where NOME='" + nome + "'");

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

    public ArrayList<ArrayList<String>> readCaratteristicaByID(int id){
        try {
            connectToDB();
            rs = stmt.executeQuery("select * from Caratteristiche where ID='" + id + "'");

            ArrayList<ArrayList<String>> list = new ArrayList<>();
            while (rs.next()) {
                ArrayList<String> temp = new ArrayList<>();
                for(int i=1; i<=NumeroCaratteristiche; i++){
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

    public ArrayList<ArrayList<String>> readAllCaratteristiche(){
        try {
            connectToDB();
            rs = stmt.executeQuery("select * from Caratteristiche");

            ArrayList<ArrayList<String>> list = new ArrayList<>();
            while (rs.next()) {
                ArrayList<String> temp = new ArrayList<>();
                for(int i=1; i<=NumeroCaratteristiche; i++){
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

    public ArrayList<String> readAllTypeFromCaratteristiche(){
        try {
            connectToDB();
            rs = stmt.executeQuery("select distinct NOME from Caratteristiche");

            ArrayList<String> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getString(1));
            }

            conn.close();
            return list;
        } catch (SQLException e) {
            System.err.println(e);
            forceClose();
            return null;
        }
    }

    public ArrayList<String> readTypes(){
        try {
            connectToDB();
            rs = stmt.executeQuery("select NOME from Tipi");

            ArrayList<String> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getString(1));
            }

            conn.close();
            return list;
        } catch (SQLException e) {
            System.err.println(e);
            forceClose();
            return null;
        }
    }

    public boolean login(String user, String password) {

        try {
            connectToDB();
            String temp = "select count(*) from login where UserName ='" + user + "' and PsWord ='" + password+"'";

            rs = stmt.executeQuery(temp);
            rs.next();
            int ck = rs.getInt(1);
            conn.close();

            if (ck > 0)
                return true;
            return false;

        } catch (SQLException e) {
            System.err.println(e);
            forceClose();
            return false;
        }
    }

    public ArrayList<ArrayList<String>> readSales(){
        try {
            connectToDB();
            rs = stmt.executeQuery("select * from Vendite");

            ArrayList<ArrayList<String>> list = new ArrayList<>();
            while (rs.next()) {
                ArrayList<String> temp = new ArrayList<>();
                for(int i=1; i<=4; i++){
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
