package DAO;

import Model.Articolo;

import java.io.File;
import java.util.ArrayList;

public interface IArticoloDAO {

    ArrayList<Articolo> findAll();

    Articolo getById(int id);

    boolean prodottoExists(Articolo a);

    boolean servizioExists(Articolo a);

    void remove(int idArticolo);

    void update(Articolo a);

    boolean articoloExists(Articolo articolo);

    void add(Articolo articolo);

    Articolo getByName(String name);

    int inserisciFoto(Articolo a, File foto);
}
