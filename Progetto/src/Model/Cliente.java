package Model;


public class Cliente extends Utente implements ICliente{

    public enum CanalePreferito {SMS, EMAIL, PUSH}
    public enum StatoCliente {ATTIVO, DISABILITATO}


    private int idPuntoVendita;
    private CanalePreferito canalePreferito;
    private StatoCliente statoCliente;

    public CanalePreferito getCanalePreferito() {
        return canalePreferito;
    }

    public void setCanalePreferito(CanalePreferito canalePreferito) {
        this.canalePreferito = canalePreferito;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntovendita) {
        this.idPuntoVendita = idPuntovendita;
    }

    public StatoCliente getStatoCliente() {
        return statoCliente;
    }

    public void setStatoCliente(StatoCliente statoCliente) {
        this.statoCliente = statoCliente;
    }
}
