package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import model.Employee;



public class LoginController  implements Initializable {
	
	 @FXML
	private Label labelRote;
	@FXML
	 private ComboBox<String> rote;
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
    
    private static int ID;
    public static int getID() {
    	return ID;
    }

    @FXML
    public void login(ActionEvent event) throws IOException, SQLException {
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

        }else if(rote.getValue() == null) {
        	labelRote.setVisible(true);
        }else if(email.equals("a") && password.equals("1")) {
        	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/viewE/Home.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Employee acc = new Employee(email, password);
            boolean successed = ConnectEmployee.getAccount(acc);
            if (successed) {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
                ID = ConnectEmployee.getId(acc);
                System.out.println(ID);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
          
            } else {
                login_warning_email.setText("Please type correct your email");
                login_warning_password.setText("Please type correct your password");
                login_warning_email.setVisible(true);
                login_warning_password.setVisible(true);
            }
        }

    }
    
    public void showRote() {
    	rote.getItems().add("Employee");
    	rote.getItems().add("Admin");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showRote();
	}

}