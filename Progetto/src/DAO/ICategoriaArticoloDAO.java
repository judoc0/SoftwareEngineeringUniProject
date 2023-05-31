package DAO;

import Model.CategoriaArticolo;

import java.util.ArrayList;

public interface ICategoriaArticoloDAO {

    ArrayList<CategoriaArticolo> findCategories();

    CategoriaArticolo findById(int id);

    CategoriaArticolo findByName(String name);

    void add(CategoriaArticolo c);

    void remove(int id);

}
