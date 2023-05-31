package Business;

import DAO.PuntoVendita_has_articoloDAO;
import Model.Articolo;
import Model.PuntoVendita;
import Model.PuntoVendita_has_articolo;
import java.util.ArrayList;
import java.util.List;

public class  PuntoVendita_has_articoloBusiness {

    private static PuntoVendita_has_articoloBusiness instance;     //SINGLETON PATTERN

    public static synchronized PuntoVendita_has_articoloBusiness getInstance() {
        if (instance == null) instance = new PuntoVendita_has_articoloBusiness();
        return instance;
    }

    private PuntoVendita_has_articoloBusiness() {}

    public List<Articolo> getByPuntoVendita(int id) {


        PuntoVendita_has_articoloDAO pDao = PuntoVendita_has_articoloDAO.getInstance();
        List<PuntoVendita_has_articolo> list = pDao.getById(id);
        List<Articolo> list1 = new ArrayList<>();

        for (PuntoVendita_has_articolo p : list)
            list1.add(ArticoloBusiness.getInstance().getOneProducts(p.getIdArticolo()));

        return list1;
    }


    public void add(PuntoVendita_has_articolo p) {
        PuntoVendita_has_articoloDAO pDao = PuntoVendita_has_articoloDAO.getInstance();

        pDao.add(p);
    }

    public void remove(int idPuntoVendita, int idArticolo) {
        PuntoVendita_has_articoloDAO pDao = PuntoVendita_has_articoloDAO.getInstance();

        pDao.remove(idPuntoVendita, idArticolo);
    }

    public void removeAll(int idArticolo) {
        PuntoVendita_has_articoloDAO pDao = PuntoVendita_has_articoloDAO.getInstance();

        pDao.removeAll(idArticolo);
    }

    public List<PuntoVendita_has_articolo> getWhereVenduto(int idArticolo) {

        PuntoVendita_has_articoloDAO pDAO = PuntoVendita_has_articoloDAO.getInstance();

        List<PuntoVendita_has_articolo> list = pDAO.findbyArticolo(idArticolo);

        for (PuntoVendita_has_articolo p : list) {
            p.setPuntoVendita(PuntoVenditaBusiness.getInstance().getOnePuntoVendita(p.getIdPuntoVendita()));
            p.setArticolo(ArticoloBusiness.getInstance().getOneProducts(p.getIdArticolo()));
        }

        return list;
    }
}
