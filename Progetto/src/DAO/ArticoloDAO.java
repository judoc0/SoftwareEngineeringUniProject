package DAO;

import DBInterface.*;
import Model.Articolo;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class ArticoloDAO implements IArticoloDAO{
    private static ArticoloDAO instance = new ArticoloDAO();  //SINGLETON PATTERN
    private Articolo articolo;
    private static ResultSet rs;

    private ArticoloDAO() {
        articolo = null;
        rs = null;
    }

    public static ArticoloDAO getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Articolo> findAll() {

        String sql = "SELECT * FROM Articolo;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        ArrayList<Articolo> articoli = new ArrayList<>();
        try {
            while (rs.next()) {
                articolo = new Articolo();
                articolo.setId(rs.getInt("idArticolo"));
                articolo.setPrezzo(rs.getFloat("prezzo"));
                articolo.setNome(rs.getString("nome"));
                articolo.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setFoto(rs.getBytes("foto"));
                articoli.add(articolo);
            }
            return articoli;
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

    @Override
    public Articolo getById(int id) {

        String sql = "SELECT * FROM Articolo WHERE idArticolo = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);


        try {
            rs.next();
            if (rs.getRow()==1) {
                articolo = new Articolo();
                articolo.setId(rs.getInt("idArticolo"));
                articolo.setPrezzo(rs.getFloat("prezzo"));
                articolo.setNome(rs.getString("nome"));
                articolo.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setFoto(rs.getBytes("foto"));
            }
            return articolo;
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
    public boolean prodottoExists(Articolo a) {

        boolean prodottoExists = false;
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Articolo as A INNER JOIN Prodotto as P ON A.idArticolo = P.Articolo_idArticolo WHERE A.idArticolo = '" + a.getId() + "';"; //risolto il problema dell'asterisco descritto dopo con "count(*)"
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) prodottoExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return prodottoExists;
    }

    @Override
    public boolean servizioExists(Articolo a) {

        boolean servizioExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Articolo as A INNER JOIN SERVIZIO as S ON A.idArticolo = S.Articolo_idArticolo WHERE A.idArticolo = '" + a.getId() + "';"; //risolto il problema dell'asterisco descritto dopo con "count(*)"
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) servizioExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return servizioExists;
    }

    @Override
    public void remove(int idArticolo) {

        String sql = "DELETE FROM Articolo WHERE idArticolo = '" + idArticolo + "';";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp2 = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp2);
    }

    @Override
    public void update(Articolo a) {

        String sql ="UPDATE Articolo SET nome = '" + a.getNome() + "', prezzo = '" + a.getPrezzo() + "', Categoria_idCategoria = '" + a.getIdCategoria() + "', descrizione = '" + a.getDescrizione() + "' WHERE idArticolo = '" + a.getId() + "';";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp2 = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp2);
    }

    @Override
    public boolean articoloExists(Articolo articolo) {

        boolean articoloExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Articolo WHERE nome = '" + articolo.getNome() + "';"; //risolto il problema dell'asterisco descritto dopo con "count(*)"
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) articoloExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return articoloExists;
    }

    @Override
    public void add(Articolo articolo) {

        String sql = "INSERT INTO Articolo (prezzo, nome, Categoria_idCategoria, descrizione) VALUES ('"+ articolo.getPrezzo() + "','" + articolo.getNome() + "','" + articolo.getIdCategoria() + "', '" + articolo.getDescrizione() + "');";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    @Override
    public int inserisciFoto(Articolo articolo, File foto) {

        String sql = "UPDATE Articolo set foto = ? WHERE idArticolo = '" + articolo.getId() + "';";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp2 = new SavePhotoOperation(sql, foto);
        rs = dbOperationExecutor.executeOperation(dbOp2);

        return 0;
    }

    @Override
    public Articolo getByName(String name) {

        String sql = "SELECT * FROM Articolo WHERE nome = '" + name + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow() == 1) {
                articolo = new Articolo();
                articolo.setId(rs.getInt("idArticolo"));
                articolo.setPrezzo(rs.getFloat("prezzo"));
                articolo.setNome(rs.getString("nome"));
                articolo.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setFoto(rs.getBytes("foto"));
            }
            return articolo;
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
