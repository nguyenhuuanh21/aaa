package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB.ConnectionType;

import model.Employee;

public class ConnectEmployee {
    public static int addEmployee(Employee employee) throws SQLException {
        Connection conn = ConnectDB.getConnection(ConnectionType.MYSQL);
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
    
    public static String getName(int id) throws SQLException {
        String name = null;
        Connection conn = ConnectDB.getConnection(ConnectionType.MYSQL);
        ResultSet rs;
        try (conn) {
            String sql = "SELECT employee_name FROM Employee WHERE employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("employee_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    
    public static int getId(Employee employee) throws SQLException {
        Connection conn = ConnectDB.getConnection(ConnectionType.MYSQL);
        ResultSet rs;
        int id = -1;
        try (conn) {
            String sql = "select * from Employee where email=? and password = ?";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, employee.getEmail());
            ps.setString(2, employee.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("employee_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static boolean getAccount(Employee employee) throws SQLException {
        Connection conn = ConnectDB.getConnection(ConnectionType.MYSQL);
        ResultSet rs;
        boolean accountExists = false;
        try (conn) {
            String sql = "select * from Employee where email=? and password = ?";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, employee.getEmail());
            ps.setString(2, employee.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                accountExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountExists;
    }
    
    public static Employee readEmployee(int id) throws SQLException {
        Employee employee = null;
        Connection conn = ConnectDB.getConnection(ConnectionType.MYSQL);
        try (conn) {
            var sql = "SELECT * FROM Employee where employee_id = " + id ;
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
                 employee = new Employee(employee_id, employee_name, gender, date_of_birth, department_id,
                        address, phone, email, password);
                
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }

    public static List<Employee> readEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection conn = ConnectDB.getConnection(ConnectionType.MYSQL);
        try (conn) {
            var sql = "SELECT * FROM Employee";
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
}