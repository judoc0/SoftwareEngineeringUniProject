package DAO;

import Model.Disponibilita;

import java.util.ArrayList;

public interface IDisponibilitaDAO {

    ArrayList<Disponibilita> findAll();

    ArrayList<Disponibilita> find(int idMagazzino);

    Disponibilita getById(int id, int idMagazzino);

    void addQuantity(int idArticolo, int quantita, int idMagazzino);

    void rifornimento(Disponibilita d);

    void remove(int idArticolo);

    void addDisponibilita(Disponibilita disponibilita);

    void removebyMagazzino(int idArticolo, int idMagazzino);
}
