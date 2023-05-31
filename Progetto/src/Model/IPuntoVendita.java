package Model;
//FACTHORY METHOD PATTERN
//Product
public interface IPuntoVendita {

    int getIdPuntoVendita();

    void setIdPuntoVendita(int idPuntoVendita);

    String getCitta();

    void setCitta(String citta);

    Magazzino getMagazzino();

    void setMagazzino(Magazzino magazzino);

    Manager getManager();

    void setManager(Manager manager);

}
