package DAO;

import Model.Prodotto;
import Model.ProdottoComposito;

public interface IProdottoCompositoDAO {

    ProdottoComposito getById(int id);

    void add(ProdottoComposito pro, Prodotto p);

    void remove(int idProdotto);

    boolean compositoExists(int id);

    void deletefromComposito(ProdottoComposito p, Prodotto prodotto);

    void updateQuantity(ProdottoComposito p, Prodotto prodotto);

    boolean belongComposito(int id);

}
