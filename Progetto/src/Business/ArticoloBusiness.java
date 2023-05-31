package Business;

import DAO.*;
import Model.*;

import java.io.File;
import java.util.List;

public class ArticoloBusiness {

    private static ArticoloBusiness instance;     //SINGLETON PATTERN

    public enum TipoArticolo {PRODOTTO, SERVIZIO}

    public static synchronized ArticoloBusiness getInstance() {
        if (instance == null) instance = new ArticoloBusiness();
        return instance;
    }

    private ArticoloBusiness() {}

    public  List<Articolo> getProducts() {

        IArticoloDAO uDAO = ArticoloDAO.getInstance();

        List<Articolo> list = uDAO.findAll();

        for (Articolo a : list) {
            a.setId(a.getId());
            a.setNome(a.getNome());
            a.setPrezzo(a.getPrezzo());
            CategoriaArticolo cp = CategoriaArticoloDAO.getInstance().findById(a.getIdCategoria());
            a.setCategoria(cp);
            a.setDescrizione(a.getDescrizione());
            a.setFoto(a.getFoto());
        }

        return list;
    }

    public Articolo getOneProducts(int id) {

        IArticoloDAO uDao = ArticoloDAO.getInstance();

        Articolo a = uDao.getById(id);
        a.setId(a.getId());
        a.setNome(a.getNome());
        a.setPrezzo(a.getPrezzo());
        CategoriaArticolo cp = CategoriaArticoloDAO.getInstance().findById(a.getIdCategoria());
        a.setCategoria(cp);
        a.setDescrizione(a.getDescrizione());
        a.setFoto(a.getFoto());

        return a;
    }

    public boolean typeArticolo(Articolo a, TipoArticolo t) {

        IArticoloDAO uDao = ArticoloDAO.getInstance();

        if (TipoArticolo.PRODOTTO.equals(t)) {
            // vediamo se u è un manager
            return uDao.prodottoExists(a);
        }
        if (TipoArticolo.SERVIZIO.equals(t)) {
            // vediamo se u è un manager
            return uDao.servizioExists(a);
        }

        return false;
    }

    public void remove(int idArticolo) {

        IArticoloDAO uDao = ArticoloDAO.getInstance();

        // Usando la eliminazione a cascata nel database mysql per le tabelle coinvolte non abbiamo bisogno di eliminare 'manualmente' tutte le righe contenenti la foreign key idArticolo

      /*  if (typeArticolo(ArticoloBusiness.instance.getOneProducts(idArticolo), TipoArticolo.SERVIZIO)) {
            FeedbackBusiness.getInstance().removeCommentoArticolo(idArticolo);
            Lista_has_ArticoloBusiness.getInstance().deletefromAllListe(idArticolo);
            PuntoVendita_has_articoloBusiness.getInstance().removeAll(idArticolo);
            ServizioBusiness.getInstance().removeServizio(idArticolo);
            ArticoloAcquistatoBusiness.getInstance().removeArticolo(idArticolo);
        } else {
            FeedbackBusiness.getInstance().removeCommentoArticolo(idArticolo);
            Lista_has_ArticoloBusiness.getInstance().deletefromAllListe(idArticolo);
            ArticoloAcquistatoBusiness.getInstance().removeArticolo(idArticolo);

            int idPosizione = ProdottoBusiness.getInstance().getOneProducts(idArticolo).getPosizione().getIdPosizione();
            DisponibilitaBusiness.getInstance().removeDisponibilita(idArticolo);
            if (ProdottoCompositoBusiness.getInstance().compositoExists(idArticolo)) {
                ProdottoCompositoBusiness.getInstance().removeComponente(idArticolo);
            }
            PuntoVendita_has_articoloBusiness.getInstance().removeAll(idArticolo);
            ProdottoBusiness.getInstance().removeProdotto(idArticolo);
            PosizioneBusiness.getInstance().removePosizione(idPosizione);

        }*/
        if (typeArticolo(ArticoloBusiness.instance.getOneProducts(idArticolo), TipoArticolo.PRODOTTO)) {
            PosizioneBusiness.getInstance().removePosizione(ProdottoBusiness.getInstance().getOneProducts(idArticolo).getPosizione().getIdPosizione());
        }
        uDao.remove(idArticolo);
    }


    public void updateArticolo(Articolo a) {

        IArticoloDAO uDao = ArticoloDAO.getInstance();

        uDao.update(a);

        if (a.getPercorsoFoto() != null && !a.getPercorsoFoto().equals(""))
            uDao.inserisciFoto(a, new File(a.getPercorsoFoto()));
            //SE SI VUOLE GESTIRE FILE NON TROVATO
            //if (uDao.inserisciFoto(prodotto, new File(prodotto.getPercorsoFoto())) == -1)
    }




    public Articolo getByName(String name) {

        IArticoloDAO uDao = ArticoloDAO.getInstance();

        return uDao.getByName(name);
    }

    public Boolean nomeExists(String name) {


        List<Articolo> list = ArticoloBusiness.getInstance().getProducts();

        for (Articolo a : list) if (name.equals(a.getNome())) return true;

        return false;
    }

}
