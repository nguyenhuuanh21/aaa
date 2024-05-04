package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.List;
import connection.ConnectEmployee;
import connection.connectDepartment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
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
    private TableColumn<Employee, String> colEdit;

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
        Callback<TableColumn<Employee, String>, TableCell<Employee, String>> cellFactory = (
                TableColumn<Employee, String> param) -> {
            // Make cell containing buttons
            final TableCell<Employee, String> cell = new TableCell<Employee, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // This cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button deleteButton = new Button("del");
                        Button editButton = new Button("edit");
                        

                        deleteButton.setOnAction(event -> {
                            Employee employee = getTableView().getItems().get(getIndex());
                            int eId = Integer.parseInt(employee.getId());
                            
                            Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
                            confirmAlert.setTitle("Confirm Delete");
                            confirmAlert.setHeaderText(null);
                            confirmAlert.setContentText("Are you sure you want to delete employee: " + employee.getName() + "?");
                            
                            // Show the confirmation dialog and wait for user response
                            ButtonType confirmResult = confirmAlert.showAndWait().orElse(ButtonType.CANCEL);
                            
                            if (confirmResult == ButtonType.OK) {
                                int n = ConnectEmployee.deleteEmployeeById(eId);

                                Alert resultAlert = new Alert(AlertType.INFORMATION);
                                resultAlert.setTitle("Delete Employee");

                                if (n > 0) {
                                    resultAlert.setHeaderText(null);
                                    resultAlert.setContentText("Delete employee: " + employee.getName());
                                    connectDepartment.updateQuantityEmployee();
                                } else {
                                    resultAlert.setHeaderText(null);
                                    resultAlert.setContentText("Delete employee failed");
                                }

                                resultAlert.showAndWait();
                            }
                        });
                        editButton.setOnAction(event -> {
                            Employee employee = getTableView().getItems().get(getIndex());
                            // Implement edit functionality here
                            System.out.println("Edit employee: " + employee.getName());
                        });
                        HBox manageBtn = new HBox(editButton, deleteButton);
                        manageBtn.setStyle("-fx-alignment: center");
                        HBox.setMargin(deleteButton, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editButton, new Insets(2, 3, 0, 2));

                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        colEdit.setCellFactory(cellFactory);
    }

    public void adminHome(ActionEvent event) throws IOException {
        super.adminHome(event);
    }

    public void adminEmployees(ActionEvent event) throws IOException {
        super.adminEmployees(event);
    }

    public void adminDepartment(ActionEvent event) throws IOException {
        super.adminDepartment(event);
    }

    public void setting(ActionEvent event) throws IOException {
        super.adminMyAccount(event);
    }

    public void logout(ActionEvent event) throws IOException {
        super.logout(event);
    }

    public void calendar(ActionEvent event) throws IOException {
        super.calendar(event);
    }

    public void adminReport(ActionEvent event) throws IOException {
        super.adminReport(event);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
        	displayName();
            display();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}