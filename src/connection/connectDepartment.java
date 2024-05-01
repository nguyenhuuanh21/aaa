package connection;

import java.sql.Connection;
<<<<<<< HEAD

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.Department;

public class connectDepartment {
	 public static List<Department> readDepartment() {
	        List<Department> departments = new ArrayList<>();		       
	        Connection conn =connectDB.getConnection();
	        try (conn) {
	            var sql = "SELECT * FROM dbo.departments ORDER BY department_id ASC "; // câu lệnh truy vấn SQL
	            var statement = conn.createStatement(); // lấy đối tượng Statement
	            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
	            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
	                var department_id = resultSet.getString(1);
	                var department_name = resultSet.getString(2);
	                var department_quantity=resultSet.getInt(3);
	                Department department = new Department(department_id,department_name,department_quantity);
	                departments.add(department); 
	            }
	        } catch (SQLServerException throwables) {
	            throwables.printStackTrace();
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	        return departments;
	    }
	 public static void updateQuantity() {
			Connection conn =connectDB.getConnection();
			try (conn) {
	            var sql = "UPDATE pb SET pb.department_quantity = nv.department_count FROM departments AS pb INNER JOIN ( SELECT department_id, COUNT(*) AS department_count FROM Employee GROUP BY department_id ) AS nv ON pb.department_id = nv.department_id;"; // câu lệnh truy vấn SQL
	            var statement = conn.createStatement(); // lấy đối tượng Statement
	            var resultSet = statement.executeUpdate(sql); // lấy đối tượng ResultSet
	        } catch (SQLServerException throwables) {
	            throwables.printStackTrace();
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
			
		}
}


/*
=======
>>>>>>> c8632315fe63e56ccec33498861616795dd76c6e
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Department;

public class connectDepartment {
    static final String SQL_SERVER_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String SQL_SERVER_DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=your_database_name";
    static final String SQL_SERVER_USER = "your_username";
    static final String SQL_SERVER_PASS = "your_password";

    static final String MYSQL_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/quan_ly_nhan_su";
    static final String MYSQL_USER = "root";
    static final String MYSQL_PASS = "";

    public static List<Department> readDepartment() {
        List<Department> departments = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Check if the SQL Server driver is available
            Class.forName(SQL_SERVER_DRIVER_CLASS);
            conn = DriverManager.getConnection(SQL_SERVER_DB_URL, SQL_SERVER_USER, SQL_SERVER_PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbo.department");
        } catch (ClassNotFoundException | SQLException e) {
            // If SQL Server driver is not available, fallback to MySQL driver
            try {
                Class.forName(MYSQL_DRIVER_CLASS);
                conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_USER, MYSQL_PASS);
                statement = conn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM department");
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    String department_id = resultSet.getString(1);
                    String department_name = resultSet.getString(2);
                    Department department = new Department(department_id, department_name);
                    departments.add(department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return departments;
    }
}