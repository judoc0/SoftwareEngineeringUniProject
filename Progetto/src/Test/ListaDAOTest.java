package Test;

import Business.HashClass;
import DAO.*;
import DBInterface.DbUser;
import Model.Cliente;
import Model.ListaArticoli;
import Model.PuntoVendita;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ListaDAOTest {
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

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = new ListaArticoli();
        listaArticoli.setNome("lista");
        listaArticoli.setStato(ListaArticoli.StatoLista.NON_PAGATA);
        listaArticoli.setIdPuntoVendita(puntoVendita.getIdPuntoVendita());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        listaArticoli.setData(formatter.format(date)+"");
        listaArticoli.setIdCliente(c.getIdUtente());
        listaDAO.add(listaArticoli);
    }

    @After
    public void tearDown() throws Exception {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");
        listaDAO.removeById(listaArticoli.getIdLista());

        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        clienteDAO.removeByIdUtente(u.getIdUtente());
        utenteDAO.removeById(u.getEmail());

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");
        iPuntoVenditaDAO.remove(puntoVendita);
    }

    @Test
    public void findByIdClienteAndNome() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");

        Assert.assertEquals(ListaArticoli.StatoLista.NON_PAGATA, listaArticoli.getStato());

    }

    @Test
    public void findById() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        List<ListaArticoli> listaArticoliList = listaDAO.getByIdCliente(u.getIdUtente());

        Assert.assertNotNull(listaArticoliList);
        Assert.assertEquals(ListaArticoli.StatoLista.NON_PAGATA, listaArticoliList.get(listaArticoliList.size()-1).getStato());

    }

    @Test
    public void getByIdClientePuntoVendita() {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        List<ListaArticoli> listaArticoliList = listaDAO.getByIdClientePuntoVendita(u.getIdUtente(),puntoVendita.getIdPuntoVendita());
        Assert.assertNotNull(listaArticoliList);
        Assert.assertEquals(ListaArticoli.StatoLista.NON_PAGATA, listaArticoliList.get(listaArticoliList.size()-1).getStato());

    }

    @Test
    public void getOneLista() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");

        listaArticoli = listaDAO.getOneLista(listaArticoli.getIdLista());
        Assert.assertEquals(ListaArticoli.StatoLista.NON_PAGATA, listaArticoli.getStato());

    }

    @Test
    public void findAll() {

        IListaDAO listaDAO = ListaDAO.getInstance();
        List<ListaArticoli> listaArticoliList = listaDAO.findAll();
        Assert.assertNotNull(listaArticoliList);
        Assert.assertEquals(ListaArticoli.StatoLista.NON_PAGATA, listaArticoliList.get(listaArticoliList.size()-1).getStato());

    }

    @Test
    public void listaExists() {

        IListaDAO listaDAO = ListaDAO.getInstance();
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();

        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");
        Assert.assertTrue(listaDAO.listaExists(listaArticoli));

    }

    @Test
    public void updateTest() {

        IListaDAO listaDAO = ListaDAO.getInstance();
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();

        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");
        listaArticoli.setStato(ListaArticoli.StatoLista.PAGATA);
        listaDAO.update(listaArticoli);
        listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");
        Assert.assertEquals(ListaArticoli.StatoLista.PAGATA, listaArticoli.getStato());

    }
}
