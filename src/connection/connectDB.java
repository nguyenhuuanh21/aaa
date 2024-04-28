package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.Department;
import model.Employee;


	public class connectDB {
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
		        Connection conn = null;
		        try {
		            conn = ds.getConnection();
		            System.out.println("Kết nối với SQL Server thành công!");
		            System.out.println(conn.getCatalog());
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		        return conn;
		    }
		 public static List<Department> readDepartment() {
		        List<Department> departments = new ArrayList<>();		       
		        Connection conn=getConnection();
		        try (conn) {
		            var sql = "SELECT * FROM dbo.department"; // câu lệnh truy vấn SQL
		            var statement = conn.createStatement(); // lấy đối tượng Statement
		            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
		            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
		                var department_id = resultSet.getString(1);
		                var department_name = resultSet.getString(2);
		                Department department = new Department(department_id,department_name);
		                departments.add(department); 
		            }
		        } catch (SQLServerException throwables) {
		            throwables.printStackTrace();
		        } catch (SQLException throwables) {
		            throwables.printStackTrace();
		        }
		        return departments;
		    }
}
