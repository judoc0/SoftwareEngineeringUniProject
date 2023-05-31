package Model;
//FACTHORY METHOD PATTERN
//Product
public interface IDisponibilita {

     int getIdMagazzino();;

     void setIdMagazzino(int idMagazzino); 

     int getIdProdotto();

     void setIdProdotto(int idProdotto);

     int getQuantita(); 

     void setQuantita(int quantita); 

     Prodotto getProdotto();

     void setProdotto(Prodotto prodotto);
}
