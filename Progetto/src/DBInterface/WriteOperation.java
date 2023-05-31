package DBInterface;

import java.sql.ResultSet;
//COMMAND PATTERN
//Concrete Command
public class WriteOperation implements IDbOperation{

    private static DbConnection conn = DbConnection.getInstance();
    private String sql;

    public WriteOperation(String sql) {
        this.sql = sql;
    }

    @Override
    public ResultSet execute() {
        int i = conn.executeUpdate(sql);
        return null;
    }

}
