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


public class Lista_has_articoloDAOTest {
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
        listaArticoli = listaDAO.getListabyClienteAndNome(c.getIdUtente(), "lista");

        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArticoliLista articoliLista = new ArticoliLista();
        articoliLista.setIdLista(listaArticoli.getIdLista());
        articoliLista.setIdArticolo(1); //Dovrei creare un articolo mock ma per rapidità ne prendiamo uno già presente nel database
        articoliLista.setQuantita(2);
        lista_has_articoloDAO.add(articoliLista);

    }

    @After
    public void tearDown() throws Exception {


        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");

        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArticoliLista articoliLista = lista_has_articoloDAO.getOneArticoliLista(listaArticoli.getIdLista(),1);
        lista_has_articoloDAO.removeById(articoliLista);

        listaDAO.removeById(listaArticoli.getIdLista());
        IClienteDAO clienteDAO = ClienteDAO.getInstance();
        clienteDAO.removeByIdUtente(u.getIdUtente());
        utenteDAO.removeById(u.getEmail());

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");
        iPuntoVenditaDAO.remove(puntoVendita);
    }

    @Test
    public void isEmpty() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");

        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArticoliLista articoliLista = lista_has_articoloDAO.getOneArticoliLista(listaArticoli.getIdLista(),1);

        Assert.assertFalse(lista_has_articoloDAO.isEmpty(articoliLista.getIdLista()));

    }

    @Test
    public void has_articolo() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");

        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArticoliLista articoliLista = lista_has_articoloDAO.getOneArticoliLista(listaArticoli.getIdLista(),1);

        Assert.assertTrue(lista_has_articoloDAO.has_articolo(articoliLista.getIdLista(), 1));

    }

    @Test
    public void getOneArticoliLista() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");

        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArticoliLista articoliLista = lista_has_articoloDAO.getOneArticoliLista(listaArticoli.getIdLista(),1);

        Assert.assertEquals(2, articoliLista.getQuantita());

    }

    @Test
    public void updateQuantity() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");

        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArticoliLista articoliLista = lista_has_articoloDAO.getOneArticoliLista(listaArticoli.getIdLista(),1);
        articoliLista.setQuantita(1);
        lista_has_articoloDAO.updateQuantity(articoliLista);
        articoliLista = lista_has_articoloDAO.getOneArticoliLista(listaArticoli.getIdLista(),1);

        Assert.assertEquals(1, articoliLista.getQuantita());

    }

    @Test
    public void getArticoliLista() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");
        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        List<ArticoliLista> articoliListaList = lista_has_articoloDAO.getArticoliLista(listaArticoli.getIdLista());
        Assert.assertNotNull(articoliListaList);
        Assert.assertEquals(2,articoliListaList.get(articoliListaList.size()-1).getQuantita());

    }

    @Test
    public void removefromAllListe() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "Lista");

        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArticoliLista articoliLista = new ArticoliLista();
        articoliLista.setIdLista(listaArticoli.getIdLista());
        articoliLista.setIdArticolo(2); //Dovrei creare un articolo mock ma per rapidità ne prendiamo uno già presente nel database
        articoliLista.setQuantita(3);
        lista_has_articoloDAO.add(articoliLista);

        lista_has_articoloDAO.removefromAllListe(2);
        List<ArticoliLista> articoliListaList = lista_has_articoloDAO.getArticoliLista(listaArticoli.getIdLista());
        Assert.assertNotNull(articoliListaList);
        Assert.assertEquals(1,articoliListaList.size());

    }

    @Test
    public void removeAll() {

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        IListaDAO listaDAO = ListaDAO.getInstance();
        ListaArticoli listaArticoli = new ListaArticoli();
        listaArticoli.setNome("list");
        listaArticoli.setStato(ListaArticoli.StatoLista.NON_PAGATA);
        listaArticoli.setIdPuntoVendita(puntoVendita.getIdPuntoVendita());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        listaArticoli.setData(formatter.format(date)+"");
        listaArticoli.setIdCliente(u.getIdUtente());
        listaDAO.add(listaArticoli);
        listaArticoli = listaDAO.getListabyClienteAndNome(u.getIdUtente(), "list");

        ILista_has_ArticoloDAO lista_has_articoloDAO = Lista_has_ArticoloDAO.getInstance();
        ArticoliLista articoliLista = new ArticoliLista();
        articoliLista.setIdLista(listaArticoli.getIdLista());
        articoliLista.setIdArticolo(2); //Dovrei creare un articolo mock ma per rapidità ne prendiamo uno già presente nel database
        articoliLista.setQuantita(3);
        lista_has_articoloDAO.add(articoliLista);
        lista_has_articoloDAO.removeAll(listaArticoli.getIdLista());
        listaDAO.removeById(listaArticoli.getIdLista());

        List<ArticoliLista> lista = lista_has_articoloDAO.getArticoliLista(listaArticoli.getIdLista());
        Assert.assertEquals(0, lista.size());

    }
}
