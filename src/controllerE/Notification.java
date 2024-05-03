package controllerE;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import connection.connectReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Report;
public class Notification extends controller implements Initializable {
	
	@FXML
	private VBox report;
	 @FXML
	    private Label hello;
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
	
	 private void display(int page) {
	    	List<Report>reports=connectReport.readReport();
	    	String []content=new String[reports.size()];
	    	for(int i=0;i<reports.size();i++) {
	    		content[i]=reports.get(i).getContent();  		
	    	}
	    	totalPage=reports.size();
	        report.getChildren().clear();
	        int start = (page - 1) * 5 + 1;
	        double labelWidth = 800; 
	        double labelHeight = 160; 
	        int j=0;
	        for (int i = start - 1; i < Math.min(start + 4, totalPage); i++) {
	            if (i < content.length) {
	                Label label = new Label("Comment " + (i + 1) +" : "+ content[i]);
	                label.setAlignment(Pos.TOP_LEFT);
	                label.setPrefWidth(labelWidth);
	                label.setPrefHeight(labelHeight);
	                label.setFont(new Font(16));
	                label.setWrapText(true); 
	                label.setStyle("-fx-padding: 5;");
	                report.getChildren().add(label);
	            }
	        }

	    }
	@FXML
    public void HomeE(ActionEvent event)throws IOException {
    	super.HomeE(event);
    }
	
	@FXML
    public void settingE(ActionEvent event)throws IOException{
    	super.settingE(event);
    }
	@FXML
    public void calendarE(ActionEvent event)throws IOException{
    	super.calendarE(event);
    }
	
	@FXML
    public void notificationE(ActionEvent event)throws IOException{
    	super.notificationE(event);
    }
	
	@FXML
    public void MyAccountE(ActionEvent event)throws IOException{
    	super.MyAccountE(event);
    }
	

	 @FXML
	    public void logout(ActionEvent event)throws IOException {
	    	super.logout(event);
	 }
	 public void displayName() {
	    	hello.setText("Hello : " +getName );
	    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		display(currentPage);
		 displayName();
		
	}
}
