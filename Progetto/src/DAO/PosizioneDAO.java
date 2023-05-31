package DAO;

import DBInterface.*;
import Model.Articolo;
import Model.Posizione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class PosizioneDAO implements IPosizioneDAO{
    private static PosizioneDAO instance = new PosizioneDAO();  //SINGLETON PATTERN
    private Posizione posizione;
    private static ResultSet rs;

    private PosizioneDAO() {
        posizione = null;
        rs = null;
    }

    public static PosizioneDAO getInstance() {
        return instance;
    }


    public ArrayList<Posizione> findAll() {

        String sql = "SELECT * FROM Posizione;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<Posizione> posiziones = new ArrayList<>();
        try {
            while (rs.next()) {
                posizione = new Posizione();
                posizione.setIdPosizione(rs.getInt("idPosizione"));
                posizione.setCorsia(rs.getString("corsia"));
                posizione.setScaffale(rs.getString("scaffale"));
                posiziones.add(posizione);
            }
            return posiziones;
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


    public Posizione getById(int id) {

        String sql = "SELECT * FROM Posizione WHERE idPosizione = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);


        try {
            rs.next();
            if (rs.getRow()==1) {
                posizione = new Posizione();
                posizione.setIdPosizione(rs.getInt("idPosizione"));
                posizione.setCorsia(rs.getString("corsia"));
                posizione.setScaffale(rs.getString("scaffale"));
            }
            return posizione;
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

    public boolean posizioneExists(Posizione posizione) {
        boolean posizioneExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Posizione WHERE corsia = '" + posizione.getCorsia() + "' AND scaffale = '" + posizione.getScaffale() +"';"; //risolto il problema dell'asterisco descritto dopo con "count(*)"
        ReadOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) posizioneExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return posizioneExists;
    }

    public void add(Posizione posizione) {

        String sql = "INSERT INTO Posizione (corsia, scaffale) VALUES ('" + posizione.getCorsia() + "','" + posizione.getScaffale() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    public Posizione getByPosizione(Posizione posizione) {

        String sql = "SELECT * FROM Posizione WHERE corsia = '" + posizione.getCorsia() + "' AND scaffale = '" + posizione.getScaffale() +"';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);


        try {
            rs.next();
            if (rs.getRow()==1) {
                posizione = new Posizione();
                posizione.setIdPosizione(rs.getInt("idPosizione"));
                posizione.setCorsia(rs.getString("corsia"));
                posizione.setScaffale(rs.getString("scaffale"));
            }
            return posizione;
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

    public void remove(int posizione) {

        String sql = "DELETE FROM Posizione WHERE idPosizione = '" + posizione + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    @Override
    public void update(Posizione po) {

        String sql =  "UPDATE Posizione SET corsia = '" + po.getCorsia() + "', Scaffale = '" + po.getScaffale() + "' WHERE idPosizione = '" + po.getIdPosizione() + "'";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

}
