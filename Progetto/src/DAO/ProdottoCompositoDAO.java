package DAO;

import DBInterface.*;
import Model.Prodotto;
import Model.ProdottoComposito;

import java.sql.ResultSet;
import java.sql.SQLException;
//COMMAND PATTERN
//Client

public class ProdottoCompositoDAO implements IProdottoCompositoDAO{
    private static ProdottoCompositoDAO instance = new ProdottoCompositoDAO();  //SINGLETON PATTERN
    private ProdottoComposito prodottoComposito;
    private static ResultSet rs;

    private ProdottoCompositoDAO() {
        prodottoComposito = null;
        rs = null;
    }

    public static ProdottoCompositoDAO getInstance() {
        return instance;
    }


    public ProdottoComposito getById(int id) {

        String sql = "SELECT * FROM prodotto_composito WHERE idProdottoComposito = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        prodottoComposito = new ProdottoComposito(); //Client COMPOSITE PATTERN
        try {
            while (rs.next()) {
                prodottoComposito.setId(rs.getInt("idProdottoComposito"));
                Prodotto prodotto = new Prodotto();
                prodotto.setId(rs.getInt("idProdottoComponente"));
                prodotto.setQuantita(rs.getInt("quantita"));
                prodottoComposito.add(prodotto);
            }
            return prodottoComposito;
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


    public void add(ProdottoComposito pro, Prodotto p) {

        String sql = "INSERT INTO Prodotto_Composito (idProdottoComposito, idProdottoComponente, quantita) VALUES ('" + pro.getId() + "', '" + p.getId() + "','" + p.getQuantita() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }

    public void remove(int idProdotto) {

        String sql = "DELETE FROM Prodotto_Composito WHERE idProdottoComposito = '" + idProdotto + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }

    public boolean compositoExists(int id) {
        boolean userExists = false;
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Prodotto_Composito WHERE idProdottoComposito = '" + id + "';"; //risolto il problema dell'asterisco descritto dopo con "count(*)"
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") > 0) userExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userExists;
    }

    public void deletefromComposito(ProdottoComposito p, Prodotto prodotto) {

        String sql = "DELETE FROM Prodotto_Composito WHERE idProdottoComposito = '" + p.getId() + "' AND idProdottoComponente = '" + prodotto.getId() + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

    }

    public void updateQuantity(ProdottoComposito p, Prodotto prodotto) {

        String sql = "UPDATE Prodotto_Composito SET Quantita = '" + prodotto.getQuantita() + "' WHERE idProdottoComposito = '" + p.getId() + "' AND idProdottoComponente = '" + prodotto.getId() + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }

    @Override
    public boolean belongComposito(int id) {

        boolean belongComposito = false;
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Prodotto_Composito WHERE idProdottoComponente = '" + id + "';"; //risolto il problema dell'asterisco descritto dopo con "count(*)"
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") > 0) belongComposito = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return belongComposito;
    }
}
