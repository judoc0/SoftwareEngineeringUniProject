package Model;
//FACTHORY METHOD PATTERN
//Product
 public interface IListaArticoli {

     int getIdLista();

     void setIdLista(int idLista);

     int getIdCliente();

     void setIdCliente(int idCliente);

     String getNome();

     void setNome(String nome);

     ListaArticoli.StatoLista getStato();

     void setStato(ListaArticoli.StatoLista stato);

     String getData();

     void setData(String data);

     int getIdPuntoVendita();

     void setIdPuntoVendita(int idPuntoVendita);
}
