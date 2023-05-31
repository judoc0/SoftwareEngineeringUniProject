package DAO;

import DBInterface.*;
import Model.Magazzino;
import Model.PuntoVendita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class PuntoVenditaDAO implements IPuntoVenditaDAO{

    private static PuntoVenditaDAO instance = new PuntoVenditaDAO();  //SINGLETON PATTERN
    private PuntoVendita puntoVendita;

    private static ResultSet rs;

    private PuntoVenditaDAO() {
        puntoVendita = null;
        rs = null;
    }

    public static PuntoVenditaDAO getInstance() {
        return instance;
    }

    
    public ArrayList<PuntoVendita> findAll() {

        String sql = "SELECT * FROM PuntoVendita;";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<PuntoVendita> magazzinos = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setCitta(rs.getString("citta"));
                magazzinos.add(puntoVendita);
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


    public PuntoVendita getById(int id) {

        String sql = "SELECT * FROM PuntoVendita WHERE idPuntoVendita = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setCitta(rs.getString("citta"));
            }
            return puntoVendita;
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

    public PuntoVendita getByCity(String city) {

        String sql = "SELECT * FROM PuntoVendita WHERE citta = '" + city + "';";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setCitta(rs.getString("citta"));
            }
            return puntoVendita;
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

    public void add(PuntoVendita puntoVendita) {
        String sql = "INSERT INTO PuntoVendita (citta)  VALUES ('"+ puntoVendita.getCitta() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }

    @Override
    public void remove(PuntoVendita puntoVendita) {

        String sql = "DELETE FROM PuntoVendita WHERE idPuntoVendita = '" + puntoVendita.getIdPuntoVendita() + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    @Override
    public void update(PuntoVendita puntoVendita) {

        String sql = "UPDATE PuntoVendita SET citta = '" + puntoVendita.getCitta() + "' WHERE idPuntoVendita = '"+ puntoVendita.getIdPuntoVendita() + "'";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    public boolean puntoVenditaExists(String citta) {

        boolean puntoVenditaExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM PuntoVendita WHERE citta = '" + citta + "';"; //risolto il problema dell'asterisco descritto dopo con "count(*)"
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) puntoVenditaExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return puntoVenditaExists;
    }
}
