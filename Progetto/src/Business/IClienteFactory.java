package Business;

import Model.Cliente;
import Model.ICliente;
import Model.IUtente;
import Model.Utente;

//FACTHORY METHOD PATTERN
//Concrete Creator
public class IClienteFactory {

    public ICliente getICliente(String Type){

        if (Type.equalsIgnoreCase("CLIENTE"))
            return new Cliente();

        return null;
    }
}
