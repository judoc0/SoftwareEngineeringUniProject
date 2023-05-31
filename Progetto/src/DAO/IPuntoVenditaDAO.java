package DAO;

import Model.PuntoVendita;

import java.util.ArrayList;

public interface IPuntoVenditaDAO {

    ArrayList<PuntoVendita> findAll();

    PuntoVendita getById(int id);

    PuntoVendita getByCity(String city);

    void add(PuntoVendita puntoVendita);

    void remove(PuntoVendita puntoVendita);

    void update(PuntoVendita puntoVendita);

    boolean puntoVenditaExists(String citta);
}
