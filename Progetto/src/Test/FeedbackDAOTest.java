package Test;

import Business.HashClass;
import DAO.*;
import DBInterface.DbUser;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


public class FeedbackDAOTest {
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
        acquisto = iAcquistoDAO.getLastAcquisto();

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = new Articolo();
        articolo.setPrezzo(50F);
        articolo.setNome("Frigorifero");
        articolo.setIdCategoria(4);
        articolo.setDescrizione("Frigorifero molto ampio");
        articolo.setPercorsoFoto("");
        iArticoloDAO.add(articolo);
        articolo = iArticoloDAO.getByName("Frigorifero");

        IArticoloAcquistatoDAO iArticoloAcquistatoDAO = ArticoloAcquistatoDAO.getInstance();
        ArticoloAcquistato articoloAcquistato = new ArticoloAcquistato();
        articoloAcquistato.setIdArticolo(articolo.getId());
        articoloAcquistato.setIdAcquisto(acquisto.getIdAcquisto());
        articoloAcquistato.setIdCliente(c.getIdUtente());
        iArticoloAcquistatoDAO.add(articoloAcquistato);

        IFeedbackDAO iFeedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedBack = new FeedBack();
        feedBack.setCommento("Molto utile");
        java.sql.Date date = new Date(System.currentTimeMillis());
        feedBack.setDate(date);
        feedBack.setPunteggio(4);
        feedBack.setIdCliente(articoloAcquistato.getIdCliente());
        feedBack.setIdArticolo(articoloAcquistato.getIdArticolo());
        feedBack.setIdAcquisto(articoloAcquistato.getIdAcquisto());
        iFeedbackDAO.add(feedBack);


    }

    @After
    public void tearDown() throws Exception {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IFeedbackDAO iFeedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedBack = iFeedbackDAO.getByArticoloCliente(articolo.getId(),u.getIdUtente());
        iFeedbackDAO.removeById(feedBack.getId());

        IArticoloAcquistatoDAO iArticoloAcquistatoDAO = ArticoloAcquistatoDAO.getInstance();
        iArticoloAcquistatoDAO.removeByIdUtente(u.getIdUtente());

        iArticoloDAO.remove(articolo.getId());

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
    public void CommentoExists() {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IFeedbackDAO iFeedbackDAO = FeedbackDAO.getInstance();
        Assert.assertTrue(iFeedbackDAO.CommentoExists(articolo.getId(), u.getIdUtente()));

    }

    @Test
    public void getByArticoloCliente() {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IFeedbackDAO iFeedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedBack = iFeedbackDAO.getByArticoloCliente(articolo.getId(),u.getIdUtente());
        Assert.assertEquals("Molto utile",feedBack.getCommento());

    }

    @Test
    public void getById() {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IFeedbackDAO iFeedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedBack = iFeedbackDAO.getByArticoloCliente(articolo.getId(),u.getIdUtente());
        feedBack = iFeedbackDAO.getById(feedBack.getId());
        Assert.assertEquals("Molto utile",feedBack.getCommento());

    }

    @Test
    public void removeArticolo() {


    }

    @Test
    public void findAllArticolo() {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");

        IFeedbackDAO iFeedbackDAO = FeedbackDAO.getInstance();
        List<FeedBack> feedBackList = iFeedbackDAO.findAllArticolo(articolo.getId());

        Assert.assertNotNull(feedBackList);
        Assert.assertEquals("Molto utile", feedBackList.get(feedBackList.size()-1).getCommento());


    }

    @Test
    public void findAll() {

        IFeedbackDAO iFeedbackDAO = FeedbackDAO.getInstance();
        List<FeedBack> feedBackList = iFeedbackDAO.findAll();
        Assert.assertNotNull(feedBackList);
        Assert.assertEquals("Molto utile", feedBackList.get(feedBackList.size()-1).getCommento());

    }

    @Test
    public void update() {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IFeedbackDAO iFeedbackDAO = FeedbackDAO.getInstance();
        FeedBack feedBack = iFeedbackDAO.getByArticoloCliente(articolo.getId(),u.getIdUtente());

        feedBack.setCommento("Non utile");
        iFeedbackDAO.update(feedBack);
        feedBack = iFeedbackDAO.getByArticoloCliente(articolo.getId(),u.getIdUtente());

        Assert.assertEquals("Non utile",feedBack.getCommento());

    }

}
