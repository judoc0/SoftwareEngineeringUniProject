package DAO;

import Model.Fornitore;
import Model.Servizio;

import java.util.ArrayList;

public interface IFornitoreDAO {

    ArrayList<Fornitore> findAll();

    Fornitore getById(int id);

    Fornitore getByName(String name);

    void remove(int idFornitore);

    void add(Fornitore fornitore);

    void update(Fornitore fornitore);

    boolean exists(Fornitore p);
}
