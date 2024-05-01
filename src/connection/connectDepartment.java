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


/*
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Department;

public class connectDepartment {
	static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/quan_ly_nhan_su";
	static final String USER = "root";
	static final String PASS = "";
		public static ArrayList<Department> getDepartment() throws ClassNotFoundException, SQLException {
			Connection conn = null;
			Statement stmt = null;
			ArrayList<Department> list = new ArrayList<>();
			try {
				System.out.println("STEP 1: Register JDBC driver");
				Class.forName(DRIVER_CLASS);
		
				System.out.println("STEP 2: Open a connection");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
				System.out.println("STEP 3: Execute a query");
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM department");
		
				System.out.println("STEP 4: Extract data from result set");
			while (rs.next()) {	
				String id = rs.getString("id");
				String name = rs.getString("name");
				list.add(new Department(id, name));
				System.out.print("ID: " + id);
				System.out.println(", name: " + name);
			}
				rs.close();
			}catch (SQLException e) {
				throw e;
			} finally {
				System.out.println("STEP 5: Close connection");
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} 
			System.out.println("Done!");
			return list;
		}
	    	

}
*/
