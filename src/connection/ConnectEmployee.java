package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.Employee;

public class ConnectEmployee {
    public static int addEmployee(Employee employee) throws SQLException {
        Connection conn = ConnectDB.getConnection(null);
        int n = 0;
        try (conn) {
            String sql = "INSERT INTO Employee(employee_name, gender, date_of_birth, department_id, address, phone, email, password)"
                    + " values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getGender());
            LocalDate localDate = employee.getBirth();
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
            ps.setDate(3, sqlDate);
            ps.setString(4, employee.getDepartment());
            ps.setString(5, employee.getAddress());
            ps.setString(6, employee.getPhone());
            ps.setString(7, employee.getEmail());
            ps.setString(8, employee.getPassword());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public static boolean getAccount(Employee employee) throws SQLException {
        Connection conn = ConnectDB.getConnection(null);
        ResultSet rs;
        try (conn) {
            String sql = "select * from Employee where email=? and password=?";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, employee.getEmail());
            ps.setString(2, employee.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static List<Employee> readEmployee() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection conn = ConnectDB.getConnection(null);
        try (conn) {
            var sql = "SELECT * FROM dbo.Employee";
            var statement = conn.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                var employee_id = String.valueOf(resultSet.getInt(1));
                var employee_name = resultSet.getString(2);
                var gender = resultSet.getString(3);
                var date_of_birth_sql = resultSet.getDate(4);
                var date_of_birth = date_of_birth_sql.toLocalDate();
                var department_id = resultSet.getString(5);
                var address = resultSet.getString(6);
                var phone = resultSet.getString(7);
                var email = resultSet.getString(8);
                var password = resultSet.getString(9);
                Employee employee = new Employee(employee_id, employee_name, gender, date_of_birth, department_id,
                        address, phone, email, password);
                employees.add(employee);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }
<<<<<<< HEAD
	public static Employee getEmployeeByEmail(String email) {
		var conn =connectDB.getConnection();
		Employee employee=null;
		try(conn){
			 String sql = "SELECT * FROM Employee WHERE email = ?";
		     PreparedStatement ps = conn.prepareStatement(sql);
		     ps.setString(1, email);
		     ResultSet rs = ps.executeQuery();
		     if(rs.next()) {
		    	 String employee_id = String.valueOf(rs.getInt(1));
		         String employee_name = rs.getString(2);
		         String gender = rs.getString(3);
		         LocalDate date_of_birth = rs.getDate(4).toLocalDate();
		         String department_id = rs.getString(5);
		         String address = rs.getString(6);
		         String phone = rs.getString(7);
		         String password = rs.getString(9);
		         employee=new Employee(employee_id, employee_name, gender, date_of_birth, department_id, address, phone, email, password);
		     }
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return employee;
		
	}
}
=======
}
>>>>>>> c8632315fe63e56ccec33498861616795dd76c6e
