package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import connection.ConnectEmployee;
import connection.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;



public class ListEmployeeController extends Controller implements Initializable {

    @FXML
    private TableColumn<Employee, String> colAddress;

    @FXML
    private TableColumn<Employee, String> colBirth;

    @FXML
    private TableColumn<Employee, String> colDepartment;

    @FXML
    private TableColumn<Employee, String> colGender;

    @FXML
    private TableColumn<Employee, String> colID;

    @FXML
    private TableColumn<Employee, String> colName;

    @FXML
    private TableColumn<Employee, String> colPhone;

    @FXML
    private TableView<Employee> tableView;

    public void display() throws ClassNotFoundException, SQLException {
        List<Employee> list = ConnectEmployee.readEmployee();

        // Create an ObservableList from the list
        ObservableList<Employee> employeeList = FXCollections.observableArrayList(list);

        // Set the items in the TableView
        tableView.setItems(employeeList);

        // Set the property value factories for each column
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colBirth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
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
    public void adminDelete(ActionEvent event) throws IOException {
        super.adminDelete(event);
    }

    @FXML
    public void adminAdd(ActionEvent event) throws IOException {
        super.adminAdd(event);
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
            display();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}