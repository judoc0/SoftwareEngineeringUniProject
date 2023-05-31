package Business;

import DAO.MagazzinoDAO;
import Model.Magazzino;

import java.util.List;

public class MagazzinoBusiness {

    private static MagazzinoBusiness instance;     //SINGLETON PATTERN

    public static synchronized MagazzinoBusiness getInstance() {
        if (instance == null) instance = new MagazzinoBusiness();
        return instance;
    }

    private MagazzinoBusiness() {}

    public List<Magazzino> getAllMagazzino() {

        MagazzinoDAO mDAO = MagazzinoDAO.getInstance();

        List<Magazzino> list = mDAO.findAll();

        for (Magazzino m : list) {
            m.setIdMagazzino(m.getIdMagazzino());
            m.setIdPuntoVendita(m.getIdPuntoVendita());
        }

        return list;
    }

    public Magazzino getOneMagazzino(int id) {

        MagazzinoDAO mDAO = MagazzinoDAO.getInstance();

        return mDAO.getById(id);
    }


    public void addMagazzino(Magazzino magazzino) {

        MagazzinoDAO mDAO = MagazzinoDAO.getInstance();

        mDAO.add(magazzino);
    }

    public Magazzino getByIdPuntoVendita(int idPuntoVendita) {

        MagazzinoDAO mDAO = MagazzinoDAO.getInstance();

        return mDAO.getByIdPuntoVendita(idPuntoVendita);
    }
}
