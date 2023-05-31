package Business;

import DAO.*;
import Model.*;

public class UtenteBusiness {

    private static UtenteBusiness instance;     //SINGLETON PATTERN

    IUtenteDAO uDAO= UtenteDAO.getInstance();

    public enum Privilegio { CLIENT, MANAGE_SHOP, ADMIN_SYSTEM } //CLIENTE_CAN? ADD LISTA

    public static synchronized UtenteBusiness getInstance() {
        if (instance == null) instance = new UtenteBusiness();
        return instance;
    }

    private UtenteBusiness() {}

    public void setuDAO(IUtenteDAO uDAO) {
        this.uDAO = uDAO;
    }

    public LoginResponse login(String username, String password) {

        LoginResponse res = new LoginResponse();
        res.setMessage("Errore non definito.");

        IUtenteDAO uDao = UtenteDAO.getInstance();

        // 1. username non esistente
        if (!uDao.userExists(username)) {
            res.setMessage("L'utente indicato non esiste.");
            res.setjOptionPane(0);
            res.setResult(LoginResponse.LoginResult.USER_NOT_EXISTS);
            return res;
        }

        password = HashClass.getInstance().encrypt(password);

        // 2. username corretto, ma la pw è sbagliata
        if (!uDao.credentialsOk(username, password)) {
            res.setMessage("La password è errata.");
            res.setjOptionPane(0);
            res.setResult(LoginResponse.LoginResult.WRONG_PASSWORD);
            return res;
        }

        // 3. ottenere i dati dall'utente
        Utente u = uDao.getByUsername(username);
        //alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (u != null) {
            if (UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.CLIENT) && Cliente.StatoCliente.DISABILITATO.equals(ClienteBusiness.getInstance().getCliente(u.getIdUtente()).getStatoCliente())) {
                res.setMessage("Account disabilitato");
                res.setjOptionPane(0);
                res.setResult(LoginResponse.LoginResult.USER_DISABLED);
                return res;
            }
            else {
                res.setMessage("Benvenuto " + u.getName() + "!");
                res.setjOptionPane(1);
                res.setUtente(u);
                res.setResult(LoginResponse.LoginResult.LOGIN_OK);
            }
        }

        return res;
    }

    public boolean userCan(Utente u, Privilegio p) {

        IUtenteDAO uDao = UtenteDAO.getInstance();

        if (Privilegio.CLIENT.equals(p)) {
            // vediamo se u è un manager
            return uDao.clientExists(u);
        }
        if (Privilegio.MANAGE_SHOP.equals(p)) {
            // vediamo se u è un manager
            return uDao.managerExists(u);
        }
        if (Privilegio.ADMIN_SYSTEM.equals(p)) {
            // vediamo se u è un amministratore
            return uDao.administratorExists(u);
        }

        return false;
    }

    public Utente getById(int id) {
        // usare la classe ProdottoDAO per inviare la query al db che mi prende tutti i prodotti

        Utente u = uDAO.findById(id);

        u.setIdUtente(u.getIdUtente());
        u.setUsername(u.getUsername());
        u.setPassword(u.getPassword());
        u.setName(u.getName());
        u.setSurname(u.getSurname());
        u.setEmail(u.getEmail());
        u.setPhoneNumber(u.getPhoneNumber());
        u.setAge(u.getAge());
        u.setResidence(u.getResidence());
        u.setJob(u.getJob());

        return u;
    }

    public Utente getByEmail(String email) {

        Utente u = uDAO.findByEmail(email);

        u.setIdUtente(u.getIdUtente());
        u.setUsername(u.getUsername());
        u.setPassword(u.getPassword());
        u.setName(u.getName());
        u.setSurname(u.getSurname());
        u.setEmail(u.getEmail());
        u.setPhoneNumber(u.getPhoneNumber());
        u.setAge(u.getAge());
        u.setResidence(u.getResidence());
        u.setJob(u.getJob());

        return u;
    }

    public void removeById(Utente u) {

        // Usando la eliminazione a cascata nel database mysql per le tabelle coinvolte non abbiamo bisogno di eliminare 'manualmente' tutte le righe contenenti la foreign key idArticolo

        /*if (userCan(u, Privilegio.CLIENT)) {
            FeedbackBusiness.getInstance().removeCommentoByCliente(u.getIdUtente());
            ArticoloAcquistatoBusiness.getInstance().removeAcquistoByIdCliente(u.getIdUtente());
            AcquistoBusiness.getInstance().removeAcquistoByIdCliente(u.getIdUtente());
            ListaArticoliBusiness.getInstance().removeAllListeCliente(u.getIdUtente());
            ClienteBusiness.getInstance().removeCliente(u.getIdUtente());

        }*/
        uDAO.removeById(u.getEmail());
    }



}
