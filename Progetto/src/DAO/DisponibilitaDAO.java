package DAO;

import DBInterface.*;
import Model.Disponibilita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;;
//COMMAND PATTERN
//Client
public class DisponibilitaDAO implements IDisponibilitaDAO{

    private static DisponibilitaDAO instance = new DisponibilitaDAO();  //SINGLETON PATTERN
    private Disponibilita disponibilita;
    private static ResultSet rs;

    private DisponibilitaDAO() {
        disponibilita = null;
        rs = null;
    }

    public static DisponibilitaDAO getInstance() {
        return instance;
    }


    public ArrayList<Disponibilita> findAll() {
        String sql = "SELECT * FROM Disponibilita;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<Disponibilita> disponibilitas = new ArrayList<>();
        try {
            while (rs.next()) {
                disponibilita = new Disponibilita();
                disponibilita.setIdMagazzino(rs.getInt("Magazzino_idMagazzino"));
                disponibilita.setIdProdotto(rs.getInt("Prodotto_Articolo_idArticolo"));
                disponibilita.setQuantita(rs.getInt("quantita"));
                disponibilitas.add(disponibilita);
            }
            return disponibilitas;
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

    public ArrayList<Disponibilita> find(int idMagazzino) {

        String sql = "SELECT * FROM Disponibilita WHERE Magazzino_idMagazzino = '"+idMagazzino+"';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<Disponibilita> disponibilitas = new ArrayList<>();
        try {
            while (rs.next()) {
                disponibilita = new Disponibilita();
                disponibilita.setIdMagazzino(rs.getInt("Magazzino_idMagazzino"));
                disponibilita.setIdProdotto(rs.getInt("Prodotto_Articolo_idArticolo"));
                disponibilita.setQuantita(rs.getInt("quantita"));
                disponibilitas.add(disponibilita);
            }
            return disponibilitas;
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


    public Disponibilita getById(int id, int idMagazzino) {

        String sql = "SELECT * FROM Disponibilita WHERE Prodotto_Articolo_idArticolo = '" + id + "' AND Magazzino_idMagazzino = '" + idMagazzino + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                disponibilita = new Disponibilita();
                disponibilita.setIdMagazzino(rs.getInt("Magazzino_idMagazzino"));
                disponibilita.setIdProdotto(rs.getInt("Prodotto_Articolo_idArticolo"));
                disponibilita.setQuantita(rs.getInt("quantita"));
                return disponibilita;
            }
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


    public void addQuantity(int idArticolo, int quantita, int idMagazzino) {

        String sql = "UPDATE Disponibilita SET quantita = '" + quantita + "' WHERE Prodotto_Articolo_idArticolo = '" + idArticolo + "' AND Magazzino_idMagazzino = '" + idMagazzino + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    public void rifornimento(Disponibilita d) {
      
        String sql = "UPDATE Disponibilita SET quantita = '" + d.getQuantita() + "' WHERE Prodotto_Articolo_idArticolo = '" + d.getIdProdotto() + "' AND Magazzino_idMagazzino = '" + d.getIdMagazzino() + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }


    public void remove(int idArticolo) {
      
        String sql = "DELETE FROM Disponibilita WHERE Prodotto_Articolo_idArticolo = '" + idArticolo + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
   
    }

    public void addDisponibilita(Disponibilita disponibilita) {
        String sql = "INSERT INTO Disponibilita (Magazzino_idMagazzino, Prodotto_Articolo_idArticolo, quantita) VALUES ('"+ disponibilita.getIdMagazzino() + "','" + disponibilita.getIdProdotto() + "','" + disponibilita.getQuantita() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    public void removebyMagazzino(int idArticolo, int idMagazzino) {

        String sql = "DELETE FROM Disponibilita WHERE Prodotto_Articolo_idArticolo = '" + idArticolo + "' AND Magazzino_idMagazzino = '" + idMagazzino + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        
    }
}
