package DAO;

import DBInterface.*;
import Model.Magazzino;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class MagazzinoDAO implements IMagazzinoDAO{

    private static MagazzinoDAO instance = new MagazzinoDAO();  //SINGLETON PATTERN
    private Magazzino magazzino;
    private static ResultSet rs;

    private MagazzinoDAO() {
        magazzino = null;
        rs = null;
    }

    public static MagazzinoDAO getInstance() {
        return instance;
    }

    
    public ArrayList<Magazzino> findAll() {

        String sql = "SELECT * FROM Magazzino;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        ArrayList<Magazzino> magazzinos = new ArrayList<>();
        try {
            while (rs.next()) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idMagazzino"));
                magazzino.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                magazzinos.add(magazzino);
            }
            return magazzinos;
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


    public Magazzino getById(int id) {

        String sql = "SELECT * FROM Magazzino WHERE idMagazzino = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idMagazzino"));
                magazzino.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
            }
            return magazzino;
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

    public void add(Magazzino magazzino) {

        String sql = "INSERT INTO Magazzino (PuntoVendita_idPuntoVendita)  VALUES ('"+ magazzino.getIdPuntoVendita() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    @Override
    public void remove(int idMagazzino) {

        String sql = "DELETE FROM Magazzino WHERE idMagazzino = '" + idMagazzino + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    public Magazzino getByIdPuntoVendita(int idPuntoVendita) {

        String sql = "SELECT * FROM Magazzino WHERE PuntoVendita_idPuntoVendita = '" + idPuntoVendita + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idMagazzino"));
                magazzino.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
            }
            return magazzino;
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
}
