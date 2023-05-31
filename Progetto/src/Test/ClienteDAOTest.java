package Test;

import Business.HashClass;
import DAO.*;
import DBInterface.DbUser;
import Model.Cliente;
import Model.PuntoVendita;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ClienteDAOTest {
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
    }

    @After
    public void tearDown() throws Exception {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");


        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        clienteDAO.removeByIdUtente(u.getIdUtente());
        utenteDAO.removeById(u.getEmail());

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");
        iPuntoVenditaDAO.remove(puntoVendita);
    }
    @Test
    public void findById() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.getById(u.getIdUtente());

        Assert.assertEquals(Cliente.StatoCliente.ATTIVO,cliente.getStatoCliente());
    }

    @Test
    public void findAll() {

        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        List<Cliente> clienteList = clienteDAO.findAll();
        Assert.assertNotNull(clienteList);
        Assert.assertEquals(Cliente.StatoCliente.ATTIVO, clienteList.get(clienteList.size()-1).getStatoCliente());
    }

    @Test
    public void findClientiPuntoVendita() {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        List<Cliente> clienteList = clienteDAO.findClientiPuntoVendita(puntoVendita.getIdPuntoVendita());

        Assert.assertNotNull(clienteList);
        Assert.assertEquals(Cliente.StatoCliente.ATTIVO, clienteList.get(clienteList.size()-1).getStatoCliente());
    }

    @Test
    public void updateTest() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        Cliente cliente = clienteDAO.getById(u.getIdUtente());

        cliente.setStatoCliente(Cliente.StatoCliente.DISABILITATO);
        clienteDAO.updateStatoCliente(cliente);

        cliente = clienteDAO.getById(u.getIdUtente());

        Assert.assertEquals(Cliente.StatoCliente.DISABILITATO,cliente.getStatoCliente());


    }
}
