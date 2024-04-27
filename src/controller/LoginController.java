package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.connectDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
public class LoginController {
	@FXML
    private Button login_button;

    @FXML
    private TextField login_email;

    @FXML
    private PasswordField login_password;

    @FXML
    private CheckBox login_remember;

    @FXML
    private Label login_warning_email;

    @FXML
    private Label login_warning_password;

    private Stage stage;
    private AnchorPane root;
    private Scene scene;

    @FXML
    public void login(ActionEvent event) throws IOException, ClassNotFoundException {
        String email = login_email.getText();
        String password = login_password.getText();

        if (email.isEmpty() && password.isEmpty()) {
            login_warning_email.setText("Please type your email");
            login_warning_password.setText("Please type your password");
            login_warning_email.setVisible(true);
            login_warning_password.setVisible(true);
        } else if (email.isEmpty()) {
            login_warning_email.setText("Please type your email");
            login_warning_email.setVisible(true);
        } else if (password.isEmpty()) {
            login_warning_password.setText("Please type your password");
            login_warning_password.setVisible(true);
        }else if (email.equals("a") && password.equals("1"))  {
        	login_warning_email.setVisible(false);
            login_warning_password.setVisible(false);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
	    	try {
	    		boolean flag = false;
	    		ArrayList<Employee> list = connectDB.getData();
	    		for(int i = 0 ;i < list.size();i++) {	
	    			if (list.get(i).getEmail().equals(email) && list.get(i).getPassword().equals(password))  {
					   login_warning_email.setVisible(false);
			            login_warning_password.setVisible(false);
			            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			            root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
			            scene = new Scene(root);
			            stage.setScene(scene);
			            stage.show();		           
			            flag = true;
	    			}
	    		}
	    		if(!flag){				
					 login_warning_email.setText("Please type correct your email");
			            login_warning_password.setText("Please type correct your password");
			            login_warning_email.setVisible(true);
			            login_warning_password.setVisible(true);
			    	}
			} catch(SQLException e) {
				e.printStackTrace();
			}
        }
    }

    @FXML
    public void toggleShowPassword(ActionEvent event) {
        
    }
}
