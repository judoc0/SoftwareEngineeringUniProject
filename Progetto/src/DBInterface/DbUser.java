package DBInterface;

public class DbUser {
    private static DbUser instance = new DbUser();
    private String schemaName;
    private String userName;
    private String pwd;

    private DbUser() {
        userName = "root";
        pwd = "Elettrocardio0";
        schemaName = "myshop";
    }

    public static DbUser getInstance() {
        return instance;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPwd() {
        return pwd;
    }
}
