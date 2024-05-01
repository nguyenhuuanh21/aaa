package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import connection.ConnectEmployee;
import connection.connectDepartment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
<<<<<<< HEAD
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Department;
import model.Employee;


public class ListDepartMentController extends Controller implements  Initializable{

    @FXML
    private TableColumn<Department,String> Id;

    @FXML
    private TableColumn<Department, String> Name;

    @FXML
    private TableColumn<Department, Integer> Quantity;
    @FXML
    private TableView<Department> tableView;
    @FXML
    private Button menu_calendar;

    @FXML
    private Button menu_department;

    @FXML
    private Button menu_edit;

    @FXML
    private Button menu_logout;

    @FXML
    private Button menu_report;

    @FXML
    private Button menu_setting;

=======
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ListDepartMentController extends Controller {
	
>>>>>>> c8632315fe63e56ccec33498861616795dd76c6e
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
	 public void display() throws ClassNotFoundException, SQLException {
	        List<Department> list = connectDepartment.readDepartment();

	        // Create an ObservableList from the list
	        ObservableList<Department> departmentList = FXCollections.observableArrayList(list);

	        // Set the items in the TableView
	        tableView.setItems(departmentList);

	        // Set the property value factories for each column
	        Id.setCellValueFactory(new PropertyValueFactory<>("department_id"));
	        Name.setCellValueFactory(new PropertyValueFactory<>("department_name"));
	        Quantity.setCellValueFactory(new PropertyValueFactory<>("dpartment_quanity"));
	   
	   }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			display();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
