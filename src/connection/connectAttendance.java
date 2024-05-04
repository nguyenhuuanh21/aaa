package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class connectAttendance {
	public static Map<Integer, Integer> getEmployeeAttendance(int id, int month) {
	    Map<Integer, Integer> attendanceMap = new HashMap<>();
	    String sql = "SELECT employee_id, " +
	                 "CASE " +
	                 "WHEN COUNT(*) > 0 AND SUM(CASE WHEN checkout_datetime IS NULL OR checkin_datetime IS NULL THEN 1 ELSE 0 END) > 0 THEN 3 " +
	                 "WHEN COUNT(*) > 0 AND SUM(TIME_TO_SEC(TIMEDIFF(checkout_datetime, checkin_datetime))/3600) >= 8 THEN 1 " +
	                 "ELSE 2 " +
	                 "END AS attendance_status " +
	                 "FROM attendance " +
	                 "WHERE MONTH(checkin_datetime) = ? AND employee_id = ? " +
	                 "GROUP BY employee_id";

	    try (Connection conn = connectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, month);
	        stmt.setInt(2, id); // Add employee_id parameter
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                int employeeId = rs.getInt("employee_id");
	                int attendanceStatus = rs.getInt("attendance_status");
	                attendanceMap.put(employeeId, attendanceStatus);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return attendanceMap;
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
