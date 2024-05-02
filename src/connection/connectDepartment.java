package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB.ConnectionType;
import model.Department;

public class connectDepartment {

    public static List<Department> readDepartment() throws SQLException {
        List<Department> departments = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Get connection from ConnectDB class
            conn = ConnectDB.getConnection(ConnectionType.MYSQL);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM department");

            while (resultSet.next()) {
                String department_id = resultSet.getString("department_id");
                String department_name = resultSet.getString("department_name");
                Department department = new Department(department_id, department_name);
                departments.add(department);
            }
        } finally {
            // Close resources in a finally block to ensure they are always closed
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
