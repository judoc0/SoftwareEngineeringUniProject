package Business;

import DAO.Lista_has_ArticoloDAO;
import Model.ArticoliLista;
import Model.Articolo;
import Model.ListaArticoli;

import java.util.List;

public class Lista_has_ArticoloBusiness {

    private static Lista_has_ArticoloBusiness instance;     //SINGLETON PATTERN

    public static synchronized Lista_has_ArticoloBusiness getInstance() {
        if (instance == null) instance = new Lista_has_ArticoloBusiness();
        return instance;
    }

    private Lista_has_ArticoloBusiness() {}

    public void addArticolo(ArticoliLista articoliLista) {

        Lista_has_ArticoloDAO hasDAO = Lista_has_ArticoloDAO.getInstance();

        hasDAO.add(articoliLista);
    }

    public boolean isEmpty(int idLista) {
        Lista_has_ArticoloDAO hasDAO = Lista_has_ArticoloDAO.getInstance();

        return hasDAO.isEmpty(idLista);

    }

    public boolean has_Articolo(int idLista, int idArticolo) {
        Lista_has_ArticoloDAO hasDAO = Lista_has_ArticoloDAO.getInstance();

        return hasDAO.has_articolo(idLista, idArticolo);
    }

    public ArticoliLista getOneArticoliLista(int idLista, int idArticolo) {
        Lista_has_ArticoloDAO hasDAO = Lista_has_ArticoloDAO.getInstance();

        return hasDAO.getOneArticoliLista(idLista, idArticolo);
    }

    public void addQuantity(ArticoliLista articoliLista) {
        Lista_has_ArticoloDAO hasDAO = Lista_has_ArticoloDAO.getInstance();

        int addsum = hasDAO.getOneArticoliLista(articoliLista.getIdLista(), articoliLista.getIdArticolo()).getQuantita() + articoliLista.getQuantita();
        articoliLista.setQuantita(addsum);

        hasDAO.updateQuantity(articoliLista);
    }

    public void removeQuantity(ArticoliLista articoliLista) {
        Lista_has_ArticoloDAO hasDAO = Lista_has_ArticoloDAO.getInstance();

        int addsub = hasDAO.getOneArticoliLista(articoliLista.getIdLista(), articoliLista.getIdArticolo()).getQuantita() - articoliLista.getQuantita();
        articoliLista.setQuantita(addsub);

        hasDAO.updateQuantity(articoliLista);

        if (hasDAO.getOneArticoliLista(articoliLista.getIdLista(), articoliLista.getIdArticolo()).getQuantita() == 0)
            Lista_has_ArticoloBusiness.getInstance().deletefromLista(articoliLista);
    }

    public List<ArticoliLista> getArticoli(int idLista) {
        Lista_has_ArticoloDAO hasDAO = Lista_has_ArticoloDAO.getInstance();
        List<ArticoliLista> lista = hasDAO.getArticoliLista(idLista);
        for (ArticoliLista a : lista) {
            Articolo articolo = ArticoloBusiness.getInstance().getOneProducts(a.getIdArticolo());
            a.setArticolo(articolo);
            if (ArticoloBusiness.getInstance().typeArticolo(articolo, ArticoloBusiness.TipoArticolo.PRODOTTO))
                a.setPosizione(PosizioneBusiness.getInstance().getOnePosizione(articolo.getId()));
        }

        return lista;
    }

    public String updateLista(List<ArticoliLista> list) {

        String text = "Quantità aggiornata dei seguenti articolo, dopo la riduzione delle scorte in magazzino:\n";
        String text1 = text;
        int idLista = list.get(0).getIdLista();
        if (ListaArticoliBusiness.getInstance().getOneLista(idLista).getStato().equals(ListaArticoli.StatoLista.NON_PAGATA)) {
            for (ArticoliLista articoliLista : list) {
                int idPuntoVendita = ListaArticoliBusiness.getInstance().getOneLista(articoliLista.getIdLista()).getIdPuntoVendita();
                int qMagazzino = 0;
                if (DisponibilitaBusiness.getInstance().getOneDisponibilita(articoliLista.getIdArticolo(), MagazzinoBusiness.getInstance().getByIdPuntoVendita(idPuntoVendita).getIdMagazzino()) != null)
                    qMagazzino = DisponibilitaBusiness.getInstance().getOneDisponibilita(articoliLista.getIdArticolo(), MagazzinoBusiness.getInstance().getByIdPuntoVendita(idPuntoVendita).getIdMagazzino()).getQuantita();
                if (articoliLista.getQuantita() > qMagazzino) {
                    text += articoliLista.getArticolo().getNome() + " da " + articoliLista.getQuantita() + " a " + qMagazzino;
                    articoliLista.setQuantita(articoliLista.getQuantita() - qMagazzino);
                    if (qMagazzino == 0) Lista_has_ArticoloBusiness.getInstance().deletefromLista(articoliLista);
                    else Lista_has_ArticoloBusiness.getInstance().removeQuantity(articoliLista);
                }
            }
        }
        if (text1.equals(text))
            return "";
        else return text;
    }

    public void deletefromLista(ArticoliLista articoliLista) {
        Lista_has_ArticoloDAO hasDAO = Lista_has_ArticoloDAO.getInstance();

        hasDAO.removeById(articoliLista);
    }

    public void deleteAllfromLista(int idLista) {
        Lista_has_ArticoloDAO hasDAO = Lista_has_ArticoloDAO.getInstance();

        hasDAO.removeAll(idLista);
    }

    public void deletefromAllListe(int idArticolo) {
        Lista_has_ArticoloDAO hasDAO = Lista_has_ArticoloDAO.getInstance();

        hasDAO.removefromAllListe(idArticolo);
    }

    public float getPrezzoLista(List<ArticoliLista> lista) {
        float totale = 0F;
        for (ArticoliLista a : lista) totale += a.getArticolo().getPrezzo() * a.getQuantita();

        return totale;
    }
}
