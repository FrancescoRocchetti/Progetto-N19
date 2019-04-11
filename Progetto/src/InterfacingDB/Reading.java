package InterfacingDB;
import java.io.*

public class Reading {
  private BufferedReader buffer;
  public Reading(){
    buffer = new BufferedReader(new FileReader("prova.csv"));
  }
  
  public String[] read(){
    String line = buffer.ReadLine();
    if(line!=null){
      return line.split(";");
    } else {
      return null;
    }
  }
}
