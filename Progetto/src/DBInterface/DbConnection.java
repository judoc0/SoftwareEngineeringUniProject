package DBInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
//COMMAND PATTERN
//Receiver
public class DbConnection implements IDbConnection{
    private static DbUser dbUser = DbUser.getInstance();
    private static DbConnection instance = new DbConnection();
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static int rowCount;

    private DbConnection() {
        conn = null;
        stmt = null;
        rs = null;
        rowCount = 0;
        try {
            Class cls = Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Ho caricato la classe di nome: " + cls.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("Non trovo il driver MySQL JDBC: " + e.getMessage());;
        }
    }

    public static DbConnection getInstance() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbUser.getSchemaName() + "?serverTimeZone=UTC", dbUser.getUserName(), dbUser.getPwd());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        return instance;
    }

    @Override
    public ResultSet executeQuery(String sqlStatement) {
        try {
            DbConnection.getInstance();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStatement);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("vendor Error: " + e.getErrorCode());
        }
        return rs;
    }

    @Override
    public int executeUpdate(String sqlStatement) {
        try {
            DbConnection.getInstance();
            stmt = conn.createStatement();
            rowCount = stmt.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("vendor Error: " + e.getErrorCode());
        }
        return rowCount;
    }

    @Override
    public void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Vendor Error: " + e.getErrorCode());
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Vendor Error: " + e.getErrorCode());
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Vendor Error: " + e.getErrorCode());
            }
            conn = null;
        }
    }

    @Override
    public int addFoto(File photo, String sql) {
        try {
            DbConnection.getInstance();
            FileInputStream inputStream = new FileInputStream(photo);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBlob(1, inputStream);
            rowCount = statement.executeUpdate();
            statement.close();   // Chiudo lo Statement
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        } catch (FileNotFoundException e) {
            System.out.println("Foto non trovata");
            return -1;
        }
        return rowCount;

    }
}

