package Model;
//ABSTRACT FACTHORY PATTERN
//Concrete product
public class Produttore implements IProduttore{

    private int idProduttore;
    private String nome;
    private String sito;
    private String citta;
    private String nazione;

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
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
