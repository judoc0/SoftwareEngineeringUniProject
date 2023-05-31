package Test;

import DAO.*;
import DBInterface.DbUser;
import Model.Fornitore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class FornitoreDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

        IFornitoreDAO iFornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore();
        fornitore.setNome("Caio");
        fornitore.setCitta("Roma");
        fornitore.setNazione("Italia");
        fornitore.setSito("www.caio.it");
        iFornitoreDAO.add(fornitore);

    }

    @After
    public void tearDown() throws Exception {

        IFornitoreDAO iFornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = iFornitoreDAO.getByName("Caio");
        iFornitoreDAO.remove(fornitore.getIdFornitore());

    }

    @Test
    public void findById() {
        IFornitoreDAO iFornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = iFornitoreDAO.getByName("Caio");
        fornitore = iFornitoreDAO.getById(fornitore.getIdFornitore());
        Assert.assertEquals("www.caio.it", fornitore.getSito());
    }


    @Test
    public void findAllTest() {
        IFornitoreDAO iFornitoreDAO = FornitoreDAO.getInstance();
        List<Fornitore> fornitoreList = iFornitoreDAO.findAll();
        Assert.assertNotNull(fornitoreList);
        Assert.assertEquals("Caio", fornitoreList.get(fornitoreList.size()-1).getNome());
    }

    @Test
    public void findByUsername() {
        IFornitoreDAO iFornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = iFornitoreDAO.getByName("Caio");
        Assert.assertEquals("www.caio.it", fornitore.getSito());
    }

    @Test
    public void update() {
        IFornitoreDAO iFornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = iFornitoreDAO.getByName("Caio");
        fornitore.setCitta("Latina");
        iFornitoreDAO.update(fornitore);
        fornitore = iFornitoreDAO.getByName("Caio");
        Assert.assertEquals("Latina", fornitore.getCitta());
    }

    @Test
    public void exists() {
        IFornitoreDAO iFornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = new Fornitore();
        fornitore.setNome("Caio");
        fornitore.setCitta("Roma");
        fornitore.setNazione("Italia");
        fornitore.setSito("www.caio.it");
        Assert.assertTrue(iFornitoreDAO.exists(fornitore));
    }
}
