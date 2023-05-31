package Model;
//FACTHORY METHOD PATTERN
//Concrete product
public class Disponibilita implements IDisponibilita{

    private int idMagazzino;
    private int idProdotto;
    private Prodotto prodotto;
    private int quantita;

    public Disponibilita() {
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }
}
