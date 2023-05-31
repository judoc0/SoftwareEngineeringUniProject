package DAO;

import DBInterface.*;
import Model.Cliente;
import Model.ListaArticoli;
import Model.PuntoVendita;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class ClienteDAO implements IClienteDAO{

    private static ClienteDAO instance = new ClienteDAO();  //SINGLETON PATTERN
    private Cliente cliente;
    private static ResultSet rs;

    private ClienteDAO() {
        cliente = null;
        rs = null;
    }

    public static ClienteDAO getInstance() {
        return instance;
    } 
    
    public ArrayList<Cliente> findAll() {
        String sql = "SELECT * FROM utente as U INNER JOIN Cliente as C ON U.idUtente = C.Utente_idUtente;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        ArrayList<Cliente> clienti = new ArrayList<>();
        try {
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIdUtente(rs.getInt("idUtente"));
                cliente.setUsername(rs.getString("Username"));
                cliente.setPassword(rs.getString("Password"));
                cliente.setName(rs.getString("Name"));
                cliente.setSurname(rs.getString("Surname"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setPhoneNumber(rs.getString("PhoneNumber"));
                cliente.setAge(rs.getInt("Age"));
                cliente.setResidence(rs.getString("Residence"));
                cliente.setJob(rs.getString("Job"));
                cliente.setCanalePreferito(Cliente.CanalePreferito.valueOf(rs.getString("canale_preferito")));
                cliente.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                cliente.setStatoCliente(Cliente.StatoCliente.valueOf(rs.getString("StatoCliente")));
                clienti.add(cliente);
            }
            return clienti;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } 
        return null;
    }

 
    public Cliente getById(int id) {

        String sql = "SELECT * FROM utente as U INNER JOIN Cliente as C ON U.idUtente = C.Utente_idUtente WHERE U.idUtente = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                cliente = new Cliente();
                cliente.setIdUtente(rs.getInt("idUtente"));
                cliente.setUsername(rs.getString("Username"));
                cliente.setPassword(rs.getString("Password"));
                cliente.setName(rs.getString("Name"));
                cliente.setSurname(rs.getString("Surname"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setPhoneNumber(rs.getString("PhoneNumber"));
                cliente.setAge(rs.getInt("Age"));
                cliente.setResidence(rs.getString("Residence"));
                cliente.setJob(rs.getString("Job"));
                cliente.setCanalePreferito(Cliente.CanalePreferito.valueOf(rs.getString("canale_preferito")));
                cliente.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                cliente.setStatoCliente(Cliente.StatoCliente.valueOf(rs.getString("StatoCliente")));
            }
            return cliente;
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } 
        return null;
    }



    public ArrayList<Cliente> findClientiPuntoVendita(int id) {
      
        String sql = "SELECT * FROM utente as U INNER JOIN Cliente as C ON U.idUtente = C.Utente_idUtente WHERE C.PuntoVendita_idPuntoVendita = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        ArrayList<Cliente> clienti = new ArrayList<>();
        try {
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIdUtente(rs.getInt("idUtente"));
                cliente.setUsername(rs.getString("Username"));
                cliente.setPassword(rs.getString("Password"));
                cliente.setName(rs.getString("Name"));
                cliente.setSurname(rs.getString("Surname"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setPhoneNumber(rs.getString("PhoneNumber"));
                cliente.setAge(rs.getInt("Age"));
                cliente.setResidence(rs.getString("Residence"));
                cliente.setJob(rs.getString("Job"));
                cliente.setCanalePreferito(Cliente.CanalePreferito.valueOf(rs.getString("canale_preferito")));
                cliente.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                cliente.setStatoCliente(Cliente.StatoCliente.valueOf(rs.getString("StatoCliente")));
                clienti.add(cliente);
            }
            return clienti;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        }
        return null;
    }

    public int updateStatoCliente(Cliente cliente) {
      
        String sql = "UPDATE Cliente SET StatoCLiente = '" + cliente.getStatoCliente() + "' WHERE Utente_idUtente = '" + cliente.getIdUtente() + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        return 0;

    }

    public int setCliente(Cliente c) {

        String sql = "INSERT INTO Cliente (Utente_idUtente, PuntoVendita_idPuntoVendita, canale_preferito, StatoCLiente) VALUES('" + c.getIdUtente() + "','" + c.getIdPuntoVendita() + "','" + c.getCanalePreferito() + "','ATTIVO');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        return 0;
    }

    public void removeByIdUtente(int id) {
   
        String sql = "DELETE FROM Cliente WHERE Utente_idUtente = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
     
    }
}
