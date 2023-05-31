package Business;

import DAO.*;
import Model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProdottoBusiness {

    private static ProdottoBusiness instance;     //SINGLETON PATTERN

    public static synchronized ProdottoBusiness getInstance() {
        if (instance == null) instance = new ProdottoBusiness();
        return instance;
    }

    private ProdottoBusiness() {}

    public NewArticoloResponse newProdotto(Prodotto prodotto) {

        NewArticoloResponse res = new NewArticoloResponse();

        res.setMessage("Errore non definito.");

        ArticoloDAO uDao = ArticoloDAO.getInstance();
        ProdottoDAO pDAO = ProdottoDAO.getInstance();

        // 1. articolo già esistente
        if (uDao.articoloExists(prodotto)) {
            res.setMessage("Articolo già esistente.");
            res.setjOptionPane(0);
            res.setResult(NewArticoloResponse.newArticoloResult.ARTICOLO_ALREADY_EXISTS);
            return res;
        }
        else if (prodotto.getNome().equals("")) {
            res.setMessage("Non puoi creare un articolo senza nome.");
            res.setjOptionPane(0);
            res.setResult(NewArticoloResponse.newArticoloResult.ARTICOLO_ALREADY_EXISTS);
            return res;
        }
        else {
            // 3. aggiungere i dati dell'articolo
            Posizione posizione = prodotto.getPosizione();
            if (!PosizioneBusiness.getInstance().posizioneExists(posizione)) {
                PosizioneBusiness.getInstance().addPosizione(posizione);
                prodotto.setIdPosizione(PosizioneBusiness.getInstance().getByPosizione(posizione).getIdPosizione());
            } else {
                res.setMessage("Posizione già occupata.");
                res.setjOptionPane(0);
                return res;
            }
            uDao.add(prodotto);
            int id = ArticoloBusiness.getInstance().getByName(prodotto.getNome()).getId();
            prodotto.setId(id);
            pDAO.addProdotto(prodotto);
            if (!"".equals(prodotto.getPercorsoFoto())) {
                uDao.inserisciFoto(prodotto, new File(prodotto.getPercorsoFoto()));
                //SE LA FOTO NON VIENE TROVATA
                /*if (uDao.inserisciFoto(prodotto, new File(prodotto.getPercorsoFoto())) == -1)
                res.setMessage("Foto non esiste.");
                res.setResult(NewArticoloResponse.newArticoloResult.ARTICOLO_ALREADY_EXISTS);
                return res;*/
            }
            res.setMessage("Nuovo prodotto creato");
            res.setjOptionPane(1);
            res.setResult(NewArticoloResponse.newArticoloResult.NEW_ARTICOLO_OK);
        }

        return res;
    }

    public  List<Prodotto> getProducts() {

        IProdottoDAO uDAO = ProdottoDAO.getInstance();

        List<Prodotto> list = uDAO.findAll();

        for (Prodotto p : list) {
            p.setId(p.getId());
            p.setNome(p.getNome());
            p.setPrezzo(p.getPrezzo());
            CategoriaArticolo cp = CategoriaArticoloDAO.getInstance().findById(p.getIdCategoria());
            p.setCategoria(cp);
            p.setDescrizione(p.getDescrizione());
            p.setFoto(p.getFoto());
            p.setPosizione(PosizioneBusiness.getInstance().getOnePosizione(p.getIdPosizione()));
            Produttore pr = ProduttoreBusiness.getInstance().getOneProduttore(p.getIdProduttore());
            p.setProduttore(pr);
            List<FeedBack> fb = FeedbackBusiness.getInstance().getAllCommentiArticolo(p.getId());
            p.setFeedbacks(fb);
        }

        return list;
    }

    public  Prodotto getOneProducts(int id) {

        IProdottoDAO uDao = ProdottoDAO.getInstance();
        Prodotto p = uDao.getById(id);

        p.setId(p.getId());
        p.setNome(p.getNome());
        p.setPrezzo(p.getPrezzo());
        p.setIdCategoria(p.getIdCategoria());
        CategoriaArticolo cp = CategoriaArticoloDAO.getInstance().findById(p.getIdCategoria());
        p.setCategoria(cp);
        p.setDescrizione(p.getDescrizione());
        p.setFoto(p.getFoto());
        p.setPosizione(PosizioneBusiness.getInstance().getOnePosizione(p.getIdPosizione()));
        Produttore pr = ProduttoreBusiness.getInstance().getOneProduttore(p.getIdProduttore());
        p.setProduttore(pr);
        List<FeedBack> fb = FeedbackBusiness.getInstance().getAllCommentiArticolo(p.getId());
        p.setFeedbacks(fb);

        return p;
    }


    public void removeProdotto(int idArticolo) {

        IProdottoDAO uDao = ProdottoDAO.getInstance();

        uDao.removeProdotto(idArticolo);

    }

    public Prodotto getByName(String name) {

        IProdottoDAO uDao = ProdottoDAO.getInstance();

        return uDao.getByName(name);
    }

    public void updateProdotto(Prodotto p) {

        IProdottoDAO uDao = ProdottoDAO.getInstance();

        uDao.updateProdotto(p);

    }
}
