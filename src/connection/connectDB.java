package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
    public static Connection getConnection(ConnectionType type) {
        switch (type) {
            case MYSQL:
                return getMySQLConnection();
            case SQL_SERVER:
                return getSQLServerConnection();
            default:
                throw new IllegalArgumentException("Invalid connection type: " + type);
        }
    }

    private static Connection getMySQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/database";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Connection getSQLServerConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://LAPTOP-0M51BIGT\\SQLEXPRESS:1433;databaseName=student_management;user=sa;password=anhkk123";
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public enum ConnectionType {
        MYSQL,
        SQL_SERVER
    }
}
