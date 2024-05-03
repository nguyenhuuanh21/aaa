package controllerE;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllerE.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
public class Home extends controller implements Initializable {
	 @FXML
	    private Label hello;
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
		displayName();
		
	}
}
