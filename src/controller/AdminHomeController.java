package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class AdminHomeController extends Controller implements Initializable{


	    @FXML
	    LineChart<String, Number> myLineChart = new LineChart<>(new CategoryAxis(), new NumberAxis());
	    
	    @FXML
	    private PieChart myPieChart;

	    
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
	    	super.adminSetting(event);
	    }
		

		 @FXML
		    public void logout(ActionEvent event)throws IOException {
		    	 super.logout(event);
		 }
    
    private void inPieChart() {
		ObservableList<PieChart.Data> pieCharData = FXCollections.observableArrayList(
				new PieChart.Data("1",20),
				new PieChart.Data("2",5),
				new PieChart.Data("3",15),
				new PieChart.Data("4",35),
				new PieChart.Data("5",25)
				);
		myPieChart.setData(pieCharData);
		if(myPieChart == null) {
			System.out.println("Pie chart is null");
		}
	}
    

 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		inLineChart();
		inPieChart();
	}
}
