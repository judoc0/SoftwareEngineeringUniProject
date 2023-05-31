package DAO;

import Model.Utente;

import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class MockUtenteDAO implements IUtenteDAO{

    //db in-memory
    ArrayList<Utente> list = new ArrayList<>();
    ArrayList<Utente> manager = new ArrayList<>();
    ArrayList<Utente> amministratori = new ArrayList<>();

    @Override
    public Utente findById(int id) {
        for (Utente u:list) if(id == u.getIdUtente()) return u;
        return null;
    }

    @Override
    public Utente findByEmail(String email) {
        for (Utente u:list) if(email.equals(u.getEmail())) return u;
        return null;
    }

    @Override
    public ArrayList<Utente> findAll() {
        return list;
    }

    @Override
    public int add(Utente utente) {
        list.add(utente);
        return list.size();
    }

    @Override
    public int removeById(String email) {

        for (Utente u:list)
            if (email.equals(u.getEmail())) {
                list.remove(u);
                break;
            }
        return list.size();
    }

    @Override
    public int update(Utente utente) {

        Utente u = findByEmail(utente.getEmail());
        if (u != null) u=utente;
        return list.size();
    }

    @Override
    public boolean userExists(String username) {

        for (Utente u:list) if(username.equals(u.getUsername())) return true;
        return false;
    }

    @Override
    public boolean credentialsOk(String username, String password) {

        for (Utente u:list) if(username.equals(u.getUsername()) && password.equals(u.getPassword())) return true;
        return false;
    }

    @Override
    public Utente getByUsername(String username) {

        for (Utente u:list) if (username.equals(u.getUsername())) return u;
        return null;
    }

    @Override
    public boolean managerExists(Utente u) {

        for (Utente u1:list) if (u.getUsername().equals(u1.getUsername())) return true;
        return false;
    }

    @Override
    public boolean administratorExists(Utente u) {
        for (Utente u1:amministratori) if (u.getUsername().equals(u1.getUsername())) return true;
        return false;
    }

    @Override
    public boolean clientExists(Utente u) {
        return false;
    }

    public ArrayList<Utente> getManager() {
        return manager;
    }

    public ArrayList<Utente> getAmministratori() {
        return amministratori;
    }
}
