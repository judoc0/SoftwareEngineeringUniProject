package DAO;

import Model.Cliente;

import java.util.ArrayList;

public interface IClienteDAO {

    ArrayList<Cliente> findAll();

    Cliente getById(int id);

    ArrayList<Cliente> findClientiPuntoVendita(int id);

    int updateStatoCliente(Cliente cliente);

    int setCliente(Cliente cliente);

    void removeByIdUtente(int id);
}
