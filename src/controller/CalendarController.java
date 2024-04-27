package controller;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class CalendarController extends Controller implements Initializable {
	
	ZonedDateTime dateFocus;
    ZonedDateTime today;
    
    @FXML
    private Label calendar_month;

    @FXML
    private Label calendar_year;

    @FXML
    private FlowPane calendar_date;
    
    @FXML
    public void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar_date.getChildren().clear();
        drawCalendar();
    }

    @FXML
    public void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar_date.getChildren().clear();
        drawCalendar();
    }
    
    
    public void drawCalendar() {
    	calendar_month.setText(String.valueOf(dateFocus.getYear()));
    	calendar_year.setText(String.valueOf(dateFocus.getMonth()));
    	double calendarWidth = calendar_date.getPrefWidth();
        double calendarHeight = calendar_date.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar_date.getHgap();
        double spacingV = calendar_date.getVgap();
        
        List<Integer> list = getCalendarActivitiesMonth(dateFocus);
        int monthMaxDate = dateFocus.getMonth().maxLength();
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();
        for(int i = 0 ;i < 6;i++) {
        	for(int j = 0 ;j < 7;j++) {
        		StackPane stackPane = new StackPane();
        		Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.GRAY);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);
                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        if(list.contains(currentDate)){
                            VBox calendarActivityBox = new VBox();
                            calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
                            calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
                            calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
                            calendarActivityBox.setStyle("-fx-background-color:LIME");
                            stackPane.getChildren().add(calendarActivityBox);
                        }
                        
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar_date.getChildren().add(stackPane);
        	}
        }
    }

    private List<Integer> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int randomDay = random.nextInt(27) + 1;
            list.add(randomDay);
        }

        return list;
    }

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
	}

}