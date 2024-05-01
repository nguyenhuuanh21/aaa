package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.ConnectEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;


public class MyAccountController extends Controller implements Initializable{
	
	private Stage stage;
	private AnchorPane root;
	private Scene scene;
	
	 @FXML
	    private Label address;

	    @FXML
	    private Label birth;

	    @FXML
	    private Label department;

	    @FXML
	    private Label email;

	    @FXML
	    private Label gender;
	    @FXML
	    private Label name;

	    @FXML
	    private Label password;

	    @FXML
	    private Label phone;
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
	public void display() throws SQLException {
		Employee em = ConnectEmployee.readEmployee(id);
		name.setText(em.getName());
		phone.setText(em.getPhone());
		password.setText(em.getPassword());
		email.setText(em.getEmail());
		department.setText(em.getDepartment());
		gender.setText(em.getGender());
		birth.setText(em.getBirth().toString());
		address.setText(em.getAddress());
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			display();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
