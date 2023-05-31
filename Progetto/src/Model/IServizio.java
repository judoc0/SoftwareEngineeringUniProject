package Model;
//ABSTRACT FACTHORY PATTERN
//Abstract Products
public interface IServizio extends IArticolo{

     int getIdFornitore();

     void setIdFornitore(int idProduttore);

     Fornitore getFornitore();

     void setFornitore(Fornitore fornitore);
}
