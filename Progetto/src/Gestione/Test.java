package Gestione;

import Components.*;

import java.lang.reflect.Constructor;

public class Test {
    public static void main(String[] args) {
        String nome="Components.CPU";
        AbstractComponent c;
        String att="0;CPU;Intel Core i7 7700K_4.20_4_8_91_64_Y_1151_Y;5;275;4";

        String[] at= att.split(";");

        System.out.println(Validation.check(at));
/*
        try{
            Class cl = Class.forName(nome);
           // System.out.println(cl);
            Constructor con = cl.getDeclaredConstructor(String[].class);
            c = (AbstractComponent) con.newInstance((Object)at);
            System.out.println(c);
            System.out.println(c.getResource());
            System.out.println(at.length);
        }
        catch (Exception e){
            e.printStackTrace();
        }
*/
    }

}
