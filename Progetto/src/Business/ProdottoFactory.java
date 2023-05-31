package Business;

import Model.*;
//ABSTRACT FACTHORY PATTERN
//Concrete Factory
public class ProdottoFactory implements AbstractFactory{

    @Override
    public IProdotto crea() {
        return new Prodotto();
    }

    @Override
    public ICategoria creaCategoria() {
        return new CategoriaArticolo(CategoriaArticolo.tipoCategoria.PRODOTTO);
    }

    @Override
    public IProduttore creaProduttore() {
        return new Produttore();
    }
}
