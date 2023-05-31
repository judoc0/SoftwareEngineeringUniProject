package DAO;

import DBInterface.*;
import Model.ArticoloAcquistato;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//COMMAND PATTERN
//Client
public class ArticoloAcquistatoDAO implements IArticoloAcquistatoDAO{
    private static ArticoloAcquistatoDAO instance = new ArticoloAcquistatoDAO();  //SINGLETON PATTERN
    private ArticoloAcquistato articoloAcquistato;
    private static ResultSet rs;

    private ArticoloAcquistatoDAO() {
        articoloAcquistato = null;
        rs = null;
    }

    public static ArticoloAcquistatoDAO getInstance() {
        return instance;
    }


    public ArrayList<ArticoloAcquistato> findAll() {
        String sql = "SELECT * FROM ArticoloAcquistato;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        ArrayList<ArticoloAcquistato> articoliAcquistati = new ArrayList<>();
        try {
            while (rs.next()) {
                articoloAcquistato = new ArticoloAcquistato();
                articoloAcquistato.setIdAcquisto(rs.getInt("Acquisto_idAcquisto"));
                articoloAcquistato.setIdArticolo(rs.getInt("Articolo_idArticolo"));
                articoloAcquistato.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
                articoliAcquistati.add(articoloAcquistato);
            }
            return articoliAcquistati;
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

    public List<ArticoloAcquistato> getByIdAcquisto(int idAcquisto) {

        String sql = "SELECT * FROM ArticoloAcquistato WHERE Acquisto_idAcquisto = '" + idAcquisto + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        ArrayList<ArticoloAcquistato> articoliAcquistati = new ArrayList<>();

        try {
            rs.next();
            if (rs.getRow()==1) {
                articoloAcquistato = new ArticoloAcquistato();
                articoloAcquistato.setIdAcquisto(rs.getInt("Acquisto_idAcquisto"));
                articoloAcquistato.setIdArticolo(rs.getInt("Articolo_idArticolo"));
                articoloAcquistato.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
                articoliAcquistati.add(articoloAcquistato);
            }
            return articoliAcquistati;
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

    public void add(ArticoloAcquistato articoloAcquistato) {
        String sql = "INSERT INTO ArticoloAcquistato (Acquisto_idAcquisto, Articolo_idArticolo, Cliente_Utente_idUtente) VALUES ('"+ articoloAcquistato.getIdAcquisto() + "','" + articoloAcquistato.getIdArticolo() + "','" + articoloAcquistato.getIdCliente() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    public ArticoloAcquistato getByArticoloAndCliente(int idArticolo, int idCliente) {

        String sql = "SELECT * FROM ArticoloAcquistato WHERE Articolo_idArticolo = '" + idArticolo + "' AND Cliente_Utente_idUtente = '" + idCliente + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                articoloAcquistato = new ArticoloAcquistato();
                articoloAcquistato.setIdAcquisto(rs.getInt("Acquisto_idAcquisto"));
                articoloAcquistato.setIdArticolo(rs.getInt("Articolo_idArticolo"));
                articoloAcquistato.setIdCliente(rs.getInt("Cliente_Utente_idUtente"));
            }
            return articoloAcquistato;
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

    public void removeByIdUtente(int id) {
        String sql = "DELETE FROM ArticoloAcquistato WHERE Cliente_Utente_idUtente = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    public void removeArticolo(int idArticolo) {

        String sql = "DELETE FROM ArticoloAcquistato WHERE Articolo_idArticolo = '" + idArticolo + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }
}
