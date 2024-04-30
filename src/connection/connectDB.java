package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    static final String MYSQL_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/quan_ly_nhan_su";
    static final String MYSQL_USER = "root";
    static final String MYSQL_PASS = "";
    
    static final String SQL_SERVER_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String SQL_SERVER_DB_URL = "jdbc:sqlserver://LAPTOP-0M51BIGT\\SQLEXPRESS:1433;databaseName=student_management";
    static final String SQL_SERVER_USER = "sa";
    static final String SQL_SERVER_PASS = "anhkk123";
    
    public static Connection getConnection(ConnectionType connectionType) throws SQLException {
        try {
            switch (connectionType) {
                case MYSQL:
                    Class.forName(MYSQL_DRIVER_CLASS);
                    return DriverManager.getConnection(MYSQL_DB_URL, MYSQL_USER, MYSQL_PASS);
                case SQL_SERVER:
                    Class.forName(SQL_SERVER_DRIVER_CLASS);
                    return DriverManager.getConnection(SQL_SERVER_DB_URL, SQL_SERVER_USER, SQL_SERVER_PASS);
                default:
                    throw new IllegalArgumentException("Invalid connection type");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public enum ConnectionType {
        MYSQL,
        SQL_SERVER
    }
}