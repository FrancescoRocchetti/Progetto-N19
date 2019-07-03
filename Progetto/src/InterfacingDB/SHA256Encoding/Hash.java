package InterfacingDB.SHA256Encoding;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Hash {
    public static String getHash(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
