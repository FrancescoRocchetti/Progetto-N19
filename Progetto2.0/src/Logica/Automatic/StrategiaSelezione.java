package Logica.Automatic;

import Logica.BComponente;
import Logica.Componente;
import Logica.ComponentiSelezionati;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        int min = (int)((budget/cosaServe.length)*1.2);
        int max = (int)((budget/cosaServe.length)*0.5);
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
                return; //bisognerebbe aggiungere un messaggio d'errore
            }
        }
    }

    public ComponentiSelezionati getCs() {
        return cs;
    }

    @Override
    public abstract int compare(Componente o1, Componente o2);
}
