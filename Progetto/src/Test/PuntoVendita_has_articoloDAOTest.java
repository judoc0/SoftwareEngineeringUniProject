package Test;

import DAO.*;
import DBInterface.DbUser;
import Model.PuntoVendita;
import Model.PuntoVendita_has_articolo;
import Model.Servizio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class PuntoVendita_has_articoloDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Servizio servizio = new Servizio();
        servizio.setPrezzo(50F);
        servizio.setNome("Frigorifero");
        servizio.setIdCategoria(4);
        servizio.setDescrizione("Frigorifero molto ampio");
        servizio.setPercorsoFoto("");
        servizio.setIdFornitore(1);
        iArticoloDAO.add(servizio);

        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        servizio.setId(iArticoloDAO.getByName("Frigorifero").getId());
        iServizioDAO.addServizio(servizio);
        servizio = iServizioDAO.getByName("Frigorifero");


        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = new PuntoVendita();
        puntoVendita.setCitta("Acaya");
        iPuntoVenditaDAO.add(puntoVendita);
        puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");


        IPuntoVendita_has_articoloDAO iPuntoVendita_has_articoloDAO = PuntoVendita_has_articoloDAO.getInstance();
        PuntoVendita_has_articolo puntoVendita_has_articolo = new PuntoVendita_has_articolo();
        puntoVendita_has_articolo.setIdArticolo(servizio.getId());
        puntoVendita_has_articolo.setIdPuntoVendita(puntoVendita.getIdPuntoVendita());
        iPuntoVendita_has_articoloDAO.add(puntoVendita_has_articolo);

    }

    @After
    public void tearDown() throws Exception {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");


        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        Servizio servizio = iServizioDAO.getByName("Frigorifero");


        IPuntoVendita_has_articoloDAO iPuntoVendita_has_articoloDAO = PuntoVendita_has_articoloDAO.getInstance();
        iPuntoVendita_has_articoloDAO.remove(puntoVendita.getIdPuntoVendita(), servizio.getId());


        iServizioDAO.remove(servizio.getId());
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        iArticoloDAO.remove(servizio.getId());


        iPuntoVenditaDAO.remove(puntoVendita);

    }

    @Test
    public void findById() {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getById(iPuntoVenditaDAO.getByCity("Acaya").getIdPuntoVendita());

        IPuntoVendita_has_articoloDAO iPuntoVendita_has_articoloDAO = PuntoVendita_has_articoloDAO.getInstance();
        List<PuntoVendita_has_articolo> puntoVendita_has_articoloList = iPuntoVendita_has_articoloDAO.getById(puntoVendita.getIdPuntoVendita());

        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        Servizio servizio = iServizioDAO.getByName("Frigorifero");

        Assert.assertNotNull(puntoVendita_has_articoloList);
        Assert.assertEquals(servizio.getId(), puntoVendita_has_articoloList.get(puntoVendita_has_articoloList.size()-1).getIdArticolo());
    }


    @Test
    public void findAll() {
        IPuntoVendita_has_articoloDAO iPuntoVendita_has_articoloDAO = PuntoVendita_has_articoloDAO.getInstance();
        List<PuntoVendita_has_articolo> puntoVendita_has_articoloList = iPuntoVendita_has_articoloDAO.findAll();

        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        Servizio servizio = iServizioDAO.getByName("Frigorifero");

        Assert.assertNotNull(puntoVendita_has_articoloList);
        Assert.assertEquals(servizio.getId(), puntoVendita_has_articoloList.get(puntoVendita_has_articoloList.size()-1).getIdArticolo());

    }

    @Test
    public void findbyArticolo() {

        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        Servizio servizio = iServizioDAO.getByName("Frigorifero");


        IPuntoVendita_has_articoloDAO iPuntoVendita_has_articoloDAO = PuntoVendita_has_articoloDAO.getInstance();
        List<PuntoVendita_has_articolo> puntoVendita_has_articoloList = iPuntoVendita_has_articoloDAO.findbyArticolo(servizio.getId());

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        Assert.assertNotNull(puntoVendita_has_articoloList);
        Assert.assertEquals(puntoVendita.getIdPuntoVendita(), puntoVendita_has_articoloList.get(puntoVendita_has_articoloList.size()-1).getIdPuntoVendita());
    }

    @Test
    public void removeAll() {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Servizio servizio = new Servizio();
        servizio.setPrezzo(50F);
        servizio.setNome("Cucina");
        servizio.setIdCategoria(4);
        servizio.setDescrizione("Frigorifero molto ampio");
        servizio.setPercorsoFoto("");
        servizio.setIdFornitore(1);
        iArticoloDAO.add(servizio);

        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        servizio.setId(iArticoloDAO.getByName("Cucina").getId());
        iServizioDAO.addServizio(servizio);
        servizio = iServizioDAO.getByName("Cucina");


        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = new PuntoVendita();
        puntoVendita.setCitta("Venezia");
        iPuntoVenditaDAO.add(puntoVendita);
        puntoVendita = iPuntoVenditaDAO.getByCity("Venezia");


        IPuntoVendita_has_articoloDAO iPuntoVendita_has_articoloDAO = PuntoVendita_has_articoloDAO.getInstance();
        PuntoVendita_has_articolo puntoVendita_has_articolo = new PuntoVendita_has_articolo();
        puntoVendita_has_articolo.setIdArticolo(servizio.getId());
        puntoVendita_has_articolo.setIdPuntoVendita(puntoVendita.getIdPuntoVendita());
        iPuntoVendita_has_articoloDAO.add(puntoVendita_has_articolo);


        iPuntoVendita_has_articoloDAO.removeAll(servizio.getId());


        iServizioDAO.remove(servizio.getId());
        iArticoloDAO.remove(servizio.getId());


        iPuntoVenditaDAO.remove(puntoVendita);

    }
}
