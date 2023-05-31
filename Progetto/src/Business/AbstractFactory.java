package Business;

import Model.IArticolo;
import Model.IAzienda;
import Model.ICategoria;

//ABSTRACT FACTHORY PATTERN
//Abstract Factory
public interface AbstractFactory {

    IArticolo crea();
    ICategoria creaCategoria();
    IAzienda creaProduttore();
}
