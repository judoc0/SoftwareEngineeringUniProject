package DBInterface;

import java.sql.ResultSet;
//COMMAND PATTERN
//Command
public interface IDbOperation { //Command pattern
    ResultSet execute();
}
