package Business;

import Model.IMagazzino;
import Model.IPuntoVendita;
import Model.Magazzino;
import Model.PuntoVendita;

//FACTHORY METHOD PATTERN
//Concrete Creator
public class IMagazzinoFactory {

    public IMagazzino getIMagazzino(String Type){

        if (Type.equalsIgnoreCase("MAGAZZINO"))
            return new Magazzino();

        return null;
    }
}
