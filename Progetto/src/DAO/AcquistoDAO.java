package DAO;

import DBInterface.*;
import Model.Acquisto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class AcquistoDAO implements IAcquistoDAO{
    private static AcquistoDAO instance = new AcquistoDAO();  //SINGLETON PATTERN
    private Acquisto acquisto;
    private static ResultSet rs;

    private AcquistoDAO() {
        acquisto = null;
        rs = null;
    }

    public static AcquistoDAO getInstance() {
        return instance;
    }

    public ArrayList<Acquisto> findAll() {
        String sql = "SELECT * FROM Acquisto;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
        ArrayList<Acquisto> acquisti = new ArrayList<>();
        try {
            while (rs.next()) {
                acquisto = new Acquisto();
                acquisto.setIdAcquisto(rs.getInt("idAcquisto"));
                acquisto.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
                acquisto.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                acquisti.add(acquisto);
            }
            return acquisti;
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

    public ArrayList<Acquisto> findAcquistiPuntoVendita(int idPuntoVendita) {
        String sql = "SELECT * FROM Acquisto WHERE PuntoVendita_idPuntoVendita = '" + idPuntoVendita + "';";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        ArrayList<Acquisto> acquisti = new ArrayList<>();
        try {
            while (rs.next()) {
                acquisto = new Acquisto();
                acquisto.setIdAcquisto(rs.getInt("idAcquisto"));
                acquisto.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
                acquisto.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                acquisti.add(acquisto);
            }
            return acquisti;
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

    public Acquisto getById(int idAcquisto) {

        String sql = "SELECT * FROM Acquisto WHERE idAcquisto = '" + idAcquisto + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                acquisto = new Acquisto();
                acquisto.setIdAcquisto(rs.getInt("idAcquisto"));
                acquisto.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
                acquisto.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
            }
            return acquisto;
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

    public int add(Acquisto acquisto) {
        String sql = "INSERT INTO Acquisto (Cliente_Utente_idUtente, PuntoVendita_idPuntoVendita) VALUES ('"+ acquisto.getIdCliente() + "','" + acquisto.getIdPuntoVendita() + "');";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
        return 0;
    }

    public Acquisto getLastAcquisto() {

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT * FROM Acquisto ORDER BY idAcquisto DESC LIMIT 1";
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if (rs.getRow()==1) {
                acquisto = new Acquisto();
                acquisto.setIdAcquisto(rs.getInt("idAcquisto"));
                acquisto.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
                acquisto.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
            }
            return acquisto;
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

    public void removeAcquistoByIdCliente(int id) {
        String sql = "DELETE FROM Acquisto WHERE Cliente_Utente_idUtente = '" + id + "';";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }
}
