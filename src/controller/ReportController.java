package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import connection.connectReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Report;


public class ReportController extends Controller implements Initializable{

    @FXML
    private Button btnSend;

    @FXML
    private Button buttonHome;

    @FXML
    private Button menu_calendar;

    @FXML
    private Button menu_department;

    @FXML
    private Button menu_edit;

    @FXML
    private Button menu_logout;

    @FXML
    private Button menu_setting;

    @FXML
    private VBox report;

    @FXML
    private TextField textReport;

	
	@FXML
	private int currentPage = 1;
	@FXML
	private int totalPage ;//số truy report / 5
	
	@FXML
	private void previous(ActionEvent event) {
		if(currentPage > 1) {
			currentPage--;
			display(currentPage);
		}
	}
	
	@FXML
	private void next(ActionEvent event) {
		if(currentPage < totalPage) {
			currentPage++;
			display(currentPage);
		}
	}
	
	 @FXML
	    public void display(int page) {
	        report.getChildren().clear();
	        int start = (page - 1) * 5 + 1;
	        for (int i = start; i <= start + 4; i++) {
	            if (i <= totalPage * 5) {
	                Label label = new Label("Comment " + i);
	                report.getChildren().add(label);
	            }
	        }
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
	 @FXML
	    public void sendReport(ActionEvent event)throws IOException {
		 LocalTime currentTime = LocalTime.now();
		 LocalDate currentDate = LocalDate.now();
		 Report report=new Report(id,currentTime,currentDate,textReport.getText());
		 int n=connectReport.addReport(report);
		 if(n>0) {
			  System.out.println("Yes");
		 }else {
			  System.out.println("No" );
		 }
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		display(currentPage);
	}


}
