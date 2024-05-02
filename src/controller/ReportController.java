package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ReportController extends Controller implements Initializable {

    @FXML
    private VBox report;

    private int currentPage = 1;
    private int totalPage = 20; // số truy report / 5

    @FXML
    private void previous(ActionEvent event) {
        if (currentPage > 1) {
            currentPage--;
            display(currentPage);
        }
    }

    @FXML
    private void next(ActionEvent event) {
        if (currentPage < totalPage/5) {
            currentPage++;
            display(currentPage);
        }
    }

    private void display(int page) {
        report.getChildren().clear();
        int start = (page - 1) * 5 + 1;
        double labelWidth = 800; 
        double labelHeight = 160; 
        for (int i = start; i <= Math.min(start + 4, totalPage); i++) {
            Label label = new Label("Comment " + i);
            label.setAlignment(Pos.TOP_LEFT);
            label.setPrefWidth(labelWidth);
            label.setPrefHeight(labelHeight);
            label.setFont(new Font(16));
            label.setWrapText(true); 
            label.setStyle("-fx-padding: 5;");
            report.getChildren().add(label);
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
        // Initialize totalPage here based on the total number of reports
        //totalPage = getTotalPageCount(); // You need to implement this method
        display(currentPage);
        displayName();
    }
}
