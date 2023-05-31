package Business;

import Model.IPosizione;
import Model.IPuntoVendita_has_articolo;
import Model.Posizione;
import Model.PuntoVendita_has_articolo;

public class IPuntoVendita_has_articoloFactory {

    public IPuntoVendita_has_articolo getPuntoVendita_has_articolo(String Type){

        if (Type.equalsIgnoreCase("PUNTOVENDITA_HAS_ARTICOLO"))
            return new PuntoVendita_has_articolo();

        return null;
    }
}
