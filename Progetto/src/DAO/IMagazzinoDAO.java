package DAO;

import Model.Magazzino;

import java.util.ArrayList;

public interface IMagazzinoDAO {

    ArrayList<Magazzino> findAll();

    Magazzino getById(int id);

    void add(Magazzino magazzino);

    void remove(int idPuntoVendita);

    Magazzino getByIdPuntoVendita(int idPuntoVendita);
}
