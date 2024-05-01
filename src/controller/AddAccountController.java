package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Department;
import model.Employee;
import connection.ConnectEmployee;
import connection.connectDepartment;

public class AddAccountController extends Controller implements Initializable {

    @FXML
    private TextField addAddress;

    @FXML
    private DatePicker addBirth;

    @FXML
    private ComboBox<String> addDepartment;

    @FXML
    private TextField addEmail;

    @FXML
    private ComboBox<String> addGender;

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
    private void showGender() {
        addGender.getItems().addAll("Male", "Female");
    }

    @FXML
    private void showDepartment() throws SQLException {
        List<Department> departments = connectDepartment.readDepartment();
        List<String> departmentNames = new ArrayList<>();
        for (Department department : departments) {
            departmentNames.add(department.getDepartment_name());
        }
        addDepartment.getItems().addAll(departmentNames);
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
            addImage.setImage(image);
        }
    }

    @FXML
    public void addSave(ActionEvent event) throws IOException {
        if (addEmail.getText().isEmpty() || addName.getText().isEmpty() || addPassword.getText().isEmpty()
                || addAddress.getText().isEmpty() || addPhone.getText().isEmpty() || addDepartment.getValue() == null
                || addGender.getValue() == null || addBirth.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("You need to fill all fields!!!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save Profile");
            alert.setContentText("Do you want to save?");
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
<<<<<<< HEAD
                    Employee employee=new Employee(addName.getText(),addGender.getValue(),addBirth.getValue(),
                    		addDepartment.getValue(),addAddress.getText(),addPhone.getText(),addEmail.getText(),addPassword.getText());
                    int n=ConnectEmployee.addEmployee(employee);
                    if(n!=0) {
                    	alert1.setContentText("Register successfully");
                        System.out.println("Yes");                      
                        alert1.showAndWait();
                        connectDepartment.updateQuantity();
                    }else {
                    	 System.out.println("No"); 
=======
                    Employee employee = new Employee(addName.getText(), addGender.getValue(), addBirth.getValue(),
                            addDepartment.getValue(), addAddress.getText(), addPhone.getText(), addEmail.getText(),
                            addPassword.getText());
                    int n;
                    try {
                        n = ConnectEmployee.addEmployee(employee);
                        if (n != 0) {
                            alert1.setContentText("Register successfully");
                            System.out.println("Yes");
                            alert1.showAndWait();
                        } else {
                            System.out.println("No");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
>>>>>>> c8632315fe63e56ccec33498861616795dd76c6e
                    }
                    try {
                        super.adminEmployees(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    public void adminHome(ActionEvent event) throws IOException {
        super.adminHome(event);
    }

    @FXML
    public void adminEmployees(ActionEvent event) throws IOException {
        super.adminEmployees(event);
    }

    @FXML
    public void adminDepartment(ActionEvent event) throws IOException {
        super.adminDepartment(event);
    }

    @FXML
    public void calendar(ActionEvent event) throws IOException {
        super.calendar(event);
    }

    @FXML
    public void adminReport(ActionEvent event) throws IOException {
        super.adminReport(event);
    }

    @FXML
    public void adminSetting(ActionEvent event) throws IOException {
        super.adminMyAccount(event);
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        super.logout(event);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            showDepartment();
            showGender();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}