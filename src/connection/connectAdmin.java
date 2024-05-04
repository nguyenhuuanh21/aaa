package connection;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.Employee;

public class connectAdmin {
		public static boolean getAccountAD(Employee employee) {
		Connection conn=connectDB.getConnection();
		ResultSet rs;
		try(conn){
			String sql="select*from Admin where email=? and password=?";
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

	public static int getIdByAccountAD(Employee employee) {
		int id=0;
		var conn=connectDB.getConnection();
		try(conn){
			String sql="select * from Admin where email=? and password=?";
			 PreparedStatement ps=conn.prepareCall(sql);
             ps.setString(1,employee.getEmail());
             ps.setString(2,employee.getPassword());
	         var resultSet = ps.executeQuery();
	         while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
	             id=resultSet.getInt("admin_id");
	         }
		}catch(SQLException e)  {
			e.printStackTrace();
		}
		return id;
	}
	public static String getNameByAccountAD(Employee employee) {
		String name="";
		var conn=connectDB.getConnection();
		try(conn){
			String sql="select * from Admin where email=? and password=?";
			 PreparedStatement ps=conn.prepareCall(sql);
             ps.setString(1,employee.getEmail());
             ps.setString(2,employee.getPassword());
	         var resultSet = ps.executeQuery();
	         while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
	             name=resultSet.getString("admin_name");
	         }
		}catch(SQLException e)  {
			e.printStackTrace();
		}
		return name;
	}
	 public static Employee readAdById(int id) throws SQLException, IOException {
	        Employee employee = null;
	        Connection conn = connectDB.getConnection();
	        try (conn) {
	            var sql = "SELECT * FROM Admin where admin_id = ?" ;
	            var ps = conn.prepareStatement(sql);
	            ps.setInt(1, id);
	            var resultSet=ps.executeQuery();
	            while (resultSet.next()) {
	                var admin_id = String.valueOf(resultSet.getInt(1));
	                var admin_name = resultSet.getString(2);
	                var gender = resultSet.getString(3);
	                var date_of_birth_sql = resultSet.getDate(4);
	                var date_of_birth = date_of_birth_sql.toLocalDate();
	                var department_id = resultSet.getString(5);
	                var address = resultSet.getString(6);
	                var phone = resultSet.getString(7);
	                var email = resultSet.getString(8);
	                var password = resultSet.getString(9);
	                
	                byte[] imageData = resultSet.getBytes("image");
	                Image image = null; // Khởi tạo image là null mặc định
	                if (imageData != null) {
	                    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
	                    image = SwingFXUtils.toFXImage(bufferedImage, null);
	                } else {
	                    // Nếu không có dữ liệu hình ảnh từ cơ sở dữ liệu, bạn có thể xác định hình ảnh mặc định ở đây
	                    // Ví dụ:
	                    // image = getDefaultImage();
	                	 //image = getDefaultImage();
	                }
	                employee = new Employee(admin_id, admin_name, gender, date_of_birth, department_id,
	                        address, phone, email, password, image);
	                
	                
	            }
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	        return employee;
	    }
	 public static int updateAdminById(int id,Employee employee) {
		 int n=0;
		 Connection conn = connectDB.getConnection();
		 try(conn){
			 var sql="UPDATE Admin SET admin_name = ? , gender = ? , date_of_birth = ? , department_id = ? , address = ? , phone = ? , email = ? , password = ? WHERE admin_id = ?";
			 var ps = conn.prepareStatement(sql);
			 ps.setString(1, employee.getName());
			 ps.setString(2, employee.getGender());
			 LocalDate localDate = employee.getBirth();
	         java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
	         ps.setDate(3, (java.sql.Date) sqlDate);
	         ps.setString(4, employee.getDepartment());
	         ps.setString(5, employee.getAddress());
	         ps.setString(6,employee.getPhone());
	         ps.setString(7,employee.getEmail());
	         ps.setString(8,employee.getPassword());
	         ps.setInt(9,id);
	         return ps.executeUpdate();
		 }catch (SQLException ex) {
	           	ex.printStackTrace();
	            return -1;
	        } 
	 }

}
