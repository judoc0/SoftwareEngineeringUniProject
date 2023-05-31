package Test;

import Business.HashClass;
import DAO.CategoriaArticoloDAO;
import DAO.ICategoriaArticoloDAO;
import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import DBInterface.DbUser;
import Model.CategoriaArticolo;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class CategoriaArticoloDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {
        ICategoriaArticoloDAO iCategoriaArticoloDAO = CategoriaArticoloDAO.getInstance();
        CategoriaArticolo categoriaArticolo = new CategoriaArticolo();
        categoriaArticolo.setNome("Bagno");
        categoriaArticolo.setIdCategoria_padre(2);
        categoriaArticolo.setTipoCategoria(CategoriaArticolo.tipoCategoria.PRODOTTO);
        iCategoriaArticoloDAO.add(categoriaArticolo);
    }

    @After
    public void tearDown() throws Exception {
        ICategoriaArticoloDAO iCategoriaArticoloDAO = CategoriaArticoloDAO.getInstance();
        CategoriaArticolo categoriaArticolo = iCategoriaArticoloDAO.findByName("Bagno");
        iCategoriaArticoloDAO.remove(categoriaArticolo.getIdCategoria());
    }

    @Test
    public void findById() {
        ICategoriaArticoloDAO iCategoriaArticoloDAO = CategoriaArticoloDAO.getInstance();
        CategoriaArticolo categoriaArticolo = iCategoriaArticoloDAO.findByName("Bagno");
        categoriaArticolo = iCategoriaArticoloDAO.findById(categoriaArticolo.getIdCategoria());
        Assert.assertEquals(2,categoriaArticolo.getIdCategoria_padre());
    }


    @Test
    public void findAllTest() {
        ICategoriaArticoloDAO iCategoriaArticoloDAO = CategoriaArticoloDAO.getInstance();
        List<CategoriaArticolo> categoriaArticoloList = iCategoriaArticoloDAO.findCategories();
        Assert.assertNotNull(categoriaArticoloList);
        Assert.assertEquals("Bagno", categoriaArticoloList.get(categoriaArticoloList.size()-1).getNome());
    }

    @Test
    public void findByUsername() {
        ICategoriaArticoloDAO iCategoriaArticoloDAO = CategoriaArticoloDAO.getInstance();
        CategoriaArticolo categoriaArticolo = iCategoriaArticoloDAO.findByName("Bagno");
        Assert.assertEquals(2,categoriaArticolo.getIdCategoria_padre());
    }
}
