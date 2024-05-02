package connection;

import java.sql.Connection;


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


