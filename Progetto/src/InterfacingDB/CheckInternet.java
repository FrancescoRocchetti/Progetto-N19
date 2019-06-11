package InterfacingDB;

import java.net.InetAddress;

public class CheckInternet {

    public static boolean check() {
        try {
            String ipAddress = "34.65.95.40";
            InetAddress inet = InetAddress.getByName(ipAddress);
            //System.out.println("Sending Ping Request to " + ipAddress);
            if (inet.isReachable(5000)) return true;
            else return false;
        } catch (Exception e) {
            return false;
        }
    }

    //public static void main(String[] args){ System.out.println(check()); }
}
