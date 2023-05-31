package Business;

import DAO.ClienteDAO;
import DAO.IClienteDAO;
import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.*;
import java.util.List;

public class ClienteBusiness {

    private static ClienteBusiness instance;     //SINGLETON PATTERN

    public static synchronized ClienteBusiness getInstance() {
        if (instance == null) instance = new ClienteBusiness();
        return instance;
    }

    private ClienteBusiness() {}

    public List<Cliente> getAllClienti() {

        ClienteDAO cDAO = ClienteDAO.getInstance();

        List<Cliente> list = cDAO.findAll();

        for (Cliente c : list) {
            c.setIdUtente(c.getIdUtente());
            c.setIdPuntoVendita(c.getIdPuntoVendita());
            c.setCanalePreferito(c.getCanalePreferito());
            c.setStatoCliente(c.getStatoCliente());
        }

        return list;
    }

    public Cliente getCliente(int id) {
        // usare la classe ProdottoDAO per inviare la query al db che mi prende tutti i prodotti

        ClienteDAO cDao = ClienteDAO.getInstance();

        return cDao.getById(id);
    }

    public List<Cliente> getClientiByPuntoVendita(int idPuntoVendita) {
        // usare la classe ProdottoDAO per inviare la query al db che mi prende tutti i prodotti

        ClienteDAO cDao = ClienteDAO.getInstance();

        List<Cliente> list = cDao.findClientiPuntoVendita(idPuntoVendita);

        for (Cliente c : list) {
            c.setIdUtente(c.getIdUtente());
            c.setUsername(c.getUsername());
            c.setPassword(c.getPassword());
            c.setName(c.getName());
            c.setSurname(c.getSurname());
            c.setEmail(c.getEmail());
            c.setPhoneNumber(c.getPhoneNumber());
            c.setAge(c.getAge());
            c.setResidence(c.getResidence());
            c.setJob(c.getJob());
            c.setIdPuntoVendita(c.getIdPuntoVendita());
            c.setCanalePreferito(c.getCanalePreferito());
            c.setStatoCliente(c.getStatoCliente());
        }

        return list;
    }

    public int updateStatoCliente(Cliente cliente) {

        ClienteDAO cDao = ClienteDAO.getInstance();

        return cDao.updateStatoCliente(cliente);
    }

    public void removeCliente(int idCliente) {

        ClienteDAO cDao = ClienteDAO.getInstance();
        cDao.removeByIdUtente(idCliente);
    }

    public NewUtenteResponse newAccount(Cliente cliente) {

        NewUtenteResponse res = new NewUtenteResponse();
        res.setMessage("Errore non definito.");

        IUtenteDAO uDao = UtenteDAO.getInstance();
        IClienteDAO cDAO = ClienteDAO.getInstance();

        // 1. username già esistente
        if (uDao.userExists(cliente.getUsername())) {
            res.setMessage("Utente già esistente.");
            res.setjOptionPane(0);
            res.setResult(NewUtenteResponse.newUtenteResult.USER_ALREADY_EXISTS);
            return res;
        } else {
            // 3. aggiungere i dati dall'utente
            uDao.add(cliente);
            int id = UtenteBusiness.getInstance().getByEmail(cliente.getEmail()).getIdUtente();
            cliente.setIdUtente(id);
            cDAO.setCliente(cliente);
            res.setMessage("Nuovo cliente creato");
            res.setjOptionPane(1);
            res.setUtente(UtenteBusiness.getInstance().getByEmail(cliente.getEmail()));
            res.setResult(NewUtenteResponse.newUtenteResult.NEW_USER_OK);
        }

        return res;
    }

}
