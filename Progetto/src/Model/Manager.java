package Model;

public class Manager extends Utente implements IManager{

    private int idPuntoVendita;

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

}
