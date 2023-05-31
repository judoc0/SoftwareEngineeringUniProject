package DAO;

import Model.Manager;
import Model.Utente;

import java.util.ArrayList;

public interface IManagerDAO {

    ArrayList<Manager> findAll();

    Manager getById(int id);

    Manager getByIdPuntoVendita(int id);

    void setManager(Manager manager);

    void remove(int idUtente);
}
