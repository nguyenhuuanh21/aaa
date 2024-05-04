package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class connectAttendance {
	public static Map<Integer, Integer> getEmployeeAttendance(int id, int month) {
	    Map<Integer, Integer> attendanceMap = new HashMap<>();
	    String sql = "SELECT DAY(checkE_date) AS day_of_month, " +
	                 "CASE " +
	                 "WHEN COUNT(*) > 0 AND SUM(CASE WHEN checkout_datetime IS NULL OR checkin_time IS NULL THEN 1 ELSE 0 END) > 0 THEN 3 " +
	                 "WHEN COUNT(*) > 0 AND SUM(TIME_TO_SEC(TIMEDIFF(checkout_time, checkin_datetime))/3600) >= 8 THEN 1 " +
	                 "ELSE 2 " +
	                 "END AS attendance_status " +
	                 "FROM attendance " +
	                 "WHERE MONTH(checkE_date) = ? AND employee_id = ? " +
	                 "GROUP BY day_of_month";

	    try (Connection conn = connectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, month);
	        stmt.setInt(2, id); // Add employee_id parameter
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                int dayOfMonth = rs.getInt("day_of_month");
	                int attendanceStatus = rs.getInt("attendance_status");
	                attendanceMap.put(dayOfMonth, attendanceStatus);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return attendanceMap;
	}

	public List<String> getTopEmployee(int month) {
	    List<String> topEmployees = new ArrayList<>();
	    String sql = "SELECT employee_id, " +
	                 "SUM(TIME_TO_SEC(TIMEDIFF(checkout_datetime, checkin_datetime))/3600) AS total_hours " +
	                 "FROM attendance " +
	                 "WHERE MONTH(checkin_datetime) = ? " +
	                 "GROUP BY employee_id " +
	                 "ORDER BY total_hours DESC " +
	                 "LIMIT 3";

	    try (Connection conn = connectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, month);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                int employeeId = rs.getInt("employee_id");
	                double totalHours = rs.getDouble("total_hours");
	                String result = "Employee ID: " + employeeId + ", Total Hours: " + totalHours;
	                topEmployees.add(result);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return topEmployees;
	}


	public static Map<Integer, Integer> totalEmployeeWork(int month) {
	    Map<Integer, Integer> employeeWorkMap = new HashMap<>();
	    String sql = "SELECT DAY(checkin_datetime) AS work_date, COUNT(DISTINCT employee_id) AS employee_count " +
	                 "FROM attendance " +
	                 "WHERE MONTH(checkin_datetime) = ? " +
	                 "GROUP BY DAY(checkin_datetime)";

	    try (Connection conn = connectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, month);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Integer workDate = rs.getInt("work_date");
	                int employeeCount = rs.getInt("employee_count");
	                employeeWorkMap.put(workDate, employeeCount);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return employeeWorkMap;
	}

}
