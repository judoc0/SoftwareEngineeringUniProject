package Test;

import Business.HashClass;
import DAO.*;
import DBInterface.DbUser;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AcquistoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Cliente c = new Cliente();
        c.setUsername("valentino");
        c.setPassword(HashClass.getInstance().encrypt("12345"));
        c.setName("Valentino");
        c.setSurname("Rossi");
        c.setEmail("valentino.rossi@gmail.com");
        c.setPhoneNumber("0000");
        c.setAge(42);
        c.setResidence("Lecce");
        c.setJob("attaccante");
        utenteDAO.add(c);
        c.setIdUtente(utenteDAO.findByEmail("valentino.rossi@gmail.com").getIdUtente());


        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = new PuntoVendita();
        puntoVendita.setCitta("Acaya");
        iPuntoVenditaDAO.add(puntoVendita);
        puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        c.setIdPuntoVendita(puntoVendita.getIdPuntoVendita());
        c.setCanalePreferito(Cliente.CanalePreferito.SMS);
        clienteDAO.setCliente(c);

        IAcquistoDAO iAcquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = new Acquisto();
        acquisto.setIdCliente(c.getIdUtente());
        acquisto.setIdPuntoVendita(puntoVendita.getIdPuntoVendita());
        iAcquistoDAO.add(acquisto);

    }

    @After
    public void tearDown() throws Exception {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IAcquistoDAO iAcquistoDAO = AcquistoDAO.getInstance();
        iAcquistoDAO.removeAcquistoByIdCliente(u.getIdUtente());

        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        clienteDAO.removeByIdUtente(u.getIdUtente());
        utenteDAO.removeById(u.getEmail());

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");
        iPuntoVenditaDAO.remove(puntoVendita);
    }

    @Test
    public void getIdAcquisto() {

        IAcquistoDAO iAcquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = iAcquistoDAO.getLastAcquisto();

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        Assert.assertEquals(u.getIdUtente(), acquisto.getIdCliente());

    }

    @Test
    public void findAcquistiPuntoVendita() {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        IAcquistoDAO iAcquistoDAO = AcquistoDAO.getInstance();
        List<Acquisto> acquistoList = iAcquistoDAO.findAcquistiPuntoVendita(puntoVendita.getIdPuntoVendita());
        Assert.assertNotNull(acquistoList);
        Assert.assertEquals(puntoVendita.getIdPuntoVendita(), acquistoList.get(acquistoList.size()-1).getIdPuntoVendita());

    }

    @Test
    public void getById() {

        IAcquistoDAO iAcquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = iAcquistoDAO.getLastAcquisto();

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");
        acquisto = iAcquistoDAO.getById(acquisto.getIdAcquisto());

        Assert.assertEquals(u.getIdUtente(),acquisto.getIdCliente());

    }

    @Test
    public void findAll() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IAcquistoDAO iAcquistoDAO = AcquistoDAO.getInstance();
        List<Acquisto> acquistoList = iAcquistoDAO.findAll();
        Assert.assertNotNull(acquistoList);
        Assert.assertEquals(u.getIdUtente(), acquistoList.get(acquistoList.size()-1).getIdCliente());

    }



}
