package Business;

import Model.Acquisto;
import Model.IAcquisto;
//FACTHORY METHOD PATTERN
//Concrete Creator
public class IAcquistoFactory {

    public IAcquisto getIAcquisto(String Type){

        if (Type.equalsIgnoreCase("ACQUISTO"))
            return new Acquisto();

        return null;
    }
}
