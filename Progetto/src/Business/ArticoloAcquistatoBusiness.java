package Business;

import DAO.ArticoloAcquistatoDAO;
import Model.ArticoloAcquistato;

import java.util.List;

public class ArticoloAcquistatoBusiness {

    private static ArticoloAcquistatoBusiness instance;     //SINGLETON PATTERN

    public static synchronized ArticoloAcquistatoBusiness getInstance() {
        if (instance == null) instance = new ArticoloAcquistatoBusiness();
        return instance;
    }

    private ArticoloAcquistatoBusiness() {}

    public List<ArticoloAcquistato> getAllArticoliAcquistati() {

        ArticoloAcquistatoDAO aDAO = ArticoloAcquistatoDAO.getInstance();

        List<ArticoloAcquistato> list = aDAO.findAll();

        for (ArticoloAcquistato a : list) {
            a.setIdAcquisto(a.getIdAcquisto());
            a.setIdArticolo(a.getIdArticolo());
            a.setIdCliente(a.getIdCliente());
        }

        return list;
    }

    public List<ArticoloAcquistato> getByIdAcquisto(int id) {

        ArticoloAcquistatoDAO aDAO = ArticoloAcquistatoDAO.getInstance();

        return aDAO.getByIdAcquisto(id);
    }

    public void addArticoloAcquistato(ArticoloAcquistato articoloAcquistato) {

        ArticoloAcquistatoDAO aDAO = ArticoloAcquistatoDAO.getInstance();

        aDAO.add(articoloAcquistato);

    }


    public ArticoloAcquistato getByArticoloAndCliente(int idArticolo, int idCliente) {

        ArticoloAcquistatoDAO aDAO = ArticoloAcquistatoDAO.getInstance();

        return aDAO.getByArticoloAndCliente(idArticolo, idCliente);
    }

    public void removeAcquistoByIdCliente(int idCliente){

        ArticoloAcquistatoDAO aDAO = ArticoloAcquistatoDAO.getInstance();

        aDAO.removeByIdUtente(idCliente);
    }

    public void removeArticolo(int idArticolo){

        ArticoloAcquistatoDAO aDAO = ArticoloAcquistatoDAO.getInstance();

        aDAO.removeArticolo(idArticolo);
    }
}
