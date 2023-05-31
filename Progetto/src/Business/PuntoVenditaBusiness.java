package Business;

import DAO.PuntoVenditaDAO;
import Model.*;
import java.util.List;

public class PuntoVenditaBusiness {

    private static PuntoVenditaBusiness instance;     //SINGLETON PATTERN

    public static synchronized PuntoVenditaBusiness getInstance() {
        if (instance == null) instance = new PuntoVenditaBusiness();
        return instance;
    }

    private PuntoVenditaBusiness() {}

    public List<PuntoVendita> getPuntiVendita() {

        PuntoVenditaDAO pDAO = PuntoVenditaDAO.getInstance();

        List<PuntoVendita> list = pDAO.findAll();

        for (PuntoVendita p : list) {
            p.setIdPuntoVendita(p.getIdPuntoVendita());
            p.setCitta(p.getCitta());
            Magazzino m = MagazzinoBusiness.getInstance().getByIdPuntoVendita(p.getIdPuntoVendita());
            p.setMagazzino(m);
            p.setManager(ManagerBusiness.getInstance().getManagerByPuntoVendita(p.getIdPuntoVendita()));
        }


        return list;
    }

    public  PuntoVendita getOnePuntoVendita(int id) {

        PuntoVenditaDAO pDao = PuntoVenditaDAO.getInstance();

        PuntoVendita p = pDao.getById(id);

        p.setIdPuntoVendita(p.getIdPuntoVendita());
        p.setCitta(p.getCitta());
        Magazzino m = MagazzinoBusiness.getInstance().getByIdPuntoVendita(p.getIdPuntoVendita());
        p.setMagazzino(m);
        p.setManager(ManagerBusiness.getInstance().getManagerByPuntoVendita(p.getIdPuntoVendita()));

        return p;
    }

    public PuntoVendita geyByCity(String city) {

        PuntoVenditaDAO pDao = PuntoVenditaDAO.getInstance();

        PuntoVendita p = pDao.getByCity(city);

        p.setIdPuntoVendita(p.getIdPuntoVendita());
        p.setCitta(p.getCitta());
        Magazzino m = MagazzinoBusiness.getInstance().getByIdPuntoVendita(p.getIdPuntoVendita());
        p.setMagazzino(m);
        p.setManager(ManagerBusiness.getInstance().getManagerByPuntoVendita(p.getIdPuntoVendita()));

        return p;
    }


    public void addPuntoVendita(PuntoVendita puntoVendita) {

        PuntoVenditaDAO pDAO = PuntoVenditaDAO.getInstance();

        pDAO.add(puntoVendita); // adda il puntovendita

        PuntoVendita p = PuntoVenditaBusiness.getInstance().geyByCity(puntoVendita.getCitta());

        IMagazzinoFactory iMagazzinoFactory = new IMagazzinoFactory();
        IMagazzino iMagazzino = iMagazzinoFactory.getIMagazzino("MAGAZZINO");
        iMagazzino.setIdPuntoVendita(p.getIdPuntoVendita());

        MagazzinoBusiness.getInstance().addMagazzino((Magazzino) iMagazzino); //adda il magazzino al puntovendita

    }

    public NewPuntoVenditaResponse newPuntoVendita(PuntoVendita puntoVendita) {

        NewPuntoVenditaResponse res = new NewPuntoVenditaResponse();
        res.setMessage("Errore non definito.");

        PuntoVenditaDAO mDAO = PuntoVenditaDAO.getInstance();

        // 1. punto vendita già esistente
        if (mDAO.puntoVenditaExists(puntoVendita.getCitta())) {
            res.setMessage("Punto vendita già esistente.");
            res.setjOptionPane(0);
            res.setResult(NewPuntoVenditaResponse.newPuntoVenditaResult.PUNTO_ALREADY_EXISTS);
            return res;
        } else {
            // 3. aggiungere punto vendita
            PuntoVenditaBusiness.getInstance().addPuntoVendita(puntoVendita);

            res.setMessage("Nuovo punto vendita creato");
            res.setjOptionPane(1);
            res.setResult(NewPuntoVenditaResponse.newPuntoVenditaResult.NEW_PUNTO_OK);
        }

        return res;
    }
}
