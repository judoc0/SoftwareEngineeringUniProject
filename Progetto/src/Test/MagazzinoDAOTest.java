package Test;

import DAO.IMagazzinoDAO;
import DAO.IPuntoVenditaDAO;
import DAO.MagazzinoDAO;
import DAO.PuntoVenditaDAO;
import DBInterface.DbUser;
import Model.Magazzino;
import Model.PuntoVendita;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class MagazzinoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = new PuntoVendita();
        puntoVendita.setCitta("Acaya");
        iPuntoVenditaDAO.add(puntoVendita);

        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = new Magazzino();
        magazzino.setIdPuntoVendita(iPuntoVenditaDAO.getByCity("Acaya").getIdPuntoVendita());
        iMagazzinoDAO.add(magazzino);

    }

    @After
    public void tearDown() throws Exception {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        iMagazzinoDAO.remove(iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita()).getIdPuntoVendita());

        iPuntoVenditaDAO.remove(puntoVendita);

    }

    @Test
    public void findByIdPuntoVendita() {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita());

        Assert.assertEquals(puntoVendita.getIdPuntoVendita(), magazzino.getIdPuntoVendita());
    }


    @Test
    public void findAllTest() {
        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        List<Magazzino> magazzinoList = iMagazzinoDAO.findAll();
        Assert.assertNotNull(magazzinoList);
        Assert.assertEquals(puntoVendita.getIdPuntoVendita(), magazzinoList.get(magazzinoList.size()-1).getIdPuntoVendita());
    }

    @Test
    public void getById() {
        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");

        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita());

        Assert.assertEquals(magazzino.getIdPuntoVendita(), iMagazzinoDAO.getById(magazzino.getIdMagazzino()).getIdPuntoVendita());
    }

}
