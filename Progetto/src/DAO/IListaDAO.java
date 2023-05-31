package DAO;

import Model.ListaArticoli;

import java.util.ArrayList;

public interface IListaDAO {

    ArrayList<ListaArticoli> findAll();

    ArrayList<ListaArticoli> getByIdCliente(int id);

    boolean listaExists(ListaArticoli listaArticoli);

    void add(ListaArticoli listaArticoli);

    void removeById(int idLista);

    ListaArticoli getListabyClienteAndNome(int idCliente, String nome);

    ListaArticoli getOneLista(int idLista);

    ArrayList<ListaArticoli> getByIdClientePuntoVendita(int idCliente, int idPuntoVendita);

    void update(ListaArticoli listaArticoli);
}
