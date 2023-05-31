package Test;

import Business.HashClass;
import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import DBInterface.DbUser;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class UtenteDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = new Utente();
        u.setUsername("valentino");
        u.setPassword(HashClass.getInstance().encrypt("12345"));
        u.setName("Valentino");
        u.setSurname("Rossi");
        u.setEmail("valentino.rossi@gmail.com");
        u.setPhoneNumber("0000");
        u.setAge(42);
        u.setResidence("Lecce");
        u.setJob("attaccante");
        utenteDAO.add(u);
    }

    @After
    public void tearDown() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.removeById("valentino.rossi@gmail.com");
    }
    @Test
    public void findByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByEmail("valentino.rossi@gmail.com");
        Assert.assertEquals("Valentino", utente.getName());
    }

    @Test
    public void updateTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = new Utente();
        u.setUsername("valentino");
        u.setPassword(HashClass.getInstance().encrypt("12345"));
        u.setName("Valentino");
        u.setSurname("Rossi");
        u.setEmail("valentino.rossi@gmail.com");
        u.setPhoneNumber("0000");
        u.setAge(41);
        u.setResidence("Lecce");
        u.setJob("attaccante");
        utenteDAO.update(u);
        u = utenteDAO.findByEmail("valentino.rossi@gmail.com");
        Assert.assertEquals(41, u.getAge());
    }

    @Test
    public void findAllTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        List<Utente> utentes = utenteDAO.findAll();
        Assert.assertNotNull(utentes);
        Assert.assertEquals("Valentino", utentes.get(utentes.size()-1).getName());
    }

    @Test
    public void userExists() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Assert.assertTrue(utenteDAO.userExists("valentino"));
    }

    @Test
    public void credentialsOk() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Assert.assertTrue(utenteDAO.credentialsOk("valentino", HashClass.getInstance().encrypt("12345")));
    }

    @Test
    public void managerExists() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.getByUsername("valentino");
        Assert.assertFalse(utenteDAO.clientExists(u));
    }

    @Test
    public void administratorExists() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.getByUsername("valentino");
        Assert.assertFalse(utenteDAO.administratorExists(u));
    }

    @Test
    public void clientExists() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.getByUsername("valentino");
        Assert.assertFalse(utenteDAO.clientExists(u));
    }

    @Test
    public void getByUsername() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.getByUsername("valentino");
        Assert.assertEquals("valentino.rossi@gmail.com", u.getEmail());
    }
}
