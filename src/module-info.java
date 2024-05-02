module AAA {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
<<<<<<< HEAD
	requires com.microsoft.sqlserver.jdbc;
=======
	requires fontawesomefx;
>>>>>>> b6599124cc9e14729901aff54abd0b19b587e1f6
	//requires com.microsoft.sqlserver.jdbc;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics,javafx.fxml;
	opens connection to javafx.graphics,javafx.fxml;
	opens model to javafx.graphics,javafx.fxml,javafx.base;
	opens controllerE to javafx.graphics,javafx.fxml,javafx.base;

	opens image to javafx.graphics,javafx.fxml,javafx.base;
}
