package Business;

import Model.IPuntoVendita;
import Model.PuntoVendita;
//FACTHORY METHOD PATTERN
//Concrete Creator
public class IPuntoVenditaFactory {

    public IPuntoVendita getIPuntoVendita(String Type){

        if (Type.equalsIgnoreCase("PUNTOVENDITA"))
            return new PuntoVendita();

        return null;
    }
}
