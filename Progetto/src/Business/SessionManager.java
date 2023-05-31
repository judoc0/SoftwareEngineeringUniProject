package Business;

import Model.ListaArticoli;
import Model.PuntoVendita;

import java.util.HashMap;

public class SessionManager {

    private static SessionManager instance;
    private static ListaArticoli currentList;
    private static ListaArticoli loginList;
    private static PuntoVendita currentPuntoVendita;
    private HashMap<String, Object> session = new HashMap<>();

    public static synchronized SessionManager getInstance() {
        if (instance == null) instance = new SessionManager();
        return instance;
    }

    public HashMap<String, Object> getSession() {
        return session;
    }

    private SessionManager(){
    }

    public ListaArticoli getCurrentList() {
        return currentList;
    }

    public void setCurrentList(ListaArticoli lista) {
        SessionManager.currentList = lista;
    }

    public ListaArticoli getLoginList() {
        return loginList;
    }

    public void setLoginList(ListaArticoli loginList) {
        SessionManager.loginList = loginList;
    }

    public PuntoVendita getCurrentPuntoVendita() {
        return currentPuntoVendita;
    }

    public void setCurrentPuntoVendita(PuntoVendita currentPuntoVendita) {
        SessionManager.currentPuntoVendita = currentPuntoVendita;
    }
}
