package Business;

import DAO.FornitoreDAO;
import DAO.ProduttoreDAO;
import Model.Fornitore;
import Model.IFornitore;
import Model.Produttore;

import java.util.List;

public class FornitoreBusiness {

    private static FornitoreBusiness instance;     //SINGLETON PATTERN

    public static synchronized FornitoreBusiness getInstance() {
        if (instance == null) instance = new FornitoreBusiness();
        return instance;
    }

    private FornitoreBusiness() {}

    public List<Fornitore> getFornitori() {

        FornitoreDAO pDAO = FornitoreDAO.getInstance();

        List<Fornitore> list = pDAO.findAll();

        for (Fornitore f : list) {
            f.setIdFornitore(f.getIdFornitore());
            f.setNome(f.getNome());
            f.setSito(f.getSito());
            f.setCitta(f.getCitta());
            f.setNazione(f.getNazione());
        }

        return list;
    }

    public  Fornitore getOneFornitore(int id) {

        FornitoreDAO uDao = FornitoreDAO.getInstance();

        return uDao.getById(id);
    }


    public Fornitore getByName(String text) {

        FornitoreDAO fDAO = FornitoreDAO.getInstance();

        return fDAO.getByName(text);
    }

    public void addFornitore(Fornitore p) {

        FornitoreDAO uDao = FornitoreDAO.getInstance();

        uDao.add(p);
    }

    public boolean existsFornitore(Fornitore p) {

        FornitoreDAO uDao = FornitoreDAO.getInstance();

        return uDao.exists(p);
    }
}
