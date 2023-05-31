package Business;

import DAO.AcquistoDAO;
import Model.Acquisto;

import java.util.List;

public class AcquistoBusiness {

    private static AcquistoBusiness instance;     //SINGLETON PATTERN

    public static synchronized AcquistoBusiness getInstance() {
        if (instance == null) instance = new AcquistoBusiness();
        return instance;
    }

    private AcquistoBusiness() {}

    public List<Acquisto> getAllAcquisti() {

        AcquistoDAO aDAO = AcquistoDAO.getInstance();

        List<Acquisto> list = aDAO.findAll();

        for (Acquisto a : list) {
            a.setIdAcquisto(a.getIdAcquisto());
            a.setIdCliente(a.getIdCliente());
            a.setIdPuntoVendita(a.getIdPuntoVendita());
        }

        return list;
    }

    public List<Acquisto> getAcquistiPuntoVendita(int idPuntoVendita) {

        AcquistoDAO aDAO = AcquistoDAO.getInstance();

        List<Acquisto> list = aDAO.findAcquistiPuntoVendita(idPuntoVendita);

        for (Acquisto a : list) {
            a.setIdAcquisto(a.getIdAcquisto());
            a.setIdCliente(a.getIdCliente());
            a.setIdPuntoVendita(a.getIdPuntoVendita());
        }

        return list;
    }

    public Acquisto getOneAcquisto(int id) {

        AcquistoDAO aDAO = AcquistoDAO.getInstance();

        return aDAO.getById(id);
    }

    public int addAcquisto(Acquisto acquisto) {

        AcquistoDAO aDAO = AcquistoDAO.getInstance();

        return aDAO.add(acquisto);

    }

    public Acquisto getLastAcquisto() {

        AcquistoDAO aDAO = AcquistoDAO.getInstance();

        return aDAO.getLastAcquisto();
    }

    public void removeAcquistoByIdCliente(int idCliente){

        AcquistoDAO aDAO = AcquistoDAO.getInstance();

        aDAO.removeAcquistoByIdCliente(idCliente);
    }
}
