package DAO;

import DBInterface.*;
import Model.ListaArticoli;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class ListaDAO implements IListaDAO{
    private static ListaDAO instance = new ListaDAO();  //SINGLETON PATTERN
    private ListaArticoli listaArticoli;
    private static ResultSet rs;

    private ListaDAO() {
        listaArticoli = null;
        rs = null;
    }

    public static ListaDAO getInstance() {
        return instance;
    }

    public ArrayList<ListaArticoli> findAll() {

        String sql = "SELECT * FROM Lista;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        ArrayList<ListaArticoli> listaArticolis = new ArrayList<>();
        try {
            while (rs.next()) {
                listaArticoli = new ListaArticoli();
                listaArticoli.setIdLista(rs.getInt("idLista"));
                listaArticoli.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
                listaArticoli.setNome(rs.getString("nome"));
                listaArticoli.setStato(ListaArticoli.StatoLista.valueOf(rs.getString("statoLista")));
                listaArticoli.setData(rs.getString("dataAcquisto"));
                listaArticoli.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                listaArticolis.add(listaArticoli);
            }
            return listaArticolis;
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

    public ArrayList<ListaArticoli> getByIdCliente(int id) {


        String sql = "SELECT * FROM Lista WHERE Cliente_Utente_idUtente = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<ListaArticoli> listaArticolis = new ArrayList<>();
        try {
            while (rs.next()) {
                listaArticoli = new ListaArticoli();
                listaArticoli.setIdLista(rs.getInt("idLista"));
                listaArticoli.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
                listaArticoli.setNome(rs.getString("nome"));
                listaArticoli.setStato(ListaArticoli.StatoLista.valueOf(rs.getString("statoLista")));
                listaArticoli.setData(rs.getString("dataAcquisto"));
                listaArticoli.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                listaArticolis.add(listaArticoli);
            }
            return listaArticolis;
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

    public boolean listaExists(ListaArticoli listaArticoli) {
        boolean listaExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Lista WHERE Cliente_Utente_idUtente = '" + listaArticoli.getIdCliente() + "' AND nome = '" + listaArticoli.getNome() + "';";
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) listaExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listaExists;
    }

    public void add(ListaArticoli listaArticoli) {

        String sql = "INSERT INTO Lista (idLista, Cliente_Utente_idUtente, nome, statoLista, dataAcquisto, PuntoVendita_idPuntoVendita) VALUES ('"+ listaArticoli.getIdLista() + "','" + listaArticoli.getIdCliente() + "','" + listaArticoli.getNome() + "','"+ listaArticoli.getStato() +"','" + listaArticoli.getData() + "','" + listaArticoli.getIdPuntoVendita() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }

    public void removeById(int idLista) {

        String sql = "DELETE FROM Lista WHERE idLista = '" + idLista + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }

    public ListaArticoli getListabyClienteAndNome(int idCliente, String nome) {

        String sql = "SELECT * FROM Lista WHERE Cliente_Utente_idUtente = '" + idCliente + "' AND nome = '"+ nome +  "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                listaArticoli = new ListaArticoli();
                listaArticoli.setIdLista(rs.getInt("idLista"));
                listaArticoli.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
                listaArticoli.setNome(rs.getString("nome"));
                listaArticoli.setStato(ListaArticoli.StatoLista.valueOf(rs.getString("statoLista")));
                listaArticoli.setData(rs.getString("dataAcquisto"));
                listaArticoli.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
            }
            return listaArticoli;
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

    public ListaArticoli getOneLista(int idLista) {

        String sql = "SELECT * FROM Lista WHERE idLista = '" + idLista + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        try {
            rs.next();
            listaArticoli = new ListaArticoli();
            listaArticoli.setIdLista(rs.getInt("idLista"));
            listaArticoli.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
            listaArticoli.setNome(rs.getString("nome"));
            listaArticoli.setStato(ListaArticoli.StatoLista.valueOf(rs.getString("statoLista")));
            listaArticoli.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
            return listaArticoli;
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

    public ArrayList<ListaArticoli> getByIdClientePuntoVendita(int idCliente, int idPuntoVendita) {

        String sql = "SELECT * FROM Lista WHERE Cliente_Utente_idUtente = '" + idCliente + "' AND PuntoVendita_idPuntoVendita = '" + idPuntoVendita + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<ListaArticoli> listaArticolis = new ArrayList<>();
        try {
            while (rs.next()) {
                listaArticoli = new ListaArticoli();
                listaArticoli.setIdLista(rs.getInt("idLista"));
                listaArticoli.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
                listaArticoli.setNome(rs.getString("nome"));
                listaArticoli.setStato(ListaArticoli.StatoLista.valueOf(rs.getString("statoLista")));
                listaArticoli.setData(rs.getString("dataAcquisto"));
                listaArticoli.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                listaArticolis.add(listaArticoli);
            }
            return listaArticolis;
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

    public void update(ListaArticoli listaArticoli) {

      String sql = "UPDATE Lista SET nome = '" + listaArticoli.getNome() + "', Cliente_Utente_idUtente = '" + listaArticoli.getIdCliente() + "', statoLista = '" + listaArticoli.getStato() + "', dataAcquisto = '" + listaArticoli.getData() + "', PuntoVendita_idPuntoVendita = '" + listaArticoli.getIdPuntoVendita() + "' WHERE idLista = '" + listaArticoli.getIdLista() + "';";
      DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
      IDbOperation op = new WriteOperation(sql);
      rs = dbOperationExecutor.executeOperation(op);

    }
}
