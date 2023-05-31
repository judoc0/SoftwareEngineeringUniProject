package Test;

import DAO.*;
import DBInterface.DbUser;
import Model.Posizione;
import Model.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class PosizioneDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        Posizione posizione = new Posizione();
        posizione.setCorsia("1");
        posizione.setScaffale("1");
        iPosizioneDAO.add(posizione);

    }

    @After
    public void tearDown() throws Exception {

        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        Posizione pos = new Posizione();
        pos.setCorsia("1");
        pos.setScaffale("1");
        Posizione posizione = iPosizioneDAO.getByPosizione(pos);
        iPosizioneDAO.remove(posizione.getIdPosizione());
    }

    @Test
    public void findById() {

        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        Posizione pos = new Posizione();
        pos.setCorsia("1");
        pos.setScaffale("1");
        Posizione posizione = iPosizioneDAO.getByPosizione(pos);
        posizione = iPosizioneDAO.getById(posizione.getIdPosizione());
        Assert.assertEquals("1",posizione.getCorsia());
    }


    @Test
    public void findAllTest() {
        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        List<Posizione> posizioneList = iPosizioneDAO.findAll();
        Assert.assertNotNull(posizioneList);
        Assert.assertEquals("1", posizioneList.get(posizioneList.size()-1).getCorsia());
    }

    @Test
    public void getByPosizione() {
        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        Posizione pos = new Posizione();
        pos.setCorsia("1");
        pos.setScaffale("1");
        Posizione posizione = iPosizioneDAO.getByPosizione(pos);
        Assert.assertEquals("1",posizione.getCorsia());
    }

    @Test
    public void posizioneExists() {

        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        Posizione pos = new Posizione();
        pos.setCorsia("1");
        pos.setScaffale("1");
        Assert.assertTrue(iPosizioneDAO.posizioneExists(pos));
    }

    @Test
    public void update() {

        IPosizioneDAO iPosizioneDAO = PosizioneDAO.getInstance();
        Posizione pos = new Posizione();
        pos.setCorsia("1");
        pos.setScaffale("1");
        Posizione posizione = iPosizioneDAO.getByPosizione(pos);
        posizione.setCorsia("2");
        posizione.setScaffale("2");
        iPosizioneDAO.update(posizione);
        posizione = iPosizioneDAO.getById(posizione.getIdPosizione());
        Assert.assertEquals("2",posizione.getCorsia());
        posizione.setCorsia("1");
        posizione.setScaffale("1");
        iPosizioneDAO.update(posizione);


    }
}
