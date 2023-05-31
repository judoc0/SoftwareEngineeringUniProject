package Test;

import Business.HashClass;
import DAO.*;
import DBInterface.DbUser;
import Model.Manager;
import Model.PuntoVendita;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ManagerDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Manager m = new Manager();
        m.setUsername("valentino");
        m.setPassword(HashClass.getInstance().encrypt("12345"));
        m.setName("Valentino");
        m.setSurname("Rossi");
        m.setEmail("valentino.rossi@gmail.com");
        m.setPhoneNumber("0000");
        m.setAge(42);
        m.setResidence("Lecce");
        m.setJob("attaccante");
        utenteDAO.add(m);
        m.setIdUtente(utenteDAO.findByEmail("valentino.rossi@gmail.com").getIdUtente());


        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = new PuntoVendita();
        puntoVendita.setCitta("Acaya");
        iPuntoVenditaDAO.add(puntoVendita);
        puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");
        m.setIdPuntoVendita(puntoVendita.getIdPuntoVendita());

        IManagerDAO managerDAO = ManagerDAO.getInstance();
        managerDAO.setManager(m);
    }

    @After
    public void tearDown() throws Exception {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");


        IManagerDAO managerDAO = ManagerDAO.getInstance();
        managerDAO.remove(u.getIdUtente());
        utenteDAO.removeById(u.getEmail());

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");
        iPuntoVenditaDAO.remove(puntoVendita);
    }
    @Test
    public void findById() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = managerDAO.getById(u.getIdUtente());

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        Assert.assertEquals(puntoVendita.getIdPuntoVendita(),manager.getIdPuntoVendita());
    }

    @Test
    public void findAll() {

        IManagerDAO managerDAO = ManagerDAO.getInstance();
        List<Manager> managerList = managerDAO.findAll();
        Assert.assertNotNull(managerList);

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        Assert.assertEquals(puntoVendita.getIdPuntoVendita(), managerList.get(managerList.size()-1).getIdPuntoVendita());
    }

    @Test
    public void findByIdPuntoVendita() {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        IManagerDAO managerDAO = ManagerDAO.getInstance();
        Manager manager = managerDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita());

        Assert.assertEquals(puntoVendita.getIdPuntoVendita(), manager.getIdPuntoVendita());
    }


}
