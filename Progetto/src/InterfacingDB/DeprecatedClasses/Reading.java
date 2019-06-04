package InterfacingDB.DeprecatedClasses;

import InterfacingDB.PCParts;

import java.io.*;

/*
 *    E' una versione "primordiale" per leggere l'inventario da un semplice file CSV.
 *    Più tardi verrà fatta una versione più bella per leggere l'inventario da una tabella
 *    hostata su un server SQL.
 */

public class Reading {
    private BufferedReader buffer;
    private FileInputStream fin;

    public Reading() throws IOException {
        fin = new FileInputStream("Progetto/src/InterfacingDB/DeprecatedClasses/prova.csv");
        buffer = new BufferedReader(new InputStreamReader(fin));
    }

    public Reading(String path) throws IOException {
        fin = new FileInputStream(path);
        buffer = new BufferedReader(new InputStreamReader(fin));
    }

    public String[] read() throws IOException {
        String line = buffer.readLine();
        if (line != null)
            return line.split(";");
        returnTop();
        return null;
    }

    public String[] read(PCParts comp) throws IOException {
        String line = buffer.readLine();
        while (line != null) {
            if (line.split(";")[1].equals(comp.name()))
                return line.split(";");
            line = buffer.readLine();
        }
        returnTop();
        return null;
    }

    private void returnTop() throws IOException {
        fin.getChannel().position(0);
        buffer = new BufferedReader(new InputStreamReader(fin));
    }
}

