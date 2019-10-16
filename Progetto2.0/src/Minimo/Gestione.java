package Minimo;

import java.util.ArrayList;

/**
 * per adesso questa è la classe che gestisce tutto
 * (lettura, aggiunta dei componenti alla lista)
 */

public class Gestione {

    private Read r;
    private BComponente bc;
    private Selected s;
    private ArrayList<Componente> alc;

    public Gestione(){
        r = new Read();
        bc = new BComponente();
        s = new Selected();
        alc = new ArrayList<>();
    }

    public void leggi(String type){
        alc = new ArrayList<>();
        for(String[] s : r.readP(type)) {
            Componente temp = bc.build(s);
            alc.add(temp);
        }
    }

    public void add(int i){
        s.put(alc.get(i));
    }

    public String printSelected(){
        return s.toString();
    }

    public String getV(){
        return s.printVincoli();
    }

    public int printCost(){
        return s.getTotCost();
    }

    public int printPower(){
        return s.getPower();
    }

    public boolean checkFinale(){
        return s.checkFinale();
    }

    @Override
    public String toString() {
        String temp = "";
        for(Componente c: alc){
            temp = temp + c.toString() + "\n";
        }
        return "Gestione{\n"+ temp +"}";
    }
}
