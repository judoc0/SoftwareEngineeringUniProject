package Business;

import DAO.ProduttoreDAO;
import Model.IProduttore;
import Model.Produttore;

import java.util.List;

public class ProduttoreBusiness {

    private static  ProduttoreBusiness instance;     //SINGLETON PATTERN

    public static synchronized ProduttoreBusiness getInstance() {
        if (instance == null) instance = new ProduttoreBusiness();
        return instance;
    }

    private ProduttoreBusiness() {}

    public List<Produttore> getProducts() {

        ProduttoreDAO pDAO = ProduttoreDAO.getInstance();

        List<Produttore> list = pDAO.findAll();

        for (Produttore p : list) {
            p.setIdProduttore(p.getIdProduttore());
            p.setNome(p.getNome());
            p.setSito(p.getSito());
            p.setCitta(p.getCitta());
            p.setNazione(p.getNazione());
        }

        return list;
    }

    public  Produttore getOneProduttore(int id) {


        ProduttoreDAO uDao = ProduttoreDAO.getInstance();

        return uDao.getById(id);
    }

    public  Produttore getProduttoreByName(String nome) {

        ProduttoreDAO uDao = ProduttoreDAO.getInstance();

        return uDao.getByName(nome);
    }


    public void addProduttore(Produttore p) {

        ProduttoreDAO uDao = ProduttoreDAO.getInstance();

        uDao.add(p);

    }

    public boolean existsProduttore(Produttore p) {

        ProduttoreDAO uDao = ProduttoreDAO.getInstance();

        return uDao.exists(p);
    }
}
