package Model;
//ABSTRACT FACTHORY PATTERN
//Concrete product

//COMPOSITE PATTERN
//Leaf
public class Prodotto extends Articolo implements IProdotto{

    private int idPosizione;
    private Posizione posizione;
    private int idProduttore;
    private Produttore produttore;
    private Disponibilita disponibilita;
    private int quantita;

    public Prodotto() {}

    public int getIdPosizione() {
        return idPosizione;
    }

    public void setIdPosizione(int idPosizione) {
        this.idPosizione = idPosizione;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }

    public Disponibilita getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(Disponibilita disponibilita) {
        this.disponibilita = disponibilita;
    }

    @Override
    public String toString() {
        return 1 + " " + getNome();
    }
}
