package InterfacingDB.DeprecatedClasses;


import InterfacingDB.DeprecatedClasses.Hash;

public class TestSha {
    public static void main(String[] args) {
        String input = "password";
        System.out.println(Hash.getHash(input));
    }
}
