package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Department;

public class connectDepartment {
	public static List<Department> readDepartment() {
	    List<Department> departments = new ArrayList<>();
	    try (Connection conn = connectDB.getConnection()) {
	        var sql = "SELECT * FROM department ORDER BY department_id ASC"; // câu lệnh truy vấn SQL
	        var statement = conn.createStatement(); // lấy đối tượng Statement
	        var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
	        while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
	            var department_id = resultSet.getString("department_id");
	            var department_name = resultSet.getString("department_name");
	            Department department = new Department(department_id, department_name);
	            departments.add(department); 
	        }
	    } catch (SQLException throwables) {
	        throwables.printStackTrace();
	    } 
	    return departments;
	}
	
	public static Map<String, Integer> countEmployeesByDepartment() throws SQLException {
        Map<String, Integer> departmentEmployeeCountMap = new HashMap<>();
        
        try (Connection conn = connectDB.getConnection()) {
            String sql = "SELECT department_id, COUNT(*) AS employee_count FROM employee GROUP BY department_id";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    String departmentId = rs.getString("department_id");
                    int employeeCount = rs.getInt("employee_count");
                    departmentEmployeeCountMap.put(departmentId, employeeCount);
                }
            }
        }
        
        return departmentEmployeeCountMap;
    }

	 public static void updateQuantityEmployee() {
			Connection conn =connectDB.getConnection();
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