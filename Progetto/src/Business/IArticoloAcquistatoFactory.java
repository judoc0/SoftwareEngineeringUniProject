package Business;

import Model.ArticoloAcquistato;
import Model.IArticoloAcquistato;
//FACTHORY METHOD PATTERN
//Concrete Creator
public class IArticoloAcquistatoFactory {

    public IArticoloAcquistato getIArticoloAcquistato(String Type){

        if (Type.equalsIgnoreCase("ARTICOLOACQUISTATO"))
            return new ArticoloAcquistato();

        return null;
    }
}
