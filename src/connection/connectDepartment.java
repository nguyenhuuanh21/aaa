package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import connection.connectDB.ConnectionType;
import model.Department;

public class connectDepartment {
	 public static List<Department> readDepartment() throws SQLException {
	        List<Department> departments = new ArrayList<>();		       
	        Connection conn =connectDB.getConnection(ConnectionType.MYSQL);
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
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	        return departments;
	    }
	 public static void updateQuantityEmployee() {
			Connection conn =connectDB.getConnection(ConnectionType.MYSQL);
			try (conn) {
	            var sql = "UPDATE pb \r\n"
	            		+ "SET pb.department_quantity = nv.department_count \r\n"
	            		+ "FROM departments AS pb \r\n"
	            		+ "INNER JOIN (\r\n"
	            		+ "    SELECT department_id, SUM(department_count) AS department_count \r\n"
	            		+ "    FROM (\r\n"
	            		+ "        SELECT department_id, COUNT(*) AS department_count \r\n"
	            		+ "        FROM Employee \r\n"
	            		+ "        GROUP BY department_id \r\n"
	            		+ "        UNION ALL \r\n"
	            		+ "        SELECT department_id, COUNT(*) AS department_count \r\n"
	            		+ "        FROM Admin \r\n"
	            		+ "        GROUP BY department_id \r\n"
	            		+ "    ) AS combined\r\n"
	            		+ "    GROUP BY department_id\r\n"
	            		+ ") AS nv ON pb.department_id = nv.department_id;"; // câu lệnh truy vấn SQL
	            var statement = conn.createStatement(); // lấy đối tượng Statement
	            var resultSet = statement.executeUpdate(sql); // lấy đối tượng ResultSet
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
			
		}
	
}

