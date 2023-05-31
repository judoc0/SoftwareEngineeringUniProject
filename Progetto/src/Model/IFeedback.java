package Model;

import java.util.Date;
//FACTHORY METHOD PATTERN
//Product
public interface IFeedback {

    int getId();

    void setId(int id);

    int getIdCliente();

    void setIdCliente(int idCliente);

    Cliente getCliente();

    void setCliente(Cliente cliente);

    String getCommento();

    void setCommento(String commento);

    int getPunteggio();

    void setPunteggio(int punteggio);

    Date getDate();

    void setDate(Date date);

    int getIdAcquisto();

    void setIdAcquisto(int idAcquisto);

    int getIdArticolo();;

    void setIdArticolo(int idArticolo);;
}
