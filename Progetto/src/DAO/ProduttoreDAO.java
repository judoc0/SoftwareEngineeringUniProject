package DAO;

import DBInterface.*;
import Model.IProduttore;
import Model.Produttore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class ProduttoreDAO implements IProduttoreDAO{

    private static ProduttoreDAO instance = new ProduttoreDAO();  //SINGLETON PATTERN
    private Produttore produttore;

    private static ResultSet rs;

    private ProduttoreDAO() {
        produttore = null;
        rs = null;
    }

    public static ProduttoreDAO getInstance() {
        return instance;
    }


    public ArrayList<Produttore> findAll() {

        String sql = "SELECT * FROM Produttore;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        ArrayList<Produttore> produttori = new ArrayList<>();
        try {
            while (rs.next()) {
                produttore = new Produttore();
                produttore.setIdProduttore(rs.getInt("idProduttore"));
                produttore.setNome(rs.getString("nome"));
                produttore.setSito(rs.getString("sito_web"));
                produttore.setCitta(rs.getString("citta"));
                produttore.setNazione(rs.getString("nazione"));
                produttori.add(produttore);
            }
            return produttori;
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

    public Produttore getById(int id) {

        String sql = "SELECT * FROM Produttore WHERE idProduttore = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                produttore = new Produttore();
                produttore.setIdProduttore(rs.getInt("idProduttore"));
                produttore.setNome(rs.getString("nome"));
                produttore.setSito(rs.getString("sito_web"));
                produttore.setCitta(rs.getString("citta"));
                produttore.setNazione(rs.getString("nazione"));
            }
            return produttore;
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
    public void remove(int idProduttore) {

        String sql = "DELETE FROM Produttore WHERE idProduttore = '" + idProduttore + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    public Produttore getByName(String nome) {

        String sql = "SELECT * FROM Produttore WHERE nome = '" + nome + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                produttore = new Produttore();
                produttore.setIdProduttore(rs.getInt("idProduttore"));
                produttore.setNome(rs.getString("nome"));
                produttore.setSito(rs.getString("sito_web"));
                produttore.setCitta(rs.getString("citta"));
                produttore.setNazione(rs.getString("nazione"));
            }
            return produttore;
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

    public void add(Produttore p) {

        String sql = "INSERT INTO Produttore (nome, sito_web, citta, nazione) VALUES ('"+ p.getNome() + "','" + p.getSito() + "','" + p.getCitta() + "','" + p.getNazione() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    @Override
    public void update(Produttore produttore) {

        String sql = "UPDATE Produttore SET nome = '" + produttore.getNome() + "', sito_web = '" + produttore.getSito() + "', citta = '" + produttore.getCitta() + "', nazione = '" + produttore.getNazione() + "' WHERE idProduttore = '"+ produttore.getIdProduttore() + "'";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    @Override
    public boolean exists(Produttore p) {

        boolean produttoreExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Produttore as C WHERE nome = '" + p.getNome() + "';";
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) produttoreExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return produttoreExists;
    }
}
