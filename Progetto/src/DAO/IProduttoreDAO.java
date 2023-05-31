package DAO;

import Model.IProduttore;
import Model.Produttore;

import java.util.ArrayList;

public interface IProduttoreDAO {

    ArrayList<Produttore> findAll();

    Produttore getById(int id);

    Produttore getByName(String name);

    void remove(int idProduttore);

    void add(Produttore produttore);

    void update(Produttore produttore);

    boolean exists(Produttore p);
}
