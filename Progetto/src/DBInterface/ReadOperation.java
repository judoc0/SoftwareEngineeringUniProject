package DBInterface;

import java.sql.ResultSet;
//COMMAND PATTERN
//Concrete Command
public class ReadOperation implements IDbOperation{

    private static DbConnection conn = DbConnection.getInstance();
    private String sql;

    public ReadOperation(String sql) {
        this.sql = sql;
    }

    @Override
    public ResultSet execute() {
        //esegui query
        return conn.executeQuery(sql);
    }

}
