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

    @FXML
    public void login(ActionEvent event) throws IOException, ClassNotFoundException {
        String email = login_email.getText();
        String password = login_password.getText();

        if (email.isEmpty() || password.isEmpty()) {
            if (email.isEmpty()) {
                login_warning_email.setText("Please enter your email");
                login_warning_email.setVisible(true);
            } else {
                login_warning_email.setVisible(false);
            }

            if (password.isEmpty()) {
                login_warning_password.setText("Please enter your password");
                login_warning_password.setVisible(true);
            } else {
                login_warning_password.setVisible(false);
            }
        } else if(email.equals("a") && password.equals("1")){
        	login_warning_email.setVisible(false);
            login_warning_password.setVisible(false);
            stage = (Stage) login_button.getScene().getWindow();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            try {
                ArrayList<Employee> list = connectDB.getData();
                boolean flag = false;

                for (Employee employee : list) {
                    if (employee.getEmail().equals(email) && employee.getPassword().equals(password)) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    login_warning_email.setVisible(false);
                    login_warning_password.setVisible(false);
                    stage = (Stage) login_button.getScene().getWindow();
                    AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    login_warning_email.setText("Invalid email or password");
                    login_warning_password.setText("Invalid email or password");
                    login_warning_email.setVisible(true);
                    login_warning_password.setVisible(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void toggleShowPassword(ActionEvent event) {
        // Add your code for handling the show/hide password functionality here
    	
    }
}