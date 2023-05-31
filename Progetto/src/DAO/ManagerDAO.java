package DAO;

import DBInterface.*;
import Model.Manager;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class ManagerDAO implements IManagerDAO{

    private static ManagerDAO instance = new ManagerDAO();  //SINGLETON PATTERN
    private Manager manager;
    private static ResultSet rs;

    private ManagerDAO() {
        manager = null;
        rs = null;
    }

    public static ManagerDAO getInstance() {
        return instance;
    }

    public ArrayList<Manager> findAll() {

        String sql = "SELECT * FROM utente as U INNER JOIN Manager as M ON U.idUtente = M.Utente_idUtente;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
        ArrayList<Manager> managers = new ArrayList<>();
        try {
            while (rs.next()) {
                manager = new Manager();
                manager.setIdUtente(rs.getInt("idUtente"));
                manager.setUsername(rs.getString("Username"));
                manager.setPassword(rs.getString("Password"));
                manager.setName(rs.getString("Name"));
                manager.setSurname(rs.getString("Surname"));
                manager.setEmail(rs.getString("Email"));
                manager.setPhoneNumber(rs.getString("PhoneNumber"));
                manager.setAge(rs.getInt("Age"));
                manager.setResidence(rs.getString("Residence"));
                manager.setJob(rs.getString("Job"));
                manager.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
                managers.add(manager);
            }
            return managers;
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


    public Manager getById(int id) {

        String sql = "SELECT * FROM utente as U INNER JOIN Manager as M ON U.idUtente = M.Utente_idUtente WHERE idUtente = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                manager = new Manager();
                manager.setIdUtente(rs.getInt("idUtente"));
                manager.setUsername(rs.getString("Username"));
                manager.setPassword(rs.getString("Password"));
                manager.setName(rs.getString("Name"));
                manager.setSurname(rs.getString("Surname"));
                manager.setEmail(rs.getString("Email"));
                manager.setPhoneNumber(rs.getString("PhoneNumber"));
                manager.setAge(rs.getInt("Age"));
                manager.setResidence(rs.getString("Residence"));
                manager.setJob(rs.getString("Job"));
                manager.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
            }
            return manager;
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

    public Manager getByIdPuntoVendita(int id) {

        String sql = "SELECT * FROM utente as U INNER JOIN Manager as M ON U.idUtente = M.Utente_idUtente WHERE M.PuntoVendita_idPuntoVendita = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        manager = null;
        try {

            rs.next();
            if (rs.getRow()==1) {
                manager = new Manager();
                manager.setIdUtente(rs.getInt("idUtente"));
                manager.setUsername(rs.getString("Username"));
                manager.setPassword(rs.getString("Password"));
                manager.setName(rs.getString("Name"));
                manager.setSurname(rs.getString("Surname"));
                manager.setEmail(rs.getString("Email"));
                manager.setPhoneNumber(rs.getString("PhoneNumber"));
                manager.setAge(rs.getInt("Age"));
                manager.setResidence(rs.getString("Residence"));
                manager.setJob(rs.getString("Job"));
                manager.setIdPuntoVendita(rs.getInt("PuntoVendita_idPuntoVendita"));
            }

            return manager;
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

    public void setManager(Manager manager) {

        String sql = "INSERT INTO Manager SET PuntoVendita_idPuntoVendita = '" + manager.getIdPuntoVendita() + "',Utente_idUtente = '" + manager.getIdUtente() + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

    }

    @Override
    public void remove(int idUtente) {

        String sql = "DELETE FROM Manager WHERE Utente_idUtente = '" + idUtente + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);
    }
}
