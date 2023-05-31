package DAO;

import Model.Prodotto;

import java.util.ArrayList;

public interface IProdottoDAO {

    ArrayList<Prodotto> findAll();

    Prodotto getById(int id);

    Prodotto getByName(String name);

    void removeProdotto(int idArticolo);

    void addProdotto(Prodotto prodotto);

    void updateProdotto(Prodotto prodotto);

}
