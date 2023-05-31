package Business;

import DAO.ListaDAO;
import Model.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListaArticoliBusiness {

    private static ListaArticoliBusiness instance;     //SINGLETON PATTERN

    public static synchronized ListaArticoliBusiness getInstance() {
        if (instance == null) instance = new ListaArticoliBusiness();
        return instance;
    }

    private ListaArticoliBusiness() {}

    public List<ListaArticoli> getAllListe() {

        ListaDAO listaDAO = ListaDAO.getInstance();

        List<ListaArticoli> list = listaDAO.findAll();

        for (ListaArticoli l : list) {
            l.setIdLista(l.getIdLista());
            l.setIdCliente(l.getIdCliente());
            l.setNome(l.getNome());
            l.setStato(l.getStato());
            l.setData(l.getData());
            l.setIdPuntoVendita(l.getIdPuntoVendita());
        }

        return list;
    }

    public List<ListaArticoli> getClienteListe(int id) {

        ListaDAO listaDAO = ListaDAO.getInstance();

        return listaDAO.getByIdCliente(id);
    }

    public NewListaResponse newLista(ListaArticoli listaArticoli) {

        ListaDAO listaDAO = ListaDAO.getInstance();
        NewListaResponse res = new NewListaResponse();
        res.setMessage("Errore non definito.");

        // 1. username già esistente
        if (listaDAO.listaExists(listaArticoli)) {
            res.setMessage("Lista già esistente.");
            res.setjOptionPane(0);
            res.setResult(NewListaResponse.newListaResult.LISTA_ALREADY_EXISTS);
            return res;
        } else {
            // 3. aggiungere i dati
            listaDAO.add(listaArticoli);
            res.setMessage("Nuova lista acquisto creata");
            res.setjOptionPane(1);
            res.setListaArticoli(listaArticoli);
            res.setResult(NewListaResponse.newListaResult.NEW_LISTA_OK);
        }

        return res;
    }

    public ListaArticoli getbyClienteAndNome(int idCLiente, String nome) {

        ListaDAO listaDAO = ListaDAO.getInstance();

        return listaDAO.getListabyClienteAndNome(idCLiente, nome);
    }

    public void removeById(int idLista) {

        ListaDAO listaDAO = ListaDAO.getInstance();

        Lista_has_ArticoloBusiness.getInstance().deleteAllfromLista(idLista);

        listaDAO.removeById(idLista);
    }

    public ListaArticoli getOneLista(int id) {

        ListaDAO listaDAO = ListaDAO.getInstance();

        return listaDAO.getOneLista(id);
    }

    public void pagaLista(ListaArticoli listaArticoli) {

        ListaDAO listaDAO = ListaDAO.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        listaArticoli.setData(formatter.format(date)+"");
        listaArticoli.setStato(ListaArticoli.StatoLista.PAGATA);

        listaDAO.update(listaArticoli);
    }

    public List<ListaArticoli> getPuntovenditaClienteListe(int idCliente, int idPuntoVendita) {

        ListaDAO listaDAO = ListaDAO.getInstance();

        return listaDAO.getByIdClientePuntoVendita(idCliente, idPuntoVendita);
    }

    public NewListaResponse changeName(ListaArticoli listaArticoli) {

        ListaDAO listaDAO = ListaDAO.getInstance();
        NewListaResponse res = new NewListaResponse();
        res.setMessage("Errore non definito.");

        if (listaDAO.listaExists(listaArticoli)) {
            res.setMessage("Nome già esistente.");
            res.setjOptionPane(0);
            res.setResult(NewListaResponse.newListaResult.LISTA_ALREADY_EXISTS);
            return res;
        } else {
            // 3. aggiungere i dati dall'utente
            listaDAO.update(listaArticoli);
            res.setjOptionPane(1);
            res.setMessage("Nome cambiato");
            res.setResult(NewListaResponse.newListaResult.NEW_LISTA_OK);
        }
        return res;
    }

    public void removeAllListeCliente(int idCliente) {

        List<ListaArticoli> list = ListaArticoliBusiness.getInstance().getClienteListe(idCliente);

        for (ListaArticoli l : list) {
            Lista_has_ArticoloBusiness.getInstance().deleteAllfromLista(l.getIdLista());
            ListaArticoliBusiness.getInstance().removeById(l.getIdLista());
        }
    }

}
