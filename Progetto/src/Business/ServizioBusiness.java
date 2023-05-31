package Business;

import DAO.*;
import Model.*;
import org.bouncycastle.crypto.generators.BCrypt;

import java.io.File;
import java.security.MessageDigest;
import java.util.List;

public class ServizioBusiness {

    private static ServizioBusiness instance;     //SINGLETON PATTERN

    public static synchronized ServizioBusiness getInstance() {
        if (instance == null) instance = new ServizioBusiness();
        return instance;
    }

    private ServizioBusiness() {}

    public  List<Servizio> getServizi() {

        ServizioDAO uDAO = ServizioDAO.getInstance();

        List<Servizio> list = uDAO.findAll();

        for (Servizio s : list) {
            s.setId(s.getId());
            s.setNome(s.getNome());
            s.setPrezzo(s.getPrezzo());
            s.setCategoria(s.getCategoria());
            s.setDescrizione(s.getDescrizione());
            s.setFoto(s.getFoto());
            Fornitore f = FornitoreBusiness.getInstance().getOneFornitore(s.getIdFornitore());
            s.setFornitore(f);
            List<FeedBack> fb = FeedbackBusiness.getInstance().getAllCommentiArticolo(s.getId());
            s.setFeedbacks(fb);
        }

        return list;
    }

    public Servizio getOneServizio(int id) {
        // usare la classe ProdottoDAO per inviare la query al db che mi prende tutti i prodotti

        ServizioDAO uDao = ServizioDAO.getInstance();

        Servizio s = uDao.getById(id);

        s.setId(s.getId());
        s.setNome(s.getNome());
        s.setPrezzo(s.getPrezzo());
        s.setCategoria(s.getCategoria());
        s.setDescrizione(s.getDescrizione());
        s.setFoto(s.getFoto());
        Fornitore f = FornitoreBusiness.getInstance().getOneFornitore(s.getIdFornitore());
        s.setFornitore(f);
        List<FeedBack> fb = FeedbackBusiness.getInstance().getAllCommentiArticolo(s.getId());
        s.setFeedbacks(fb);

        return s;
    }


    public void removeServizio(int idArticolo) {

        ServizioDAO uDao = ServizioDAO.getInstance();

        uDao.remove(idArticolo);
    }

    public NewArticoloResponse newServizio(Servizio s) {
        NewArticoloResponse res = new NewArticoloResponse();
       
        res.setMessage("Errore non definito.");

        ArticoloDAO uDao = ArticoloDAO.getInstance();
        ServizioDAO sDAO = ServizioDAO.getInstance();

        // 1. articolo già esistente
        if (uDao.articoloExists(s)) {
            res.setMessage("Articolo già esistente.");
            res.setjOptionPane(0);
            res.setResult(NewArticoloResponse.newArticoloResult.ARTICOLO_ALREADY_EXISTS);
            return res;
        }
        else if (s.getNome().equals("")) {
            res.setMessage("Non puoi creare un articolo senza nome.");
            res.setjOptionPane(0);
            res.setResult(NewArticoloResponse.newArticoloResult.ARTICOLO_ALREADY_EXISTS);
            return res;
        }
        else {
            // 3. aggiungere i dati dell'articolo
            uDao.add(s);
            s.setId(ArticoloBusiness.getInstance().getByName(s.getNome()).getId());
            sDAO.addServizio(s);
            uDao.inserisciFoto(s,new File(s.getPercorsoFoto()));
            //SE SI VUOLE GESTIRE FILE NON TROVATO
                /*if (uDao.inserisciFoto(prodotto, new File(prodotto.getPercorsoFoto())) == -1)
                res.setMessage("Foto non esiste.");
                res.setResult(NewArticoloResponse.newArticoloResult.ARTICOLO_ALREADY_EXISTS);
                return res;*/
            res.setMessage("Nuovo servizio creato");
            res.setjOptionPane(1);
            res.setResult(NewArticoloResponse.newArticoloResult.NEW_ARTICOLO_OK);
        }

        return res;
    }
}
