package DBInterface;

import java.io.File;
import java.sql.ResultSet;
//COMMAND PATTERN
//Concrete Command
public class SavePhotoOperation implements IDbOperation{

    private static DbConnection conn = DbConnection.getInstance();

    String sql;
    File photo;

    public SavePhotoOperation(String sql, File photo) {
        this.sql = sql;
        this.photo = photo;
    }

    @Override
    public ResultSet execute() {
        // continua a cercare i design pattern modificando le classi
        int i = conn.addFoto(photo, sql);
        return null;
    }
}
