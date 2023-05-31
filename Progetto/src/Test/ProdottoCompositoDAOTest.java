package Test;

import DAO.*;
import DBInterface.DbUser;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ProdottoCompositoDAOTest {
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

        IProdottoCompositoDAO iProdottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = new ProdottoComposito();
        prodottoComposito.setId(iProdottoDAO.getByName("Frigorifero").getId());
        Prodotto p1 = iProdottoDAO.getById(1);  //Dovrei creare un prodotto mock ma per rapidità ne prendiamo uno già presente nel database
        p1.setQuantita(2);
        iProdottoCompositoDAO.add(prodottoComposito, p1);
        Prodotto p2 = iProdottoDAO.getById(2); //Dovrei creare un prodotto mock ma per rapidità ne prendiamo uno già presente nel database
        p2.setQuantita(3);
        iProdottoCompositoDAO.add(prodottoComposito, p2);

    }

    @After
    public void tearDown() throws Exception {

        IProdottoCompositoDAO iProdottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        ProdottoComposito prodottoComposito = iProdottoCompositoDAO.getById(iProdottoDAO.getByName("Frigorifero").getId());
        iProdottoCompositoDAO.remove(prodottoComposito.getId());

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
        IProdottoCompositoDAO iProdottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = iProdottoCompositoDAO.getById(iProdottoDAO.getByName("Frigorifero").getId());
        Assert.assertEquals(2, prodottoComposito.getSottoprodotti().size());
    }


    @Test
    public void updateQuantity() {
        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        IProdottoCompositoDAO iProdottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = iProdottoCompositoDAO.getById(iProdottoDAO.getByName("Frigorifero").getId());
        Prodotto p2 = iProdottoDAO.getById(2);
        p2.setQuantita(4);
        iProdottoCompositoDAO.updateQuantity(prodottoComposito, p2);
        prodottoComposito = iProdottoCompositoDAO.getById(iProdottoDAO.getByName("Frigorifero").getId());
        Assert.assertEquals(4, prodottoComposito.getSottoprodotti().get(prodottoComposito.getSottoprodotti().size()-1).getQuantita());

    }


    @Test
    public void compositoExists() {
        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        IProdottoCompositoDAO iProdottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = iProdottoCompositoDAO.getById(iProdottoDAO.getByName("Frigorifero").getId());
        Assert.assertTrue(iProdottoCompositoDAO.compositoExists(prodottoComposito.getId()));
    }

    @Test
    public void deletefromComposito() {
        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        IProdottoCompositoDAO iProdottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = iProdottoCompositoDAO.getById(iProdottoDAO.getByName("Frigorifero").getId());
        Prodotto p2 = iProdottoDAO.getById(2);
        iProdottoCompositoDAO.deletefromComposito(prodottoComposito, p2);
        prodottoComposito = iProdottoCompositoDAO.getById(iProdottoDAO.getByName("Frigorifero").getId());
        Assert.assertEquals(1, prodottoComposito.getSottoprodotti().size());

    }

}
