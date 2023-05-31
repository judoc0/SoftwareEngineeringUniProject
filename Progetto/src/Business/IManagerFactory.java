package Business;

import Model.IManager;
import Model.Manager;

//FACTHORY METHOD PATTERN
//Concrete Creator
public class IManagerFactory {

    public IManager getIManager(String Type){

        if (Type.equalsIgnoreCase("MANAGER"))
            return new Manager();

        return null;
    }
}
