package Database;

import java.sql.SQLException;
import java.util.ArrayList;

public class Reading extends AbConnection{

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

}
