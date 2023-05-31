package DAO;

import Model.Utente;

import java.util.ArrayList;

public interface IUtenteDAO {

    Utente findById(int id);

    Utente findByEmail(String email);

    ArrayList<Utente> findAll();

    int add(Utente utente);

    int removeById(String email);

    int update(Utente utente);

    boolean userExists(String username);

    boolean credentialsOk(String username, String password);

    Utente getByUsername(String username);

    boolean managerExists(Utente u);

    boolean administratorExists(Utente u);

    boolean clientExists(Utente u);
}
