package DBInterface;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//COMMAND PATTERN
//invoker
public class DbOperationExecutor {

    private final List<IDbOperation> dbOperations = new ArrayList<>();

    public ResultSet executeOperation(IDbOperation dbOperation) {
        dbOperations.add(dbOperation);
        return dbOperation.execute();
    }
}
