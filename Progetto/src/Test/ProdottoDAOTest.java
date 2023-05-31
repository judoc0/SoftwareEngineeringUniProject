package Test;

import DAO.*;
import DBInterface.DbUser;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ProdottoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Prodotto prodotto = new Prodotto();
        prodotto.setPrezzo(50F);
        prodotto.setNome("Frigorifero");
        prodotto.setIdCategoria(4);
        prodotto.setDescrizione("Frigorifero molto ampio");
        prodotto.setPercorsoFoto("");
        prodotto.setIdProduttore(1);
        iArticoloDAO.add(prodotto);

        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        Posizione posizione = new Posizione();
        posizione.setCorsia("1");
        posizione.setScaffale("1");
        iPosizioneDAO.add(posizione);
        posizione = iPosizioneDAO.getByPosizione(posizione);
        prodotto.setIdPosizione(posizione.getIdPosizione());

        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        prodotto.setId(iArticoloDAO.getByName("Frigorifero").getId());
        iProdottoDAO.addProdotto(prodotto);

    }

    @After
    public void tearDown() throws Exception {

        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = iProdottoDAO.getByName("Frigorifero");
        iProdottoDAO.removeProdotto(prodotto.getId());

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        iArticoloDAO.remove(prodotto.getId());

        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        iPosizioneDAO.remove(prodotto.getIdPosizione());
    }

    @Test
    public void findById() {
        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = iProdottoDAO.getByName("Frigorifero");
        prodotto = iProdottoDAO.getById(prodotto.getId());
        Float f = 50f;
        Assert.assertEquals(f, prodotto.getPrezzo());
    }


    @Test
    public void findAllTest() {
        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        List<Prodotto> prodottoList = iProdottoDAO.findAll();
        Assert.assertNotNull(prodottoList);
        Assert.assertEquals("Frigorifero", prodottoList.get(prodottoList.size()-1).getNome());
    }

    @Test
    public void findByUsername() {
        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = iProdottoDAO.getByName("Frigorifero");
        Float f = 50f;
        Assert.assertEquals(f, prodotto.getPrezzo());
    }

    @Test
    public void update() {
        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = iProdottoDAO.getByName("Frigorifero");
        prodotto.setIdProduttore(2);
        iProdottoDAO.updateProdotto(prodotto);
        prodotto = iProdottoDAO.getByName("Frigorifero");
        Assert.assertEquals(2, prodotto.getIdProduttore());
    }
}
