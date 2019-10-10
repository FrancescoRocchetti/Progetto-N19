package Minimo;

import java.io.*;
import java.util.ArrayList;

/**
 * per adesso funziona con lettura da file
 */

public class Read {

    public ArrayList<String[]> readP (String type){

        ArrayList<String[]> list = new ArrayList<>();

        try
        {
            File file=new File("C:\\Users\\rokka\\Downloads\\Progetto-N19\\Progetto2.0\\Prodotti.txt");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                if(line.split(";")[6].equalsIgnoreCase(type)|| type == null)
                    list.add(line.split(";"));
            }
            fr.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return list;

    }

    public ArrayList<String[]> readC (String id){

        ArrayList<String[]> list = new ArrayList<>();

        try
        {
            File file=new File("C:\\Users\\rokka\\Downloads\\Progetto-N19\\Progetto2.0\\Caratteristiche.txt");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                if(line.split(";")[0].equalsIgnoreCase(id)|| id == null)
                    list.add(line.split(";"));
            }
            fr.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return list;

    }

}
