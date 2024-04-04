package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class reportMat extends Stage {
	static TextField nameField, ageField, datField, genderField, maritalField;
	private Label namelab, agelab, datlab, genderlab, maritallab;

	static TextField nameField2, ageField2, datField2, genderField2, maritalField2;
	private Label namelab2, agelab2, datlab2, genderlab2, maritallab2;

	private Label addLabel, updateLabel, deleteLabel;
	private Stage add, delete, update;
	private Button cancel,cancel2;
	private GridPane g = new GridPane();
	private GridPane g1 = new GridPane();
	public void getAdd() {
		Button added=new Button("Add");
		addLabel = new Label("Add Martyr");
		namelab = new Label("Name:");
		nameField = new TextField();
		agelab = new Label("Age:");
		ageField = new TextField();
		genderlab = new Label("Gender:");
		genderField = new TextField();
		datlab = new Label("Date of Death:");
		datField = new TextField();
		maritallab = new Label("Marital Status:");
		maritalField = new TextField();

		cancel = new Button("Cancel");

		g.add(addLabel, 1, 0);

		g.add(namelab, 0, 1);
		g.add(nameField, 1, 1);

		g.add(agelab, 0, 2);
		g.add(ageField, 1, 2);

		g.add(genderlab, 0, 3);
		g.add(genderField, 1, 3);

		g.add(datlab, 0, 4);
		g.add(datField, 1, 4);

		g.add(maritallab, 0, 5);
		g.add(maritalField, 1, 5);

		g.add(cancel, 1, 8);
		g.add(added, 2, 8);
		g.setHgap(5);
		g.setVgap(5);

		nameField.setPrefSize(2, 2);
		genderField.setPrefSize(2, 2);
		ageField.setPrefSize(2, 2);
		datField.setPrefSize(2, 2);
		maritalField.setPrefSize(2, 2);

		cancel.setMaxWidth(80);
		cancel.setMaxHeight(80);
		addLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text
		namelab.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		agelab.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		genderlab.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		datlab.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		maritallab.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		cancel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));

		namelab.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		agelab.setPadding(new Insets(10, 15, 10, 10));
		genderlab.setPadding(new Insets(10, 15, 10, 10));
		datlab.setPadding(new Insets(10, 15, 10, 10));
		maritallab.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides

		cancel.setStyle("-fx-background-color: white");// To determine the Color background for Button
		cancel.setOnAction(e -> {
			  add.close();
		});
		added.setOnAction(e ->{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocationScreen.searchednode.getLocation().getSt().insert(new Martyr(nameField.getText(), Integer.parseInt(ageField.getText()), LocalDate.parse(datField.getText(),formatter), Gender.fromCode(genderField.getText().charAt(0)), MaritalStatus.valueOf(maritalField.getText()),LocationScreen.searchednode.getLocation().getName()));
			System.out.println("SUCCSFIL");
		});
		g.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		g.setAlignment(Pos.CENTER);
		Scene s = new Scene(g, 350, 300);
		add = new Stage();
		add.setTitle("Add Martyr Screen");// There is a title for the window
		add.setScene(s);
		add.show();
		

	}
	public void getupdate() {
		updateLabel = new Label("Update Martyr");
		namelab2 = new Label("Name:");
		nameField2 = new TextField();
		agelab2 = new Label("Age:");
		ageField2 = new TextField();
		genderlab2 = new Label("Gender:");
		genderField2 = new TextField();
		datlab2 = new Label("Date of Death:");
		datField2 = new TextField();
		maritallab2 = new Label("Marital Status:");
		maritalField2 = new TextField();

		cancel2 = new Button("Cancel");
		Button upd=new Button("Update");
		g1.add(updateLabel, 1, 0);

		g1.add(namelab2, 0, 1);
		g1.add(nameField2, 1, 1);

		g1.add(agelab2, 0, 2);
		g1.add(ageField2, 1, 2);

		g1.add(genderlab2, 0, 3);
		g1.add(genderField2, 1, 3);

		g1.add(datlab2, 0, 4);
		g1.add(datField2, 1, 4);

		g1.add(maritallab2, 0, 5);
		g1.add(maritalField2, 1, 5);

		g1.add(cancel2, 1, 8);
		g1.add(upd, 2, 8);
		g1.setHgap(5);
		g1.setVgap(5);

		nameField2.setPrefSize(2, 2);
		genderField2.setPrefSize(2, 2);
		ageField2.setPrefSize(2, 2);
		datField2.setPrefSize(2, 2);
		maritalField2.setPrefSize(2, 2);

		cancel2.setMaxWidth(70);
		cancel2.setMaxHeight(70);
		updateLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text
		namelab2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		agelab2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		genderlab2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		datlab2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		maritallab2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		cancel2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));

		namelab2.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		agelab2.setPadding(new Insets(10, 15, 10, 10));
		genderlab2.setPadding(new Insets(10, 15, 10, 10));
		datlab2.setPadding(new Insets(10, 15, 10, 10));
		maritallab2.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides

		cancel2.setStyle("-fx-background-color: white");// To determine the Color background for Button
		cancel2.setOnAction(e->{
			
			    update.close();
		});
		upd.setOnAction(e ->{
		
		
			        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			      
			    Main.locationsList.getFirst().getLocation().getSt().update(
			    		MartyrsScreen.nameField.getText(), new Martyr(nameField2.getText(), Integer.parseInt(ageField2.getText()),LocalDate.parse(datField2.getText(),formatter2),
			                    Gender.valueOf(genderField2.getText()),
			                    MaritalStatus.valueOf(maritalField2.getText()),Main.locationsList.getFirst().getLocation().getName()));
			    System.out.println("Sucsseffly");
		});
		g1.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		g1.setAlignment(Pos.CENTER);
		Scene s = new Scene(g1, 370, 300);
		update = new Stage();
		update.setTitle("UpdateMartyrs Screen");// There is a title for the window
		update.setScene(s);
		update.show();
		
	}

}