package Test;

import Business.CategoriaArticoloBusiness;
import DAO.ArticoloDAO;
import DAO.IArticoloDAO;
import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import DBInterface.DbUser;
import Model.Articolo;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class ArticoloDAOTest {

    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() {
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = new Articolo();
        articolo.setPrezzo(50F);
        articolo.setNome("Frigorifero");
        articolo.setIdCategoria(4);
        articolo.setDescrizione("Frigorifero molto ampio");
        articolo.setPercorsoFoto("");
        iArticoloDAO.add(articolo);
    }

    @After
    public void tearDown() {
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");
        iArticoloDAO.remove(articolo.getId());
    }

    @Test
    public void getById() {
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");
        Float f = 50F;
        Assert.assertEquals(f, articolo.getPrezzo());
    }

    @Test
    public void findAllTest() {
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        List<Articolo> articoloList = iArticoloDAO.findAll();
        Assert.assertNotNull(articoloList);
        Assert.assertEquals("Frigorifero", articoloList.get(articoloList.size()-1).getNome());
    }

    @Test
    public void prodottoExists() {
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");
        Assert.assertFalse(iArticoloDAO.prodottoExists(articolo));
    }

    @Test
    public void servizioExists() {
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");
        Assert.assertFalse(iArticoloDAO.servizioExists(articolo));
    }

    @Test
    public void articoloExists() {
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");
        Assert.assertTrue(iArticoloDAO.articoloExists(articolo));
    }

    @Test
    public void update() {
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");
        articolo.setPrezzo(45F);
        articolo.setNome("Frigorifero");
        articolo.setIdCategoria(4);
        articolo.setDescrizione("Frigorifero molto ampio");
        articolo.setPercorsoFoto("");
        iArticoloDAO.update(articolo);
        Float f = 45F;
        Assert.assertEquals(f, articolo.getPrezzo());
    }
    @Test
    public void getByName(){
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");
        Assert.assertEquals("Frigorifero molto ampio", articolo.getDescrizione());
    }
    @Test
    public void inserisciFoto(){
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Articolo articolo = iArticoloDAO.getByName("Frigorifero");
        iArticoloDAO.inserisciFoto(articolo, new File(""));
        articolo = iArticoloDAO.getByName("Frigorifero");
        Assert.assertNull(articolo.getFoto());
    }
}
