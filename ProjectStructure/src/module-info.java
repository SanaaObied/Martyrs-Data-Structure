module ProjectStructure {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base,javafx.beans.property.SimpleStringProperty;
}
