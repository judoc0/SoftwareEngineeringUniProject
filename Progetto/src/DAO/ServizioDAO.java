package DAO;

import DBInterface.*;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class ServizioDAO implements IServizioDAO{
    private static ServizioDAO instance = new ServizioDAO();  //SINGLETON PATTERN
    private Servizio servizio;
    private static ResultSet rs;

    private ServizioDAO() {
        servizio = null;
        rs = null;
    }

    public static ServizioDAO getInstance() {
        return instance;
    }


    public ArrayList<Servizio> findAll() {

        String sql = "SELECT * FROM articolo as a INNER JOIN servizio as s ON a.idArticolo = s.Articolo_idArticolo ORDER BY a.idArticolo";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while (rs.next()) {
                servizio = new Servizio();
                servizio.setId(rs.getInt("idArticolo"));
                servizio.setPrezzo(rs.getFloat("prezzo"));
                servizio.setNome(rs.getString("nome"));
                servizio.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setFoto(rs.getBytes("foto"));
                servizio.setIdFornitore(rs.getInt("Fornitore_idFornitore"));
                servizi.add(servizio);
            }
            return servizi;
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


    public Servizio getById(int id) {

        String sql = "SELECT * FROM articolo as a INNER JOIN servizio as s ON a.idArticolo = s.Articolo_idArticolo WHERE idArticolo = '" + id + "'";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);


        try {
            rs.next();
            if (rs.getRow()==1) {
                servizio = new Servizio();
                servizio.setId(rs.getInt("idArticolo"));
                servizio.setPrezzo(rs.getFloat("prezzo"));
                servizio.setNome(rs.getString("nome"));
                servizio.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setFoto(rs.getBytes("foto"));
                servizio.setIdFornitore(rs.getInt("Fornitore_idFornitore"));
            }
            return servizio;
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
    public Servizio getByName(String name) {
        String sql = "SELECT * FROM articolo as a INNER JOIN servizio as s ON a.idArticolo = s.Articolo_idArticolo WHERE nome = '" + name + "'";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);


        try {
            rs.next();
            if (rs.getRow()==1) {
                servizio = new Servizio();
                servizio.setId(rs.getInt("idArticolo"));
                servizio.setPrezzo(rs.getFloat("prezzo"));
                servizio.setNome(rs.getString("nome"));
                servizio.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setFoto(rs.getBytes("foto"));
                servizio.setIdFornitore(rs.getInt("Fornitore_idFornitore"));
            }
            return servizio;
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

    public void remove(int idArticolo) {

        String sql = "DELETE FROM Servizio WHERE Articolo_idArticolo = '" + idArticolo + "';";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    public void addServizio(Servizio servizio) {

        String sql = "INSERT INTO Servizio (Articolo_idArticolo, Fornitore_idFornitore)  VALUES ('"+ servizio.getId() + "','" + servizio.getIdFornitore() + "');";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    @Override
    public void update(Servizio servizio) {

        String sql = "UPDATE Servizio SET Fornitore_idFornitore = '" + servizio.getIdFornitore() + "' WHERE Articolo_idArticolo = '"+ servizio.getId() + "'";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }
}
