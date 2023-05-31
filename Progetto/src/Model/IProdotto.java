package Model;
//ABSTRACT FACTHORY PATTERN
//Abstract Products
//COMPOSITE PATTERN
//Component
public interface IProdotto extends IArticolo{

     int getIdPosizione();

     void setIdPosizione(int idPosizione);

     Posizione getPosizione();

     void setPosizione(Posizione posizione);

     int getIdProduttore();

     void setIdProduttore(int idProduttore);

     Produttore getProduttore();

     void setProduttore(Produttore produttore);

     int getQuantita();

     void setQuantita(int quantita);
}
