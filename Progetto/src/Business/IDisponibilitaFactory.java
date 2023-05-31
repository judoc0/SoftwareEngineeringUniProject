package Business;

import Model.Disponibilita;
import Model.IDisponibilita;
//FACTHORY METHOD PATTERN
//Concrete Creator
public class IDisponibilitaFactory {

    public IDisponibilita getIDisponibilita(String Type){

        if (Type.equalsIgnoreCase("DISPONIBILITA"))
            return new Disponibilita();

        return null;
    }
}
