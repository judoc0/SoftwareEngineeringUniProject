package Model;

public class PuntoVendita_has_articolo implements IPuntoVendita_has_articolo{

    private int idPuntoVendita;
    private PuntoVendita puntoVendita;
    private int idArticolo;
    private Articolo articolo;

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public Articolo getArticolo() {
        return articolo;
    }

    public void setArticolo(Articolo articolo) {
        this.articolo = articolo;
    }
}
