package Test;

import DAO.*;
import DBInterface.DbUser;
import Model.Posizione;
import Model.Prodotto;
import Model.Servizio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ServizioDAOTest {
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

    }

    @After
    public void tearDown() throws Exception {

        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        Servizio servizio = iServizioDAO.getByName("Frigorifero");
        iServizioDAO.remove(servizio.getId());

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        iArticoloDAO.remove(servizio.getId());

    }

    @Test
    public void findById() {
        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        Servizio servizio = iServizioDAO.getByName("Frigorifero");
        servizio = iServizioDAO.getById(servizio.getId());
        Float f = 50f;
        Assert.assertEquals(f, servizio.getPrezzo());
    }


    @Test
    public void findAllTest() {
        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        List<Servizio> servizioList = iServizioDAO.findAll();
        Assert.assertNotNull(servizioList);
        Assert.assertEquals("Frigorifero", servizioList.get(servizioList.size()-1).getNome());
    }

    @Test
    public void findByUsername() {
        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        Servizio servizio = iServizioDAO.getByName("Frigorifero");
        Float f = 50f;
        Assert.assertEquals(f, servizio.getPrezzo());
    }

    @Test
    public void update() {
        IServizioDAO iServizioDAO = ServizioDAO.getInstance();
        Servizio servizio = iServizioDAO.getByName("Frigorifero");
        servizio.setIdFornitore(2);
        iServizioDAO.update(servizio);
        servizio = iServizioDAO.getByName("Frigorifero");
        Assert.assertEquals(2, servizio.getIdFornitore());
    }
}
