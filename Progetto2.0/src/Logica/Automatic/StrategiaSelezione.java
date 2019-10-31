package Logica.Automatic;

import Database.Facade;
import Logica.Componente;

import java.util.ArrayList;

public interface StrategiaSelezione {
    ArrayList<Componente> selziona(int prezzo, Facade fdb);
}
