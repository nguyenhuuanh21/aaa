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
