package Business;

import DAO.ClienteDAO;
import DAO.FeedbackDAO;
import Model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedbackBusiness {

    private static FeedbackBusiness instance;     //SINGLETON PATTERN

    public static synchronized FeedbackBusiness getInstance() {
        if (instance == null) instance = new FeedbackBusiness();
        return instance;
    }

    private FeedbackBusiness() {}

    public void sortFeedbacks(List<FeedBack> feedBackList) {
        //STRATEGY PATTERN
        //Client
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        IFeedbackSortStrategy strategy = new BestFirstFeedbackSort();
        SortedFeedbackList sortedFeedbackList = new SortedFeedbackList(feedBackList);
        if (u != null) {
            if (UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.MANAGE_SHOP))
                strategy= new UrgentFirstFeedbackSort();
            if (UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.CLIENT))
                strategy= new RecentFirstFeedbackSort();
        }

        sortedFeedbackList.setSortStrategy(strategy);
        sortedFeedbackList.sort();
    }

    public List<FeedBack> getAllCommentiArticolo(int idArticolo) {

        FeedbackDAO cDAO = FeedbackDAO.getInstance();

        List<FeedBack> list = cDAO.findAllArticolo(idArticolo);

        for (FeedBack f : list) {
            f.setId(f.getId());
            Cliente c = ClienteDAO.getInstance().getById(f.getIdCliente());
            f.setCliente(c);
            f.setCommento(f.getCommento());
            f.setPunteggio(f.getPunteggio());
            f.setDate(f.getDate());
            f.setIdAcquisto(f.getIdAcquisto());
            f.setIdArticolo(f.getIdArticolo());
        }

        sortFeedbacks(list);

        return list;
    }

    public List<FeedBack> findCommenti(int idPuntoVendita) {

        FeedbackDAO cDAO = FeedbackDAO.getInstance();

        List<FeedBack> list = cDAO.findAll();
        List<FeedBack> list1 = new ArrayList<>();

        for (FeedBack f : list) {
            if (AcquistoBusiness.getInstance().getOneAcquisto(f.getIdAcquisto()).getIdPuntoVendita() == idPuntoVendita) {
                FeedBack f1 = new FeedBack();
                f1.setId(f.getId());
                Cliente c = ClienteDAO.getInstance().getById(f.getIdCliente());
                f1.setCliente(c);
                f1.setCommento(f.getCommento());
                f1.setPunteggio(f.getPunteggio());
                f1.setDate(f.getDate());
                f1.setIdAcquisto(f.getIdAcquisto());
                f1.setIdArticolo(f.getIdArticolo());
                list1.add(f1);
            }
            sortFeedbacks(list1);
        }

        return list1;
    }

    public FeedBack getOneCommento(int id) {

        FeedbackDAO cDAO = FeedbackDAO.getInstance();

        return cDAO.getById(id);
    }

    public void addCommento(FeedBack f) {

        FeedbackDAO cDAO = FeedbackDAO.getInstance();

        cDAO.add(f);

    }

    public boolean CommentoExists(int idArticolo, int idCliente) {

        FeedbackDAO cDAO = FeedbackDAO.getInstance();

        return cDAO.CommentoExists(idArticolo, idCliente);
    }

    public FeedBack getByArticoloCliente(int idArticolo, int idCliente) {
        // usare la classe ProdottoDAO per inviare la query al db che mi prende tutti i prodotti

        FeedbackDAO cDAO = FeedbackDAO.getInstance();

        return cDAO.getByArticoloCliente(idArticolo, idCliente);
    }

    public int removeCommento(int idCommento) {

        FeedbackDAO cDAO = FeedbackDAO.getInstance();

        return cDAO.removeById(idCommento);
    }

    public int updateCommento(FeedBack feedback) {

        FeedbackDAO cDAO = FeedbackDAO.getInstance();

        return cDAO.update(feedback);
    }

    public int removeCommentoByCliente(int idCliente) {

        FeedbackDAO cDAO = FeedbackDAO.getInstance();

        return cDAO.removeByCliente(idCliente);
    }

    public void removeCommentoArticolo(int idArticolo) {

        FeedbackDAO cDAO = FeedbackDAO.getInstance();

        cDAO.removeCommentoArticolo(idArticolo);
    }

}
