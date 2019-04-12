package InterfacingDB;
import com.sun.jdi.TypeComponent;

import java.io.*;

/*
 *    E' una versione "primordiale" per leggere l'inventario da un semplice file CSV.
 *    Più tardi verrà fatta una versione più bella per leggere l'inventario da una tabella
 *    hostata su un server SQL.
 */

public class Reading{
  private BufferedReader buffer;

  public Reading() throws IOException {
    buffer = new BufferedReader(new FileReader("prova.csv"));
  }

  public Reading(String str) throws IOException {
    buffer = new BufferedReader(new FileReader(str));
  }

  public String[] read() throws IOException {
    String line = buffer.readLine();
    if(line!=null)
      return line.split(";");
    buffer.close();
    return null;
  }

  public String[] read(TypeComponent comp) throws IOException {
    String line = buffer.readLine();
    while (line != null) {
      if (line.split(";")[1].equals(comp.name()))
        return line.split(";");
      line = buffer.readLine();
    }
    buffer.close();
    return null;
  }
}

