package DAO;

import Model.Fornitore;
import Model.Prodotto;
import Model.Servizio;

import java.util.ArrayList;

public interface IServizioDAO {

    ArrayList<Servizio> findAll();

    Servizio getById(int id);

    Servizio getByName(String name);

    void remove(int id);

    void addServizio(Servizio servizio);

    void update(Servizio servizio);

}
