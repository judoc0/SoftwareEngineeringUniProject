package DBInterface;

import java.io.File;
import java.sql.ResultSet;

public interface IDbConnection {
    public ResultSet executeQuery(String sqlStatement);
    int executeUpdate(String sqlStatement);
    public void close();

    int addFoto(File photo, String sql);
}
