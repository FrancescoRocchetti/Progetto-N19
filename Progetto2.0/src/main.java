import Interface.WebInterface.MainWeb;

public class main {
    public static void main(String[] args) {
        try {
            MainWeb.main(8080);
        }
        catch (Exception e){
            System.err.println("---");
        }
    }
}
