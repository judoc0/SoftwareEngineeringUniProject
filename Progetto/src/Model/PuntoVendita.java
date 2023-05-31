package Model;
//FACTHORY METHOD PATTERN
//Concrete product
public class PuntoVendita implements IPuntoVendita{

    private int idPuntoVendita;
    private String citta;
    private Magazzino Magazzino;
    private Manager manager;

    public PuntoVendita() {
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Magazzino getMagazzino() {
        return Magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        Magazzino = magazzino;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
