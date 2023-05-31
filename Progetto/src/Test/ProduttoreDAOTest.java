package Test;

import DAO.*;
import DBInterface.DbUser;
import Model.Produttore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ProduttoreDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

        IProduttoreDAO iProduttoreDAO = ProduttoreDAO.getInstance();
        Produttore produttore = new Produttore();
        produttore.setNome("Caio");
        produttore.setCitta("Roma");
        produttore.setNazione("Italia");
        produttore.setSito("www.caio.it");
        iProduttoreDAO.add(produttore);

    }

    @After
    public void tearDown() throws Exception {

        IProduttoreDAO iProduttoreDAO = ProduttoreDAO.getInstance();
        Produttore produttore = iProduttoreDAO.getByName("Caio");
        iProduttoreDAO.remove(produttore.getIdProduttore());

    }

    @Test
    public void findById() {
        IProduttoreDAO iProduttoreDAO = ProduttoreDAO.getInstance();
        Produttore produttore = iProduttoreDAO.getByName("Caio");
        produttore = iProduttoreDAO.getById(produttore.getIdProduttore());
        Assert.assertEquals("www.caio.it", produttore.getSito());
    }


    @Test
    public void findAllTest() {
        IProduttoreDAO iProduttoreDAO = ProduttoreDAO.getInstance();
        List<Produttore> produttoreList = iProduttoreDAO.findAll();
        Assert.assertNotNull(produttoreList);
        Assert.assertEquals("Caio", produttoreList.get(produttoreList.size()-1).getNome());
    }

    @Test
    public void findByUsername() {
        IProduttoreDAO iProduttoreDAO = ProduttoreDAO.getInstance();
        Produttore produttore = iProduttoreDAO.getByName("Caio");
        Assert.assertEquals("www.caio.it", produttore.getSito());
    }

    @Test
    public void update() {
        IProduttoreDAO iProduttoreDAO = ProduttoreDAO.getInstance();
        Produttore produttore = iProduttoreDAO.getByName("Caio");
        produttore.setCitta("Latina");
        iProduttoreDAO.update(produttore);
        produttore = iProduttoreDAO.getByName("Caio");
        Assert.assertEquals("Latina", produttore.getCitta());
    }

    @Test
    public void exists() {
        IProduttoreDAO iProduttoreDAO = ProduttoreDAO.getInstance();
        Produttore produttore = new Produttore();
        produttore.setNome("Caio");
        produttore.setCitta("Roma");
        produttore.setNazione("Italia");
        produttore.setSito("www.caio.it");
        Assert.assertTrue(iProduttoreDAO.exists(produttore));
    }
}
