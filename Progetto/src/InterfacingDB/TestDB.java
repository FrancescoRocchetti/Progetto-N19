package InterfacingDB;
import java.io.IOException;

public class TestDB {
    public static void main(String[] args) throws IOException {
        Reading r = new Reading();
        String[] str;

        while((str = r.read()) != null){
            for(String stringa : str){
                System.out.println(stringa);
            }
            System.out.println("-----------------");
        }
        System.out.println(str+"\n-----------------"); //Se è null, tutto ok, altrimenti c'è qualche problema

        while((str = r.read(PCParts.CPU)) != null){
            for(String stringa : str){
                System.out.println(stringa);
            }
            System.out.println("-----------------");
        }
        System.out.println(str+"\n-----------------"); //Se è null, tutto ok, altrimenti c'è qualche problema
    }
}
