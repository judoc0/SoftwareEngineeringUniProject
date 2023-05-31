package Business;

import Model.*;
//ABSTRACT FACTHORY PATTERN
//Concrete Factory
public class ServizioFactory implements AbstractFactory{

    @Override
    public IServizio crea() {
        return new Servizio();
    }

    @Override
    public ICategoria creaCategoria() {
        return new CategoriaArticolo(CategoriaArticolo.tipoCategoria.SERVIZIO);
    }

    @Override
    public IFornitore creaProduttore() {
        return new Fornitore();
    }
}
