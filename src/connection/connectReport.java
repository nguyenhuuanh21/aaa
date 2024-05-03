package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.Department;
import model.Report;

public class connectReport {
	public static int addReport(Report report) {
		var conn=connectDB.getConnection();
		try(conn) {
			String sql="INSERT INTO report(employee_id, report_time, report_date, report_content)"
             		+ " values(?,?,?,?)";
			 PreparedStatement ps=conn.prepareCall(sql);
             ps.setInt(1,report.getEmployeeID());
             LocalTime localtime = report.getTime();
             java.sql.Time sqlTime= java.sql.Time.valueOf(localtime);
             ps.setTime(2, (java.sql.Time) sqlTime);
             LocalDate localDate = report.getDate();
             java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
             ps.setDate(3, (java.sql.Date) sqlDate);
             ps.setString(4,report.getContent());
             return ps.executeUpdate();
		}catch (SQLServerException throwables) {
            throwables.printStackTrace();
            return -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
	}
	public static List<Report> readReport() {
        List<Report> reports = new ArrayList<>();		       
        Connection conn =connectDB.getConnection();
        try (conn) {
            var sql = "SELECT * FROM dbo.report ORDER BY report_id ASC "; // câu lệnh truy vấn SQL
            var statement = conn.createStatement(); // lấy đối tượng Statement
            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
                var report_id = resultSet.getInt(1);
                var employee_id = resultSet.getInt(2);
                var report_time=resultSet.getTime(3).toLocalTime();
                var report_date=resultSet.getDate(4).toLocalDate();
                var report_content=resultSet.getString(5);
                Report report = new Report(report_id,employee_id,report_time,report_date,report_content);
                reports.add(report); 
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return reports;
    }
}
