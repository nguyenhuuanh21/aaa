package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import connection.ConnectEmployee;
import connection.connectDepartment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Department;
import model.Employee;

public class AdminHomeController extends Controller implements Initializable{
		
	    LineChart<String, Number> myLineChart = new LineChart<>(new CategoryAxis(), new NumberAxis());
	    
	    @FXML
	    private PieChart myPieChart;
	    @FXML
	    private AnchorPane accountPane;
	    
	    @FXML
	    private void inLineChart() {
	        
	    	myLineChart.setTitle("YOU");
	    	myLineChart.getXAxis().setLabel("X");
	    	myLineChart.getYAxis().setLabel("Y");
	        XYChart.Series<String, Number> series = new XYChart.Series<>();
	        series.getData().add(new XYChart.Data<>("Mon", 8));
	        series.getData().add(new XYChart.Data<>("Tue", 12));
	        series.getData().add(new XYChart.Data<>("Wed", 10));
	        series.getData().add(new XYChart.Data<>("Thu", 15));
	        series.getData().add(new XYChart.Data<>("Fri", 12));
	        series.getData().add(new XYChart.Data<>("Sat", 12));
	        series.getData().add(new XYChart.Data<>("Sun", 8));

	        myLineChart.getData().add(series);
	        if (myLineChart == null) {
	            System.out.println("lineChart is null");
	            return;
	        }
	        myLineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
	        series.getNode().setStyle("-fx-stroke: gray");
	    }

		@FXML
	    public void adminHome(ActionEvent event)throws IOException{
	    	super.adminHome(event);
	    }
		

		@FXML
	    public void adminEmployees(ActionEvent event)throws IOException{
	    	super.adminEmployees(event);
	    }
		@FXML
	    public void adminDepartment(ActionEvent event)throws IOException{
	    	super.adminDepartment(event);
	    }
		
		@FXML
	    public void calendar(ActionEvent event)throws IOException{
	    	super.calendar(event);
	    }
		
		@FXML
	    public void adminReport(ActionEvent event)throws IOException{
	    	super.adminReport(event);
	    }
		
		@FXML
	    public void adminSetting(ActionEvent event)throws IOException{
	    	super.adminMyAccount(event);
	    }
		

		 @FXML
		    public void logout(ActionEvent event)throws IOException {
		    	 super.logout(event);
		 }
		
		 private void inPieChart() throws SQLException {
		        // Gọi hàm countEmployeeDepartment để đếm số nhân viên của từng phòng ban
		        Map<String, Integer> departmentEmployeeCountMap = connectDepartment.countEmployeesByDepartment();

		        // Tạo danh sách dữ liệu cho PieChart
		        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

		        // Duyệt qua từng phòng ban và thêm dữ liệu vào PieChart
		        for (Map.Entry<String, Integer> entry : departmentEmployeeCountMap.entrySet()) {
		            String departmentName = entry.getKey();
		            int employeeCount = entry.getValue();
		            pieChartData.add(new PieChart.Data(departmentName, employeeCount));
		        }

		        // Đặt dữ liệu vào PieChart
		        myPieChart.setData(pieChartData);

		        // Kiểm tra nếu biểu đồ rỗng
		        if (myPieChart.getData().isEmpty()) {
		            System.out.println("Pie chart is empty");
		        }
		    }

    
  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			
			displayName();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		try {
			inLineChart();
			inPieChart();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
