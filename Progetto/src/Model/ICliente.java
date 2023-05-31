package Model;

public interface ICliente extends IUtente{

    Cliente.CanalePreferito getCanalePreferito();

    void setCanalePreferito(Cliente.CanalePreferito canalePreferito);

    int getIdPuntoVendita();

    void setIdPuntoVendita(int idPuntovendita);

    Cliente.StatoCliente getStatoCliente();

    void setStatoCliente(Cliente.StatoCliente statoCliente);
}
