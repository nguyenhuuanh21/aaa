package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Department;

public class connectDepartment {
    static final String SQL_SERVER_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String SQL_SERVER_DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=your_database_name";
    static final String SQL_SERVER_USER = "your_username";
    static final String SQL_SERVER_PASS = "your_password";

    static final String MYSQL_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/quan_ly_nhan_su";
    static final String MYSQL_USER = "root";
    static final String MYSQL_PASS = "";

    public static List<Department> readDepartment() {
        List<Department> departments = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Check if the SQL Server driver is available
            Class.forName(SQL_SERVER_DRIVER_CLASS);
            conn = DriverManager.getConnection(SQL_SERVER_DB_URL, SQL_SERVER_USER, SQL_SERVER_PASS);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dbo.department");
        } catch (ClassNotFoundException | SQLException e) {
            // If SQL Server driver is not available, fallback to MySQL driver
            try {
                Class.forName(MYSQL_DRIVER_CLASS);
                conn = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_USER, MYSQL_PASS);
                statement = conn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM department");
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    String department_id = resultSet.getString(1);
                    String department_name = resultSet.getString(2);
                    Department department = new Department(department_id, department_name);
                    departments.add(department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return departments;
    }
}