package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Employee;

public class ConnectEmployee {
	public static int addEmployee(Employee employee) {
		Connection conn=connectDB.getConnection();
		int n = 0;
		try(conn){
			 String sql="INSERT INTO Employee(employee_name, gender, date_of_birth, department_id, address, phone, email, password)"
             		+ " values(?,?,?,?,?,?,?,?)";
			 PreparedStatement ps=conn.prepareCall(sql);
             ps.setString(1,employee.getName());
             ps.setString(2,employee.getGender());
             LocalDate localDate = employee.getBirth();
             java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
             ps.setDate(3, (java.sql.Date) sqlDate);
             ps.setString(4,employee.getDepartment());
             ps.setString(5,employee.getAddress());
             ps.setString(6,employee.getPhone());
             ps.setString(7,employee.getEmail());
             ps.setString(8,employee.getPassword());
             n=ps.executeUpdate();	
            
		}catch(SQLException e) {
			e.printStackTrace();
		}
		 return n;
	}
	public static boolean getAccount(Employee employee) {
		Connection conn=connectDB.getConnection();
		ResultSet rs;
		try(conn){
			String sql="select*from Employee where email=? and password=?";
			PreparedStatement ps=conn.prepareCall(sql);
			ps.setString(1, employee.getEmail());
	        ps.setString(2, employee.getPassword());
			rs=ps.executeQuery();
			if( rs.next()) {
				return true;
			}
		}catch(SQLException e)  {
			e.printStackTrace();
		}
		
			return false;

	}
}
