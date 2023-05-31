package DAO;

import DBInterface.*;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//COMMAND PATTERN
//Client
public class UtenteDAO implements IUtenteDAO {

    private static UtenteDAO instance = new UtenteDAO();  //SINGLETON PATTERN
    private static Utente utente;

    private static ResultSet rs;

    private UtenteDAO() {
        utente = null;
        rs = null;
    }

    public static UtenteDAO getInstance() {
        return instance;
    }

    @Override
    public Utente findById(int id) {

        String sql = "SELECT * FROM Utente WHERE Utente.idUtente = '" + id + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setUsername(rs.getString("Username"));
                utente.setPassword(rs.getString("Password"));
                utente.setName(rs.getString("Name"));
                utente.setSurname(rs.getString("Surname"));
                utente.setEmail(rs.getString("Email"));
                utente.setPhoneNumber(rs.getString("PhoneNumber"));
                utente.setAge(rs.getInt("Age"));
                utente.setResidence(rs.getString("Residence"));
                utente.setJob(rs.getString("Job"));
                return utente;
            }
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
    public Utente findByEmail(String email) {

        String sql = "SELECT * FROM Utente WHERE Utente.Email = '" + email + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);


        try {
            rs.next();
            if (rs.getRow()==1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setUsername(rs.getString("Username"));
                utente.setPassword(rs.getString("Password"));
                utente.setName(rs.getString("Name"));
                utente.setSurname(rs.getString("Surname"));
                utente.setEmail(rs.getString("Email"));
                utente.setPhoneNumber(rs.getString("PhoneNumber"));
                utente.setAge(rs.getInt("Age"));
                utente.setResidence(rs.getString("Residence"));
                utente.setJob(rs.getString("Job"));
                return utente;
            }
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
    public ArrayList<Utente> findAll() {
        String sql =  "SELECT * FROM Utente;";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        ArrayList<Utente> utenti = new ArrayList<>();
        try {
            while (rs.next()) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setUsername(rs.getString("Username"));
                utente.setPassword(rs.getString("Password"));
                utente.setName(rs.getString("Name"));
                utente.setSurname(rs.getString("Surname"));
                utente.setEmail(rs.getString("Email"));
                utente.setPhoneNumber(rs.getString("PhoneNumber"));
                utente.setAge(rs.getInt("Age"));
                utente.setResidence(rs.getString("Residence"));
                utente.setJob(rs.getString("Job"));
                utenti.add(utente);
            }
            return utenti;
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
    public int add(Utente utente) {
        String sql = "INSERT INTO Utente (username, password, name, surname, email, phoneNumber, age, residence, job) VALUES ('"+ utente.getUsername() + "','" + utente.getPassword() + "','" + utente.getName() + "', '" + utente.getSurname() + "','" + utente.getEmail() + "','" + utente.getPhoneNumber() + "','" + utente.getAge() + "','" + utente.getResidence() + "','" + utente.getJob() + "');";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);
        return 0;
    }

    @Override
    public int removeById(String email) {

        String sql = "DELETE FROM Utente WHERE email = '" + email + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        return 0;
    }

    @Override
    public int update(Utente utente) {

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "UPDATE Utente SET Name = '" + utente.getName() + "', Surname = '" + utente.getSurname() + "', username = '" + utente.getUsername() + "', Password = '" + utente.getPassword() + "', phoneNumber = '" + utente.getPhoneNumber() +"', age = '" + utente.getAge() +"', residence = '" + utente.getResidence() +"', job = '" + utente.getJob()  +"' WHERE Email = '" + utente.getEmail() + "';";
        IDbOperation op = new WriteOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        return 0;
    }

    @Override
    public boolean userExists(String username) {

        boolean userExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM utente WHERE username = '" + username + "';";
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) userExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userExists;
    }

    @Override
    public boolean credentialsOk(String username, String password) {

        boolean credentialsOk = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM Utente WHERE Utente.username = '" + username + "' and Utente.password = '"+password+"';";
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) credentialsOk = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return credentialsOk;
    }

    @Override
    public Utente getByUsername(String username) {

        String sql = "SELECT * FROM Utente WHERE Utente.username = '" + username + "';";
        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();

        IDbOperation dbOp = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(dbOp);

        try {
            rs.next();
            if (rs.getRow()==1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setUsername(rs.getString("Username"));
                utente.setPassword(rs.getString("Password"));
                utente.setName(rs.getString("Name"));
                utente.setSurname(rs.getString("Surname"));
                utente.setEmail(rs.getString("Email"));
                utente.setPhoneNumber(rs.getString("PhoneNumber"));
                utente.setAge(rs.getInt("Age"));
                utente.setResidence(rs.getString("Residence"));
                utente.setJob(rs.getString("Job"));
                return utente;
            }
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean managerExists(Utente u) {

        boolean managerExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM utente as U INNER JOIN Manager as M ON U.idUtente = M.Utente_idUtente WHERE U.username = '" + u.getUsername() + "';";
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) managerExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return managerExists;
    }

    @Override
    public boolean administratorExists(Utente u) {

        boolean administrator = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM utente as U INNER JOIN Amministratore as A ON U.idUtente = A.Utente_idUtente WHERE U.username = '" + u.getUsername() + "';";
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) administrator = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return administrator;
    }

    @Override
    public boolean clientExists(Utente u) {

        boolean clientExists = false;

        DbOperationExecutor dbOperationExecutor = new DbOperationExecutor();
        String sql = "SELECT count(*) AS C FROM utente as U INNER JOIN Cliente as M ON U.idUtente = M.Utente_idUtente WHERE U.username = '" + u.getUsername() + "';";
        IDbOperation op = new ReadOperation(sql);
        rs = dbOperationExecutor.executeOperation(op);

        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C") == 1) clientExists = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return clientExists;
    }

}
