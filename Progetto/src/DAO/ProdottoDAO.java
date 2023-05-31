package DAO;

import DBInterface.*;
import Model.Prodotto;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class ProdottoDAO implements IProdottoDAO{
    private static ProdottoDAO instance = new ProdottoDAO();  //SINGLETON PATTERN
    private Prodotto prodotto;

    private static ResultSet rs;

    private ProdottoDAO() {
        prodotto = null;

        rs = null;
    }

    public static ProdottoDAO getInstance() {
        return instance;
    }

   @Override
    public ArrayList<Prodotto> findAll() {

        String sql = "SELECT * FROM articolo as a INNER JOIN prodotto as p ON a.idArticolo = p.Articolo_idArticolo";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setId(rs.getInt("idArticolo"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setFoto(rs.getBytes("foto"));
                prodotto.setIdProduttore(rs.getInt("Produttore_idProduttore"));
                prodotto.setIdPosizione(rs.getInt("Posizione_idPosizione"));
                prodotti.add(prodotto);
            }
            return prodotti;
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
    public Prodotto getById(int id) {

        String sql = "SELECT * FROM articolo as a INNER JOIN prodotto as p ON a.idArticolo = p.Articolo_idArticolo WHERE idArticolo = '" + id + "'";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                prodotto = new Prodotto();
                prodotto.setId(rs.getInt("idArticolo"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setFoto(rs.getBytes("foto"));
                prodotto.setIdProduttore(rs.getInt("Produttore_idProduttore"));
                prodotto.setIdPosizione(rs.getInt("Posizione_idPosizione"));
            }
            return prodotto;
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
    public Prodotto getByName(String name) {

        String sql = "SELECT * FROM articolo as a INNER JOIN prodotto as p ON a.idArticolo = p.Articolo_idArticolo WHERE nome = '" + name + "'";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                prodotto = new Prodotto();
                prodotto.setId(rs.getInt("idArticolo"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setFoto(rs.getBytes("foto"));
                prodotto.setIdProduttore(rs.getInt("Produttore_idProduttore"));
                prodotto.setIdPosizione(rs.getInt("Posizione_idPosizione"));
            }
            return prodotto;
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
    public void removeProdotto(int idArticolo) {

        String sql = "DELETE FROM Prodotto WHERE Articolo_idArticolo = '" + idArticolo + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    @Override
    public void addProdotto(Prodotto prodotto) {

        String sql = "INSERT INTO Prodotto (Articolo_idArticolo, Produttore_idProduttore, Posizione_idPosizione)  VALUES ('"+ prodotto.getId() + "','" + prodotto.getIdProduttore() + "','" + prodotto.getIdPosizione() + "');";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    @Override
    public void updateProdotto(Prodotto prodotto) {

        String sql = "UPDATE Prodotto SET Posizione_idPosizione = '" + prodotto.getIdPosizione() + "', Produttore_idProduttore = '" + prodotto.getIdProduttore() + "' WHERE Articolo_idArticolo = '"+ prodotto.getId() + "'";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

}
