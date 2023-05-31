package Model;

public interface IPuntoVendita_has_articolo {

    int getIdPuntoVendita();

    void setIdPuntoVendita(int idPuntoVendita);

    PuntoVendita getPuntoVendita();

    void setPuntoVendita(PuntoVendita puntoVendita);

    int getIdArticolo();

    void setIdArticolo(int idArticolo);

    Articolo getArticolo();

    void setArticolo(Articolo articolo);
}
