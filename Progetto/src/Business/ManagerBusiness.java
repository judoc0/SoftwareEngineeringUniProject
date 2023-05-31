package Business;
import DAO.*;
import Model.*;

import java.util.List;

public class ManagerBusiness {

    private static ManagerBusiness instance;     //SINGLETON PATTERN

    public static synchronized ManagerBusiness getInstance() {
        if (instance == null) instance = new ManagerBusiness();
        return instance;
    }

    private ManagerBusiness() {}

    public List<Manager> getAllManagers() {

        ManagerDAO mDAO = ManagerDAO.getInstance();

        // usare la classe ProdottoDAO per inviare la query al db che mi prende tutti i prodotti
        List<Manager> list = mDAO.findAll();

        for (Manager m : list) {
            m.setIdUtente(m.getIdUtente());
            m.setUsername(m.getUsername());
            m.setPassword(m.getPassword());
            m.setName(m.getName());
            m.setSurname(m.getSurname());
            m.setEmail(m.getEmail());
            m.setPhoneNumber(m.getPhoneNumber());
            m.setAge(m.getAge());
            m.setResidence(m.getResidence());
            m.setJob(m.getJob());
            m.setIdPuntoVendita(m.getIdPuntoVendita());
        }

        return list;
    }

    public Manager getById(int id) {

        ManagerDAO mDAO = ManagerDAO.getInstance();

        return mDAO.getById(id);
    }

    public Manager getManagerByPuntoVendita(int idPuntoVendita) {

        ManagerDAO mDAO = ManagerDAO.getInstance();

        return mDAO.getByIdPuntoVendita(idPuntoVendita);
    }

    public NewUtenteResponse newAccount(Manager manager) {

        NewUtenteResponse res = new NewUtenteResponse();
        res.setMessage("Errore non definito.");

        IUtenteDAO uDao = UtenteDAO.getInstance();
        IManagerDAO mDAO = ManagerDAO.getInstance();

        // 1. username già esistente
        if (uDao.userExists(manager.getUsername())) {
            res.setMessage("Utente già esistente.");
            res.setjOptionPane(0);
            res.setResult(NewUtenteResponse.newUtenteResult.USER_ALREADY_EXISTS);
            return res;
        } else {
            // 3. aggiungere i dati dall'utente
            uDao.add(manager);
            int id = UtenteBusiness.getInstance().getByEmail(manager.getEmail()).getIdUtente();
            manager.setIdUtente(id);
            mDAO.setManager(manager);
            res.setMessage("Nuovo manager creato");
            res.setjOptionPane(1);
            res.setUtente(UtenteBusiness.getInstance().getByEmail(manager.getEmail()));
            res.setResult(NewUtenteResponse.newUtenteResult.NEW_USER_OK);
        }

        return res;
    }
}
