package Test;

import DAO.*;
import DBInterface.DbUser;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class DisponibilitaDAOTest {
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
        prodotto = iProdottoDAO.getByName("Frigorifero");


        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = new PuntoVendita();
        puntoVendita.setCitta("Acaya");
        iPuntoVenditaDAO.add(puntoVendita);
        puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");


        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = new Magazzino();
        magazzino.setIdPuntoVendita(puntoVendita.getIdPuntoVendita());
        iMagazzinoDAO.add(magazzino);
        magazzino = iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita());


        IDisponibilitaDAO iDisponibilitaDAO = DisponibilitaDAO.getInstance();
        Disponibilita disponibilita = new Disponibilita();
        disponibilita.setIdMagazzino(magazzino.getIdMagazzino());
        disponibilita.setIdProdotto(prodotto.getId());
        disponibilita.setQuantita(20);
        iDisponibilitaDAO.addDisponibilita(disponibilita);

    }

    @After
    public void tearDown() throws Exception {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");


        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita());


        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = iProdottoDAO.getByName("Frigorifero");


        IDisponibilitaDAO iDisponibilitaDAO = DisponibilitaDAO.getInstance();
        Disponibilita disponibilita = iDisponibilitaDAO.getById(prodotto.getId(), magazzino.getIdMagazzino());
        iDisponibilitaDAO.remove(disponibilita.getIdProdotto());

        iProdottoDAO.removeProdotto(prodotto.getId());
        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        iArticoloDAO.remove(prodotto.getId());

        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        iPosizioneDAO.remove(prodotto.getIdPosizione());


        iMagazzinoDAO.remove(iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita()).getIdPuntoVendita());

        iPuntoVenditaDAO.remove(puntoVendita);

    }

    @Test
    public void findById() {


        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");


        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita());


        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = iProdottoDAO.getByName("Frigorifero");


        IDisponibilitaDAO iDisponibilitaDAO = DisponibilitaDAO.getInstance();
        Disponibilita disponibilita = iDisponibilitaDAO.getById(prodotto.getId(), magazzino.getIdMagazzino());

        Assert.assertEquals(20,disponibilita.getQuantita());

    }


    @Test
    public void findAllTest() {

        IDisponibilitaDAO iDisponibilitaDAO = DisponibilitaDAO.getInstance();
        List<Disponibilita> disponibilitas = iDisponibilitaDAO.findAll();
        Assert.assertNotNull(disponibilitas);
        Assert.assertEquals(20,disponibilitas.get(disponibilitas.size()-1).getQuantita());

    }

    @Test
    public void findByMagazzino() {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");


        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita());


        IDisponibilitaDAO iDisponibilitaDAO = DisponibilitaDAO.getInstance();
        List<Disponibilita> disponibilitas = iDisponibilitaDAO.find(magazzino.getIdMagazzino());

        Assert.assertNotNull(disponibilitas);
        Assert.assertEquals(20,disponibilitas.get(disponibilitas.size()-1).getQuantita());

    }

    @Test
    public void update() {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");


        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita());


        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = iProdottoDAO.getByName("Frigorifero");

        IDisponibilitaDAO iDisponibilitaDAO = DisponibilitaDAO.getInstance();
        iDisponibilitaDAO.addQuantity(prodotto.getId(),10, magazzino.getIdMagazzino());

        Disponibilita disponibilita = iDisponibilitaDAO.getById(prodotto.getId(), magazzino.getIdMagazzino());

        Assert.assertEquals(10,disponibilita.getQuantita());


    }

    @Test
    public void rifornimento() {

        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = iPuntoVenditaDAO.getByCity("Acaya");


        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita());


        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = iProdottoDAO.getByName("Frigorifero");

        IDisponibilitaDAO iDisponibilitaDAO = DisponibilitaDAO.getInstance();
        Disponibilita disponibilita = iDisponibilitaDAO.getById(prodotto.getId(), magazzino.getIdMagazzino());

        disponibilita.setQuantita(25);
        iDisponibilitaDAO.rifornimento(disponibilita);

        disponibilita = iDisponibilitaDAO.getById(prodotto.getId(), magazzino.getIdMagazzino());
        Assert.assertEquals(25,disponibilita.getQuantita());


    }

    @Test
    public void removebyMagazzino() {

        IArticoloDAO iArticoloDAO = ArticoloDAO.getInstance();
        Prodotto prodotto = new Prodotto();
        prodotto.setPrezzo(50F);
        prodotto.setNome("Cucina");
        prodotto.setIdCategoria(4);
        prodotto.setDescrizione("Frigorifero molto ampio");
        prodotto.setPercorsoFoto("");
        prodotto.setIdProduttore(1);
        iArticoloDAO.add(prodotto);


        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        Posizione posizione = new Posizione();
        posizione.setCorsia("2");
        posizione.setScaffale("2");
        iPosizioneDAO.add(posizione);
        posizione = iPosizioneDAO.getByPosizione(posizione);
        prodotto.setIdPosizione(posizione.getIdPosizione());


        IProdottoDAO iProdottoDAO = ProdottoDAO.getInstance();
        prodotto.setId(iArticoloDAO.getByName("Cucina").getId());
        iProdottoDAO.addProdotto(prodotto);
        prodotto = iProdottoDAO.getByName("Cucina");


        IPuntoVenditaDAO iPuntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = new PuntoVendita();
        puntoVendita.setCitta("Venezia");
        iPuntoVenditaDAO.add(puntoVendita);
        puntoVendita = iPuntoVenditaDAO.getByCity("Venezia");


        IMagazzinoDAO iMagazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = new Magazzino();
        magazzino.setIdPuntoVendita(puntoVendita.getIdPuntoVendita());
        iMagazzinoDAO.add(magazzino);
        magazzino = iMagazzinoDAO.getByIdPuntoVendita(puntoVendita.getIdPuntoVendita());


        IDisponibilitaDAO iDisponibilitaDAO = DisponibilitaDAO.getInstance();
        Disponibilita disponibilita = new Disponibilita();
        disponibilita.setIdMagazzino(magazzino.getIdMagazzino());
        disponibilita.setIdProdotto(prodotto.getId());
        disponibilita.setQuantita(20);
        iDisponibilitaDAO.addDisponibilita(disponibilita);


        iDisponibilitaDAO.removebyMagazzino(prodotto.getId(), magazzino.getIdMagazzino());

        iProdottoDAO.removeProdotto(prodotto.getId());
        iArticoloDAO.remove(prodotto.getId());

        iPosizioneDAO.remove(prodotto.getIdPosizione());

        iMagazzinoDAO.remove(magazzino.getIdPuntoVendita());
        iPuntoVenditaDAO.remove(puntoVendita);
    }
}
