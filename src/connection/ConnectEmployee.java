package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.Department;
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
	public static List<Employee> readEmployee() {
        List<Employee> employees = new ArrayList<>();		       
        Connection conn =connectDB.getConnection();
        try (conn) {
            var sql = "SELECT * FROM dbo.Employee"; // câu lệnh truy vấn SQL
            var statement = conn.createStatement(); // lấy đối tượng Statement
            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
            	var employee_id = String.valueOf(resultSet.getInt(1));
                
                var employee_name = resultSet.getString(2);
                var gender=resultSet.getString(3);
                var date_of_birth_sql=resultSet.getDate(4);
                var date_of_birth=date_of_birth_sql.toLocalDate();
                var department_id=resultSet.getString(5);
                var address=resultSet.getString(6);
                var phone=resultSet.getString(7);
                var email=resultSet.getString(8);
                var password=resultSet.getString(9);
                Employee employee = new Employee(employee_id,employee_name,gender,date_of_birth,department_id,address,phone,email,password);
                employees.add(employee); 
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }
}
