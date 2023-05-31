package Model;

//ABSTRACT FACTHORY PATTERN
//Concrete product
public class Fornitore implements IFornitore{

    private int idFornitore;
    private String nome;
    private String sito;
    private String citta;
    private String nazione;

    public int getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(int idFornitore) {
        this.idFornitore = idFornitore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSito() {
        return sito;
    }

    public void setSito(String sito) {
        this.sito = sito;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
}
