package Model;
//FACTHORY METHOD PATTERN
//Concrete product
public class ListaArticoli implements IListaArticoli{

    public enum StatoLista { PAGATA, NON_PAGATA }

    private int idLista;
    private int idCliente;
    private String nome;
    private StatoLista stato;
    private String data;
    private int idPuntoVendita;


    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatoLista getStato() {
        return stato;
    }

    public void setStato(StatoLista stato) {
        this.stato = stato;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

}
