package controller;

import java.io.IOException;

<<<<<<< HEAD
import connection.ConnectEmployee;
import connection.connectDB;
=======
>>>>>>> 40e44fc388683c443727250614b6a9af5ef95afd
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
<<<<<<< HEAD
import model.Employee;
=======
import connection.connectDB;

>>>>>>> 40e44fc388683c443727250614b6a9af5ef95afd

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
    public void login(ActionEvent event) throws IOException {
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
<<<<<<< HEAD
        }else {
        	Employee acc=new Employee(email,password);
        	boolean successed=ConnectEmployee.getAccount( acc);
        	if(successed) {
        		login_warning_email.setVisible(false);
	            login_warning_password.setVisible(false);
	            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
	            root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
	            scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();		
        	}else {
        		 login_warning_email.setText("Please type correct your email");
		            login_warning_password.setText("Please type correct your password");
		            login_warning_email.setVisible(true);
		            login_warning_password.setVisible(true);
        	}
        }
        	/*
        }	
            connectDB cn=new connectDB();
	      	Connection conn=null;
	    	try {
	    		conn=cn.getConnection();
				String sql="select*from Employee where email=? and password=?";
				PreparedStatement ps=conn.prepareCall(sql);
				ps.setString(1, login_email.getText());
		        ps.setString(2, login_password.getText());
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
				
					   login_warning_email.setVisible(false);
			            login_warning_password.setVisible(false);
			            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			            root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
			            scene = new Scene(root);
			            stage.setScene(scene);
			            stage.show();		           
				}else {				
					 login_warning_email.setText("Please type correct your email");
			            login_warning_password.setText("Please type correct your password");
			            login_warning_email.setVisible(true);
			            login_warning_password.setVisible(true);
			    }
	
			}// catch(SQLException e) {
				e.printStackTrace();
			//}
=======
        }else if(email.equals("a") && password.equals("1")) {
        	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();	
        } else if(connectDB.checkAccount(email, password)) {
        	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();	
>>>>>>> 40e44fc388683c443727250614b6a9af5ef95afd
        }
		*/
   }

    @FXML
    public void toggleShowPassword(ActionEvent event) {
        
    }
}
