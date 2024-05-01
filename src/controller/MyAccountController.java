package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
<<<<<<< HEAD
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Employee;


public class MyAccountController extends Controller  {
	private  Employee loggedInEmployee1;
	@FXML
    private Label SettingBirth;

    @FXML
    private Label SettingPhone;

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
    private Label settingAddress;

    @FXML
    private Label settingDepartment;

    @FXML
    private Label settingEmail;

    @FXML
    private Label settingGender;

    @FXML
    private Label settingName;

    @FXML
    private Label settingPassword;
=======
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MyAccountController extends Controller {
	private Stage stage;
	private AnchorPane root;
	private Scene scene;
>>>>>>> c8632315fe63e56ccec33498861616795dd76c6e
	@FXML
	public void editProfile(ActionEvent event) {
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
   	 try {
				root = FXMLLoader.load(getClass().getResource("/view/Setting.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

       if(root == null) {
       	System.out.println("Root is null");
       	return;
       }
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();

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
	 
	 	

	

}
