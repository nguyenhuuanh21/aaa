package connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.microsoft.sqlserver.jdbc.SQLServerException;

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
}
