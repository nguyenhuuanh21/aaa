package connection;

import java.sql.Connection;
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
				int id = rs.getInt("id");
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
