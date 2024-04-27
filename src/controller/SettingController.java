package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class SettingController extends Controller implements Initializable {
	private Stage stage;
	private AnchorPane root;
	private Scene scene;

    @FXML
    private Label settingError;
	
	@FXML
    private TextField SettingBirth;

    @FXML
    private TextField SettingPhone;
    
    @FXML
    private TextField settingAddress;

    @FXML
    private ComboBox<String> settingDepartment;

    @FXML
    private TextField settingEmail;

    @FXML
    private ComboBox<String> settingGender;

    @FXML
    private TextField settingName;

    @FXML
    private TextField settingPassword;

    @FXML
    private ImageView myImage;

    @FXML
    private Button settingSave;
    
    @FXML
    private Button changeImage;

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
    public void adminDelete(ActionEvent event)throws IOException{
    	super.adminDelete(event);
    }
	
	@FXML
    public void adminAdd(ActionEvent event)throws IOException{
    	super.adminAdd(event);
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
	            myImage.setImage(image);
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
            	 stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                 try {
					root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
             }
         });
	 }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
