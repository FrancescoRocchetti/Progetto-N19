package Logica.Automatic;

import Logica.BComponente;
import Logica.Componente;
import Logica.ComponentiSelezionati;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * classe astratta che contiene i metodi per la selezione dei componenti
 * @author Francesco Rocchetti
 */
public abstract class StrategiaSelezione implements Comparator<Componente> {

    private ComponentiSelezionati cs;
    private int budget;
    protected String[] cosaServe;

    protected StrategiaSelezione(int budget) {
        this.cs = new ComponentiSelezionati();
        this.budget = budget;
    }

    protected void cerca(){
        BComponente bc = new BComponente();
        int min=0;
        int max=200;
        try {
            min = (int) ((budget / cosaServe.length) * 1.2);
            max = (int) ((budget / cosaServe.length) * 0.5);
        }catch (Exception e){
            e.printStackTrace();
        }
        for(String s: cosaServe){
            ArrayList<Componente> temp = bc.compByType(s);

            ArrayList<Componente> temp2 = new ArrayList<>();

            for(Componente c: temp){
                if(c.getPrice()>min && c.getPrice()<max)
                    temp2.add(c);
            }

            temp = temp2;

            Collections.sort(temp,this);
            int i = 0;
            try {
                while(!cs.put(temp.get(i))){
                    i++;
                }
            } catch (Exception e){
                cs = new ComponentiSelezionati();
                return;
            }
        }
    }

    public ComponentiSelezionati getCs() {
        return cs;
    }

    @Override
    public abstract int compare(Componente o1, Componente o2);
}
