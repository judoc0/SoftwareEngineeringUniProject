package Business;

import DAO.ArticoloDAO;
import DAO.DisponibilitaDAO;
import DAO.PosizioneDAO;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class DisponibilitaBusiness {

    private static DisponibilitaBusiness instance;     //SINGLETON PATTERN

    public static synchronized DisponibilitaBusiness getInstance() {
        if (instance == null) instance = new DisponibilitaBusiness();
        return instance;
    }

    private DisponibilitaBusiness() {}

    public List<Disponibilita> getAllDisponibilita() {

    DisponibilitaDAO dDAO = DisponibilitaDAO.getInstance();

    List<Disponibilita> list = dDAO.findAll();

        for (Disponibilita d : list) {
        d.setIdMagazzino(d.getIdMagazzino());
        Prodotto p = ProdottoBusiness.getInstance().getOneProducts(d.getIdProdotto());
        d.setProdotto(p);
        d.setQuantita(d.getQuantita());
        }

        sortDisponiblita(list);

        return list;
    }

    public List<Disponibilita> getDisponibilita(int idMagazzino) {

        DisponibilitaDAO dDAO = DisponibilitaDAO.getInstance();


        List<Disponibilita> list = dDAO.find(idMagazzino);

        for (Disponibilita d : list) {
            d.setIdMagazzino(d.getIdMagazzino());
            Prodotto p = ProdottoBusiness.getInstance().getOneProducts(d.getIdProdotto());
            d.setProdotto(p);
            d.setQuantita(d.getQuantita());
        }

        sortDisponiblita(list);

        return list;
    }

    public  Disponibilita getOneDisponibilita(int id, int idMagazzino) {

        DisponibilitaDAO dDAO = DisponibilitaDAO.getInstance();

        return dDAO.getById(id, idMagazzino);
    }

    public void removeQuantityMagazzino(int idArticolo, int quantity, int idMagazzino) {

        DisponibilitaDAO dDAO = DisponibilitaDAO.getInstance();

        quantity = getOneDisponibilita(idArticolo, idMagazzino).getQuantita() - quantity;

        dDAO.addQuantity(idArticolo, quantity, idMagazzino);
    }

    public void sortDisponiblita(List<Disponibilita> disponibilitaList) {
        //STRATEGY PATTERN
        //Client
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        IDisponibilitaSortStrategy strategy = new ALotDisponibilitaSort();
        SortedDisponibilitaList sortedDisponibilitaList = new SortedDisponibilitaList(disponibilitaList);

        if (UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.MANAGE_SHOP))
                strategy = new UrgentFirstDisponibilitaSort();

        sortedDisponibilitaList.setSortStrategy(strategy);
        sortedDisponibilitaList.sort();
    }

    public void rifornimento(int idArticolo, int quantita) {
        DisponibilitaDAO dDAO = DisponibilitaDAO.getInstance();

        Disponibilita disponibilita = DisponibilitaBusiness.getInstance().getOneDisponibilita(idArticolo, SessionManager.getInstance().getCurrentPuntoVendita().getMagazzino().getIdMagazzino());

        int addsum = disponibilita.getQuantita() + quantita;

        disponibilita.setQuantita(addsum);

        dDAO.rifornimento(disponibilita);
    }

    public void removeDisponibilita(int idArticolo) {

        DisponibilitaDAO dDAO = DisponibilitaDAO.getInstance();

        dDAO.remove(idArticolo);
    }

    public void removebyMagazzino(int idArticolo, int idMagazzino) {

        DisponibilitaDAO dDAO = DisponibilitaDAO.getInstance();

        dDAO.removebyMagazzino(idArticolo, idMagazzino);
    }


    public void addDisponibilita(Disponibilita disponibilita) {
        DisponibilitaDAO dDAO = DisponibilitaDAO.getInstance();

        dDAO.addDisponibilita(disponibilita);
    }

}
