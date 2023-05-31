package Model;
//ABSTRACT FACTHORY PATTERN
//Concrete product
public class Servizio extends Articolo implements IServizio{

    private int idFornitore;
    private Fornitore fornitore;

    public int getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(int idFornitore) {
        this.idFornitore = idFornitore;
    }

    @Override
    public Fornitore getFornitore() {
        return fornitore;
    }

    @Override
    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }
}
