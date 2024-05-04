package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {

	public static Connection getConnection() {
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

    /*
    public static Connection getConnection() {
        var server = "LAPTOP-0M51BIGT\\SQLEXPRESS";
        var user = "sa";
        var password = "anhkk123";
        var db = "student_management";
        var port = 1433;
        
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(user);
        ds.setPassword(password);
        ds.setDatabaseName(db);
        ds.setServerName(server);
        ds.setPortNumber(port);
        ds.setEncrypt(false);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/database";
        String user = "root";
        String password = "";
        Connection conn = null;
        try {
            conn = ds.getConnection();
            System.out.println("Kết nối với SQL Server thành công!");
            System.out.println(conn.getCatalog());
        } catch (SQLException ex) {
            ex.printStackTrace();
         }
        }*/
}


