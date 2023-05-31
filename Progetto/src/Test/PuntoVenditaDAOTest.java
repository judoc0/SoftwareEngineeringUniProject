package Test;

import DAO.IPuntoVenditaDAO;
import DAO.PuntoVenditaDAO;
import DBInterface.DbUser;
import Model.PuntoVendita;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class PuntoVenditaDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = new PuntoVendita();
        puntoVendita.setCitta("Acaya");
        iPuntoVenditaDAO.add(puntoVendita);

    }

    @After
    public void tearDown() throws Exception {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");
        iPuntoVenditaDAO.remove(puntoVendita);

    }

    @Test
    public void findById() {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getById(iPuntoVenditaDAO.getByCity("Acaya").getIdPuntoVendita());
        Assert.assertEquals("Acaya", puntoVendita.getCitta());
    }


    @Test
    public void findAllTest() {
        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        List<PuntoVendita> puntoVenditaList = iPuntoVenditaDAO.findAll();
        Assert.assertNotNull(puntoVenditaList);
        Assert.assertEquals("Acaya", puntoVenditaList.get(puntoVenditaList.size()-1).getCitta());
    }

    @Test
    public void findByCity() {
        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");
        Assert.assertEquals("Acaya", puntoVendita.getCitta());
    }

    @Test
    public void update() {
        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");
        puntoVendita.setCitta("Latina");
        iPuntoVenditaDAO.update(puntoVendita);
        puntoVendita = iPuntoVenditaDAO.getByCity("Latina");
        Assert.assertEquals("Latina", puntoVendita.getCitta());
    }
}
