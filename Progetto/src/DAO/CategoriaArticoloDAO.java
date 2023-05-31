package DAO;

import DBInterface.*;
import Model.CategoriaArticolo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class CategoriaArticoloDAO implements ICategoriaArticoloDAO {
    private static CategoriaArticoloDAO instance = new CategoriaArticoloDAO();  //SINGLETON PATTERN
    private CategoriaArticolo categoriaArticolo;
    private static ResultSet rs;

    private CategoriaArticoloDAO() {
        categoriaArticolo = null;
        rs = null;
    }

    public static CategoriaArticoloDAO getInstance() {
        return instance;
    }

    @Override
    public ArrayList<CategoriaArticolo> findCategories() {
        String sql = "SELECT * FROM Categoria;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        ArrayList<CategoriaArticolo> categoriaProdotti = new ArrayList<>();
        try {
            while (rs.next()) {
                categoriaArticolo = new CategoriaArticolo();
                categoriaArticolo.setNome(rs.getString("nome"));
                categoriaArticolo.setIdCategoria(rs.getInt("idCategoria"));
                categoriaArticolo.setIdCategoria_padre(rs.getInt("Categoria_idCategoria_padre"));
                categoriaArticolo.setTipoCategoria(CategoriaArticolo.tipoCategoria.valueOf(rs.getString("Tipo")));
                categoriaProdotti.add(categoriaArticolo);
            }
            return categoriaProdotti;
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
    public CategoriaArticolo findById(int id) {

        String sql = "SELECT * FROM Categoria WHERE idCategoria = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);


        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaArticolo = new CategoriaArticolo();
                categoriaArticolo.setIdCategoria(rs.getInt("idCategoria"));
                categoriaArticolo.setNome(rs.getString("Nome"));
                categoriaArticolo.setIdCategoria_padre(rs.getInt("Categoria_idCategoria_padre"));
                categoriaArticolo.setTipoCategoria(CategoriaArticolo.tipoCategoria.valueOf(rs.getString("Tipo")));
            }
            return categoriaArticolo;
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
    public void add(CategoriaArticolo c) {
        String sql;
        if (c.getIdCategoria_padre() == 0) sql = "INSERT INTO Categoria (nome, Tipo) VALUES ('"+ c.getNome() + "','" + c.getTipoCategoria() + "');";
        else sql = "INSERT INTO Categoria (nome, Categoria_idCategoria_padre, Tipo) VALUES ('"+ c.getNome() + "','" + c.getIdCategoria_padre() + "','" + c.getTipoCategoria() + "');";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    @Override
    public void remove(int id) {

        String sql = "DELETE FROM Categoria WHERE idCategoria = '" + id + "'";

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }

    @Override
    public CategoriaArticolo findByName(String nome) {

        String sql = "SELECT * FROM Categoria WHERE nome = '" + nome + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                categoriaArticolo = new CategoriaArticolo();
                categoriaArticolo.setIdCategoria(rs.getInt("idCategoria"));
                categoriaArticolo.setNome(rs.getString("Nome"));
                categoriaArticolo.setIdCategoria_padre(rs.getInt("Categoria_idCategoria_padre"));
                categoriaArticolo.setTipoCategoria(CategoriaArticolo.tipoCategoria.valueOf(rs.getString("Tipo")));
            }
            return categoriaArticolo;
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
