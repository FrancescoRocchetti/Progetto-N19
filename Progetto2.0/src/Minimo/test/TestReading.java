package Minimo.test;

import java.util.ArrayList;

public class TestReading {
    public static void main(String[] args) {
        Reading r = new Reading();
        ArrayList<String> als = r.read(null);
        for(String s : als){
            System.out.println(s);
        }
    }
}
