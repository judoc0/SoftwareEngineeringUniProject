package Business;

import Model.IListaArticoli;
import Model.ListaArticoli;
//FACTHORY METHOD PATTERN
//Concrete Creator
public class IListaArticoliFactory {

    public IListaArticoli getIListaArticoli(String Type){

        if (Type.equalsIgnoreCase("LISTAARTICOLI"))
            return new ListaArticoli();

        return null;
    }
}
