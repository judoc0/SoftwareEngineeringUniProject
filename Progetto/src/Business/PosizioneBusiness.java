package Business;

import DAO.PosizioneDAO;
import DAO.PuntoVenditaDAO;
import Model.IPosizione;
import Model.Posizione;
import Model.PuntoVendita;

import java.util.List;

public class PosizioneBusiness {
    private static  PosizioneBusiness instance;     //SINGLETON PATTERN

    public static synchronized PosizioneBusiness getInstance() {
        if (instance == null) instance = new PosizioneBusiness();
        return instance;
    }

    private PosizioneBusiness() {}

    public List<Posizione> getPosizione() {

        PosizioneDAO pDAO = PosizioneDAO.getInstance();

        List<Posizione> list = pDAO.findAll();

        for (Posizione p : list) {
            p.setIdPosizione(p.getIdPosizione());
            p.setCorsia(p.getCorsia());
            p.setScaffale(p.getScaffale());
        }

        return list;
    }

    public Posizione getOnePosizione(int id) {

        PosizioneDAO pDao = PosizioneDAO.getInstance();

        return pDao.getById(id);
    }

    public void addPosizione(Posizione posizione) {

        PosizioneDAO pDao = PosizioneDAO.getInstance();

        pDao.add(posizione);
    }

    public boolean posizioneExists(Posizione posizione) {

        PosizioneDAO pDao = PosizioneDAO.getInstance();

        return pDao.posizioneExists(posizione);
    }

    public Posizione getByPosizione(Posizione posizione) {

        PosizioneDAO pDao = PosizioneDAO.getInstance();

        return pDao.getByPosizione(posizione);
    }

    public void removePosizione(int posizione) {

        PosizioneDAO pDao = PosizioneDAO.getInstance();

        pDao.remove(posizione);
    }

    public void update(Posizione po) {

        PosizioneDAO pDao = PosizioneDAO.getInstance();

        pDao.update(po);
    }
}
