package controllerE;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class Setting extends Controller  {
	
	 @FXML
	 public void insertImage() {
		 FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Select Image File");
	        fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("image", "*.png", "*.jpg", "*.gif")
	        );

	        File selectedFile = fileChooser.showOpenDialog(new Stage());
	        if (selectedFile != null) {
	            Image image = new Image(selectedFile.toURI().toString());
	            //myImage.setImage(image);
	        }
	 }
	
	@FXML
	 public void saveInfor(ActionEvent event) {
		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save Profile");
        alert.setContentText("Do you want to save?");
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
           	 try {
					super.MyAccountE(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
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
}
