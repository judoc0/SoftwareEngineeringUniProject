package DAO;

import DBInterface.*;
import Model.Fornitore;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class FornitoreDAO implements IFornitoreDAO{

    private static FornitoreDAO instance = new FornitoreDAO();  //SINGLETON PATTERN
    private Fornitore fornitore;
    private static ResultSet rs;

    private FornitoreDAO() {
        fornitore = null;
        rs = null;
    }

    public static FornitoreDAO getInstance() {
        return instance;
    }


    public ArrayList<Fornitore> findAll() {

        String sql = "SELECT * FROM Fornitore";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<Fornitore> fornitori = new ArrayList<>();
        try {
            while (rs.next()) {
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idFornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setSito(rs.getString("sito_web"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
                fornitori.add(fornitore);
            }
            return fornitori;
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


    public Fornitore getById(int id) {

        String sql = "SELECT * FROM Fornitore WHERE idFornitore = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idFornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setSito(rs.getString("sito_web"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
            }
            return fornitore;
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

    public Fornitore getByName(String text) {

        String sql = "SELECT * FROM Fornitore WHERE nome = '" + text + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idFornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setSito(rs.getString("sito_web"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
            }
            return fornitore;
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

    @Override
    public void remove(int idFornitore) {

        String sql = "DELETE FROM Fornitore WHERE idFornitore = '" + idFornitore + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    public void add(Fornitore p) {

        String sql = "INSERT INTO Fornitore (nome, sito_web, citta, nazione) VALUES ('"+ p.getNome() + "','" + p.getSito() + "','" + p.getCitta() + "','" + p.getNazione() + "');";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);


    }

    @Override
    public void update(Fornitore fornitore) {

        String sql = "UPDATE Fornitore SET nome = '" + fornitore.getNome() + "', sito_web = '" + fornitore.getSito() + "', citta = '" + fornitore.getCitta() + "', nazione = '" + fornitore.getNazione() + "' WHERE idFornitore = '"+ fornitore.getIdFornitore() + "'";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    @Override
    public boolean exists(Fornitore p) {

        boolean fornitoreExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Fornitore as C WHERE nome = '" + p.getNome() + "';";
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) fornitoreExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return fornitoreExists;
    }
}
