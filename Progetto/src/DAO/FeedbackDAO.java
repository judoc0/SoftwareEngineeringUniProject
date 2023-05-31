package DAO;

import DBInterface.*;
import Model.FeedBack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class FeedbackDAO implements IFeedbackDAO{

    private static FeedbackDAO instance = new FeedbackDAO();  //SINGLETON PATTERN
    private FeedBack feedBack;
    private static ResultSet rs;

    private FeedbackDAO() {
        feedBack = null;
        rs = null;
    }

    public static FeedbackDAO getInstance() {
        return instance;
    }

    public ArrayList<FeedBack> findAll() {
       
       String sql = "SELECT * FROM Commento;";

       DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
       IDbOperation dbOp = new ReadOperation(sql);
       rs = dbOperationExecutor.executeOperation(dbOp);

       ArrayList<FeedBack> feedBacks = new ArrayList<>();
       try {
           while (rs.next()) {
               feedBack = new FeedBack();
               feedBack.setId(rs.getInt("idCommento"));
               feedBack.setIdCliente(rs.getInt("ArticoloAcquistato_Cliente_Utente_idUtente"));
               feedBack.setCommento(rs.getString("Testo"));
               feedBack.setPunteggio(rs.getInt("gradimento"));
               feedBack.setDate(rs.getDate("data"));
               feedBack.setIdAcquisto(rs.getInt("ArticoloAcquistato_Acquisto_idAcquisto"));
               feedBack.setIdArticolo(rs.getInt("ArticoloAcquistato_Articolo_idArticolo"));
               feedBacks.add(feedBack);
           }
           return feedBacks;
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

    public ArrayList<FeedBack> findAllArticolo(int idArticolo) {
     
        String sql = "SELECT * FROM Commento WHERE ArticoloAcquistato_Articolo_idArticolo = '" + idArticolo + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<FeedBack> feedBacks = new ArrayList<>();
        try {
            while (rs.next()) {
                feedBack = new FeedBack();
                feedBack.setId(rs.getInt("idCommento"));
                feedBack.setIdCliente(rs.getInt("ArticoloAcquistato_Cliente_Utente_idUtente"));
                feedBack.setCommento(rs.getString("Testo"));
                feedBack.setPunteggio(rs.getInt("gradimento"));
                feedBack.setDate(rs.getDate("data"));
                feedBack.setIdAcquisto(rs.getInt("ArticoloAcquistato_Acquisto_idAcquisto"));
                feedBack.setIdArticolo(rs.getInt("ArticoloAcquistato_Articolo_idArticolo"));
                feedBacks.add(feedBack);
            }
            return feedBacks;
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

   
    public FeedBack getById(int idCommento) {

        String sql = "SELECT * FROM Commento WHERE idCommento = '" + idCommento + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                feedBack = new FeedBack();
                feedBack.setId(rs.getInt("idCommento"));
                feedBack.setIdCliente(rs.getInt("ArticoloAcquistato_Cliente_Utente_idUtente"));
                feedBack.setCommento(rs.getString("Testo"));
                feedBack.setPunteggio(rs.getInt("gradimento"));
                feedBack.setDate(rs.getDate("data"));
                feedBack.setIdAcquisto(rs.getInt("ArticoloAcquistato_Acquisto_idAcquisto"));
                feedBack.setIdArticolo(rs.getInt("ArticoloAcquistato_Articolo_idArticolo"));
            }
            return feedBack;
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

    public int add(FeedBack f) {
     
        String sql = "INSERT INTO Commento (Testo, gradimento, data, ArticoloAcquistato_Acquisto_idAcquisto, ArticoloAcquistato_Articolo_idArticolo, ArticoloAcquistato_Cliente_Utente_idUtente) VALUES ('" + f.getCommento() + "','" + f.getPunteggio() + "','" + f.getDate() + "','" + f.getIdAcquisto() + "','" + f.getIdArticolo() + "','" + f.getIdCliente() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        return 0;
    }

    public boolean CommentoExists(int idArticolo, int idCliente) {
        boolean commentoExits = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Commento WHERE ArticoloAcquistato_Articolo_idArticolo = '" + idArticolo + "' AND ArticoloAcquistato_Cliente_Utente_idUtente = '" + idCliente + "'";
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) commentoExits = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } 
        return commentoExits;
    }

    public FeedBack getByArticoloCliente(int idArticolo, int idCliente) {

        String sql = "SELECT * FROM Commento WHERE ArticoloAcquistato_Articolo_idArticolo = '" + idArticolo + "' AND ArticoloAcquistato_Cliente_Utente_idUtente = '" + idCliente + "'";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                feedBack = new FeedBack();
                feedBack.setId(rs.getInt("idCommento"));
                feedBack.setIdCliente(rs.getInt("ArticoloAcquistato_Cliente_Utente_idUtente"));
                feedBack.setCommento(rs.getString("Testo"));
                feedBack.setPunteggio(rs.getInt("gradimento"));
                feedBack.setDate(rs.getDate("data"));
                feedBack.setIdAcquisto(rs.getInt("ArticoloAcquistato_Acquisto_idAcquisto"));
                feedBack.setIdArticolo(rs.getInt("ArticoloAcquistato_Articolo_idArticolo"));
            }
            return feedBack;
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

    public int removeById(int idCommento) {
      
        String sql = "DELETE FROM Commento WHERE idCommento = '" + idCommento + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        return 0;
    
    }

    public int update(FeedBack feedBack) {
        String sql = "UPDATE Commento SET Testo = '" + feedBack.getCommento() + "' WHERE idCommento = '" + feedBack.getId() + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        return 0;
       
    }

    public int removeByCliente(int idCliente) {
      
        String sql = "DELETE FROM Commento WHERE ArticoloAcquistato_Cliente_Utente_idUtente = '" + idCliente + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        return 0;
     
    }

    public void removeCommentoArticolo(int idArticolo) {
        String sql = "DELETE FROM Commento WHERE ArticoloAcquistato_Articolo_idArticolo = '" + idArticolo + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }
}
