package controller;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class AddAccountController extends Controller {
	
	@FXML
    private TextField addAddress;

    @FXML
    private DatePicker addBirth;

    @FXML
    private ComboBox<?> addDepartment;

    @FXML
    private TextField addEmail;

    @FXML
    private ComboBox<?> addGender;

    @FXML
    private TextField addName;

    @FXML
    private TextField addPassword;

    @FXML
    private TextField addPhone;


    @FXML
    private Button changeImage;
    
    @FXML
    private ImageView addImage;
    
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
	            addImage.setImage(image);
	        }
	 }

	
	@FXML
    public void addSave(ActionEvent event)throws IOException{
    	super.adminEmployees(event);
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
	 public void register(ActionEvent event) throws IOException{
		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setContentText("Register successfully");
         System.out.println("Yes");
         alert.showAndWait();
	 }

}
