package DAO;

import Model.Acquisto;

import java.util.ArrayList;

public interface IAcquistoDAO {

    ArrayList<Acquisto> findAll();

    ArrayList<Acquisto> findAcquistiPuntoVendita(int idPuntoVendita);

    Acquisto getById(int idAcquisto);

    int add(Acquisto acquisto);

    Acquisto getLastAcquisto();

    void removeAcquistoByIdCliente(int id);
}
