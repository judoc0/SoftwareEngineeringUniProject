package Model;
//FACTHORY METHOD PATTERN
//Product
public interface ILista_has_articolo {

    int getIdLista();

    void setIdLista(int idLista);

    int getIdArticolo();

    void setIdArticolo(int idArticolo);

    Articolo getArticolo();

    void setArticolo(Articolo articolo);

    int getQuantita();

    void setQuantita(int quantita);

    Posizione getPosizione();

    void setPosizione(Posizione posizione);
}
