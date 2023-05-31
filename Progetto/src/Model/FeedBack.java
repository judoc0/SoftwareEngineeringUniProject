package Model;

import java.util.Date;
//FACTHORY METHOD PATTERN
//Concrete product
public class FeedBack implements IFeedback{

    private int id;
    private int idCliente;
    private Cliente cliente;
    private String commento;
    private int idAcquisto;
    private int idArticolo;
    private int punteggio;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdAcquisto() {
        return idAcquisto;
    }

    public void setIdAcquisto(int idAcquisto) {
        this.idAcquisto = idAcquisto;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }
}
