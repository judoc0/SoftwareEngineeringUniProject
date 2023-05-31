package Model;
//ABSTRACT FACTHORY PATTERN
//Abstract Products
public interface IFornitore extends IAzienda{

    String getNome();

    void setNome(String nome);

    String getSito();

    void setSito(String sito);

    String getCitta();

    void setCitta(String citta);

    String getNazione();

    void setNazione(String nazione);

}
