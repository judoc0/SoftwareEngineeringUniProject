package DAO;

import DBInterface.*;
import Model.ArticoliLista;
import Model.Articolo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class Lista_has_ArticoloDAO implements ILista_has_ArticoloDAO{
    private static Lista_has_ArticoloDAO instance = new Lista_has_ArticoloDAO();  //SINGLETON PATTERN
    private Articolo articolo;
    private static ResultSet rs;

    private Lista_has_ArticoloDAO() {
        articolo = null;
        rs = null;
    }

    public static Lista_has_ArticoloDAO getInstance() {
        return instance;
    }

    public ArrayList<ArticoliLista> getArticoliLista(int idLista) {

        String sql = "SELECT * FROM Lista_has_articolo WHERE Lista_idLista = '" + idLista + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        ArrayList<ArticoliLista> articoliListas = new ArrayList<>();
        try {
            while (rs.next()) {
                ArticoliLista articoliLista = new ArticoliLista();
                articoliLista.setIdLista(rs.getInt("Lista_idLista"));
                articoliLista.setIdArticolo(rs.getInt("Articolo_idArticolo"));
                articoliLista.setQuantita(rs.getInt("quantita"));
                articoliListas.add(articoliLista);
            }
            return articoliListas;
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

    public void add(ArticoliLista articoliLista) {
        String sql = "INSERT INTO Lista_has_Articolo (Lista_idLista, Articolo_idArticolo, quantita) VALUES ('" + articoliLista.getIdLista() + "','" + articoliLista.getIdArticolo() + "','" + articoliLista.getQuantita() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }

    public void removeById(ArticoliLista articoliLista) {

        String sql = "DELETE FROM Lista_has_Articolo WHERE Lista_idLista = '" + articoliLista.getIdLista() + "' AND Articolo_idArticolo = '" + articoliLista.getIdArticolo() + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }

    public void removeAll(int idLista) {

        String sql = "DELETE FROM Lista_has_Articolo WHERE Lista_idLista = '" + idLista + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }

    public boolean isEmpty(int idLista) {
        boolean lista_has_articolo = true;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM lista_has_articolo WHERE Lista_idLista = '" + idLista + "';"; //risolto il problema dell'asterisco descritto dopo con "count(*)"
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") > 0) lista_has_articolo = false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista_has_articolo;
    }

    public boolean has_articolo(int idLista, int idArticolo) {

        boolean lista_has_articolo = false;

        String sql = "SELECT count(*) AS C FROM lista_has_articolo WHERE Lista_idLista = '" + idLista + "' AND Articolo_idArticolo = '" + idArticolo + "';"; //risolto il problema dell'asterisco descritto dopo con "count(*)"
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) lista_has_articolo = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista_has_articolo;
    }

    public void updateQuantity(ArticoliLista articoliLista) {

        String sql = "UPDATE lista_has_articolo SET quantita = '" + articoliLista.getQuantita() + "' WHERE Lista_idLista = '" + articoliLista.getIdLista() + "' AND Articolo_idArticolo = '" + articoliLista.getIdArticolo() + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

    }

    public ArticoliLista getOneArticoliLista(int idLista, int idArticolo) {

        String sql = "SELECT * FROM lista_has_articolo WHERE Lista_idLista = '" + idLista + "' AND Articolo_idArticolo = '" + idArticolo + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
        try {
            rs.next();
            if (rs.getRow()==1) {
                ArticoliLista articoliLista = new ArticoliLista();
                articoliLista.setIdLista(rs.getInt("Lista_idLista"));
                articoliLista.setIdArticolo(rs.getInt("Articolo_idArticolo"));
                articoliLista.setQuantita(rs.getInt("quantita"));
            return articoliLista;
            }
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

    public void removefromAllListe(int idArticolo) {
        String sql = "DELETE FROM Lista_has_Articolo WHERE Articolo_idArticolo = '" + idArticolo + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
    }
}
