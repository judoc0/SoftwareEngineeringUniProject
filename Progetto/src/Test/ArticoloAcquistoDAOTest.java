package Test;

import Business.ArticoloAcquistatoBusiness;
import Business.HashClass;
import DAO.*;
import DBInterface.DbUser;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ArticoloAcquistoDAOTest {
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

    }

    @After
    public void tearDown() throws Exception {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

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
    public void getByArticoloAndCliente() {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IArticoloAcquistatoDAO iArticoloAcquistatoDAO = ArticoloAcquistatoDAO.getInstance();
        ArticoloAcquistato articoloAcquistato = iArticoloAcquistatoDAO.getByArticoloAndCliente(articolo.getId(), u.getIdUtente());

        IAcquistoDAO iAcquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = iAcquistoDAO.getLastAcquisto();

        Assert.assertEquals(acquisto.getIdAcquisto(), articoloAcquistato.getIdAcquisto());

    }

    @Test
    public void removeArticolo() {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = new Articolo();
        articolo.setPrezzo(50F);
        articolo.setNome("Cucina");
        articolo.setIdCategoria(4);
        articolo.setDescrizione("Frigorifero molto ampio");
        articolo.setPercorsoFoto("");
        iArticoloDAO.add(articolo);
        articolo = iArticoloDAO.getByName("Cucina");

        IAcquistoDAO iAcquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = iAcquistoDAO.getLastAcquisto();

        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByEmail("valentino.rossi@gmail.com");

        IArticoloAcquistatoDAO iArticoloAcquistatoDAO = ArticoloAcquistatoDAO.getInstance();
        ArticoloAcquistato articoloAcquistato = new ArticoloAcquistato();
        articoloAcquistato.setIdArticolo(articolo.getId());
        articoloAcquistato.setIdAcquisto(acquisto.getIdAcquisto());
        articoloAcquistato.setIdCliente(u.getIdUtente());
        iArticoloAcquistatoDAO.add(articoloAcquistato);

        iArticoloAcquistatoDAO.removeArticolo(articolo.getId());
        iArticoloDAO.remove(articolo.getId());

        List<ArticoloAcquistato> articoloAcquistatoList = iArticoloAcquistatoDAO.getByIdAcquisto(acquisto.getIdAcquisto());
        Assert.assertNotNull(articoloAcquistatoList);
        Assert.assertNotEquals(articolo.getId(),articoloAcquistatoList.get(articoloAcquistatoList.size()-1).getIdArticolo());
    }

    @Test
    public void getByIdAcquisto() {

        IAcquistoDAO iAcquistoDAO = AcquistoDAO.getInstance();
        Acquisto acquisto = iAcquistoDAO.getLastAcquisto();

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");

        IArticoloAcquistatoDAO iArticoloAcquistatoDAO = ArticoloAcquistatoDAO.getInstance();
        List<ArticoloAcquistato> articoloAcquistatoList = iArticoloAcquistatoDAO.getByIdAcquisto(acquisto.getIdAcquisto());
        Assert.assertNotNull(articoloAcquistatoList);
        Assert.assertEquals(articolo.getId(),articoloAcquistatoList.get(articoloAcquistatoList.size()-1).getIdArticolo());

    }

    @Test
    public void findAll() {
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");

        IArticoloAcquistatoDAO iArticoloAcquistatoDAO = ArticoloAcquistatoDAO.getInstance();
        List<ArticoloAcquistato> articoloAcquistatoList = iArticoloAcquistatoDAO.findAll();
        Assert.assertNotNull(articoloAcquistatoList);
        Assert.assertEquals(articolo.getId(),articoloAcquistatoList.get(articoloAcquistatoList.size()-1).getIdArticolo());

    }



}
