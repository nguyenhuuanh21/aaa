package controller;

import java.io.IOException;
<<<<<<< HEAD
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
=======
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
>>>>>>> c8632315fe63e56ccec33498861616795dd76c6e

import connection.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
<<<<<<< HEAD
import javafx.scene.Parent;
=======
import javafx.fxml.Initializable;
>>>>>>> c8632315fe63e56ccec33498861616795dd76c6e
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



public class LoginController implements Initializable {
	
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
    private FXMLLoader loader;
    private Stage stage;
    private AnchorPane root;
    private Scene scene;
    private Employee loggedInEmployee;

    @FXML
    public void login(ActionEvent event) throws IOException, SQLException {
        String email = login_email.getText();
        String password = login_password.getText();
        try {
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
        } catch (Exception e) {
            // Xử lý ngoại lệ được ném trong quá trình đăng nhập
            if (e instanceof InvocationTargetException) {
                Throwable targetException = ((InvocationTargetException) e).getTargetException();
                targetException.printStackTrace();
            } else {
                e.printStackTrace();
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
	
			} catch(SQLException e) {
				e.printStackTrace();
			//}
=======
=======
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
>>>>>>> c8632315fe63e56ccec33498861616795dd76c6e
        }else if(email.equals("a") && password.equals("1")) {
        	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/viewE/Home.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Employee acc = new Employee(email, password);
            boolean successed = ConnectEmployee.getAccount(acc);
            if (successed) {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
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
<<<<<<< HEAD
		*/
   }
   
=======
>>>>>>> c8632315fe63e56ccec33498861616795dd76c6e

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