package InterfacingDB;
import java.io.*;

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
    if(line!=null){
      return line.split(";");
    } else {
      return null;
    }
  }
}
