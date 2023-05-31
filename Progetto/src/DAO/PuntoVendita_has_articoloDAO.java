package DAO;

import DBInterface.*;
import Model.PuntoVendita_has_articolo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//COMMAND PATTERN
//Client
public class PuntoVendita_has_articoloDAO implements IPuntoVendita_has_articoloDAO{

    private static PuntoVendita_has_articoloDAO instance = new PuntoVendita_has_articoloDAO();  //SINGLETON PATTERN
    private PuntoVendita_has_articolo puntoVendita_has_articolo;
    private static ResultSet rs;

    private PuntoVendita_has_articoloDAO() {
        puntoVendita_has_articolo = null;
        rs = null;
    }

    public static PuntoVendita_has_articoloDAO getInstance() {
        return instance;
    }


    public ArrayList<PuntoVendita_has_articolo> findAll() {

        String sql = "SELECT * FROM puntovendita_has_articolo;";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<PuntoVendita_has_articolo> magazzinos = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita_has_articolo = new PuntoVendita_has_articolo();
                puntoVendita_has_articolo.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                puntoVendita_has_articolo.setIdArticolo(rs.getInt("Articolo_idArticolo"));
                magazzinos.add(puntoVendita_has_articolo);
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


    public List<PuntoVendita_has_articolo> getById(int id) {

        String sql = "SELECT * FROM puntovendita_has_articolo WHERE PuntoVendita_idPuntoVendita = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<PuntoVendita_has_articolo> magazzinos = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita_has_articolo = new PuntoVendita_has_articolo();
                puntoVendita_has_articolo.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                puntoVendita_has_articolo.setIdArticolo(rs.getInt("Articolo_idArticolo"));
                magazzinos.add(puntoVendita_has_articolo);
            }
            return magazzinos;
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

    public List<PuntoVendita_has_articolo> findbyArticolo(int id) {

        String sql = "SELECT * FROM puntovendita_has_articolo WHERE Articolo_idArticolo = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<PuntoVendita_has_articolo> magazzinos = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita_has_articolo = new PuntoVendita_has_articolo();
                puntoVendita_has_articolo.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                puntoVendita_has_articolo.setIdArticolo(rs.getInt("Articolo_idArticolo"));
                magazzinos.add(puntoVendita_has_articolo);
            }
            return magazzinos;
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

    public void add(PuntoVendita_has_articolo p) {

        String sql = "INSERT INTO puntovendita_has_articolo (PuntoVendita_idPuntoVendita, Articolo_idArticolo) VALUES ('"+ p.getIdPuntoVendita() + "','" + p.getIdArticolo() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    public void remove(int idPuntoVendita, int idArticolo) {

        String sql = "DELETE FROM puntovendita_has_articolo WHERE PuntoVendita_idPuntoVendita = '" + idPuntoVendita + "' AND Articolo_idArticolo = '" + idArticolo + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    public void removeAll(int idArticolo) {

        String sql =  "DELETE FROM puntovendita_has_articolo WHERE Articolo_idArticolo = '" + idArticolo + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

}
