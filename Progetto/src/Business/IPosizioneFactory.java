package Business;

import Model.IPosizione;
import Model.Posizione;
//FACTHORY METHOD PATTERN
//Concrete Creator
public class IPosizioneFactory {

    public IPosizione getIPosizione(String Type){

        if (Type.equalsIgnoreCase("POSIZIONE"))
            return new Posizione();

        return null;
    }
}
