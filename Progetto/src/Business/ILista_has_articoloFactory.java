package Business;

import Model.ArticoliLista;
import Model.ILista_has_articolo;

public class ILista_has_articoloFactory {

    public ILista_has_articolo getILista_has_articolo(String Type){

        if (Type.equalsIgnoreCase("ARTICOLILISTA"))
            return new ArticoliLista();

        return null;
    }
}
