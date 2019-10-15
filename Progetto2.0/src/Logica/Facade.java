package Logica;

import java.util.ArrayList;

public class Facade {

    //mostra la lista delle componenti
    public ArrayList<String> getListbyType(){
        return null;
    }

    //mostra i componenti selezionati
    public ArrayList<String> getSelectedList(){
        return null;
    }

    //aggiunge un componente alla lista dei selezionati
    public boolean selectComponent(){
        return true;
    }

    //rimuove un componente dalla lista dei selezionati
    public boolean removeComponent(){
        return true;
    }

    //login admin
    public boolean login(){
        return true;
    }

    //mostra i dettagli
    public ArrayList<String> getDetail(){
        return null;
    }

    //mostra il prezzo totale
    public int getTotPrice(){
        return 0;
    }

    //mostra la potenza totale
    public int getTotPower(){
        return 0;
    }

    //fine selezione
    public boolean confirm(){
        return true;
    }

    //rimuove componente dal DB
    public boolean dropComp(){
        return true;
    }

    //aggiorna componente dal DB
    public boolean updateComp(){
        return true;
    }

    ////

}
