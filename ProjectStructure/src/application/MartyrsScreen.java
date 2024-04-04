package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import application.Main.myEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class MartyrsScreen extends Stage{
	private Button addMartyrButton, updateMartyrButton, deleteMartyrButton, searchMartyrButton, cancel, ng;
	private Label martyrLabel, nameLabel, ageLabel, genderLabel, dateLabel, massege, maritalStatusLabel;
	static TextField nameField, ageField, genderField, dateField, maritalStatusTextField;
	private GridPane g = new GridPane();
	private Button next;
	Stage martry;
	Scene martyrs;
	TextAreaStatistic rScreen;
	reportMat r = new reportMat();
	reportMat r2 = new reportMat();
	TextAreaStatistic sta;

	public Stage getMartyrsScene() {

		ng = new Button();
		Image image2 = new Image("nextst.jpg");
		ImageView imageView2 = new ImageView(image2);
		ng.setGraphic(imageView2);

		ng.setOnAction(e -> {
			Main.locationsList.getFirst().getLocation().getSt().nextSLL();
		});

		martyrLabel = new Label("Martyr Record:");
		nameLabel = new Label("Name:");
		nameField = new TextField();
		ageLabel = new Label("Age:");
		ageField = new TextField();
		genderLabel = new Label("Gender:");
		genderField = new TextField();
		dateLabel = new Label("Date of Death:");
		dateField = new TextField();
		maritalStatusLabel = new Label("Marital Status:");
		maritalStatusTextField = new TextField();
		addMartyrButton = new Button("Add Martyr");
		updateMartyrButton = new Button("Update Martyr");
		deleteMartyrButton = new Button("Delete Martyr");
		searchMartyrButton = new Button("Search Martyr");

		cancel = new Button("Cancel");
		cancel.setOnAction(e -> {
			martry.close();
		});
		massege = new Label("");
		massege.setStyle("-fx-text-fill:red");
		HBox h = new HBox(5);
		g.add(martyrLabel, 1, 0);

		g.add(nameLabel, 0, 1);
		g.add(nameField, 1, 1);

		g.add(ageLabel, 0, 2);
		g.add(ageField, 1, 2);

		g.add(genderLabel, 0, 3);
		g.add(genderField, 1, 3);

		g.add(dateLabel, 0, 4);
		g.add(dateField, 1, 4);

		g.add(maritalStatusLabel, 0, 5);
		g.add(maritalStatusTextField, 1, 5);

		Image image = new Image("109-1090359_next-page-button.png");
		ImageView imageView = new ImageView(image);

		// create a button and set its graphic to the image view
		next = new Button();
		next.setGraphic(imageView);
		imageView.setFitWidth(60);
		imageView.setFitHeight(60);

		next.setMaxWidth(90);// To determine the dimensions of image
		next.setMaxHeight(90);
		next.setAlignment(Pos.BOTTOM_RIGHT);

		h.setAlignment(Pos.BOTTOM_LEFT);
		h.getChildren().addAll(searchMartyrButton, addMartyrButton, updateMartyrButton, deleteMartyrButton, cancel);
		g.add(ng, 1, 8);
		g.add(h, 1, 10);

		g.add(next, 3, 12);

		g.setHgap(10);
		g.setVgap(10);

		nameField.setPrefSize(2, 2);
		genderField.setPrefSize(2, 2);
		ageField.setPrefSize(2, 2);
		dateField.setPrefSize(2, 2);
		maritalStatusTextField.setPrefSize(2, 2);

		searchMartyrButton.setMaxWidth(180);
		searchMartyrButton.setMaxHeight(100);
		updateMartyrButton.setMaxWidth(180);
		updateMartyrButton.setMaxHeight(100);
		addMartyrButton.setMaxWidth(180);
		addMartyrButton.setMaxHeight(100);
		deleteMartyrButton.setMaxWidth(180);
		deleteMartyrButton.setMaxHeight(100);

		genderField.setVisible(false);
		ageField.setVisible(false);
		dateField.setVisible(false);
		maritalStatusTextField.setVisible(false);
		deleteMartyrButton.setOnAction(e -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			  if(nameField.getText().equals("") || nameField.getText()==null) {
				   
			  }else {
				   SingleLLSort.Node now=Main.locationsList.getFirst().getLocation().getSt().getFirst();//.search(nameField.getText()).
						  
				   if(now==null) {
					   
				   }else {
					   LocationScreen.searchednode.getLocation().getSt().remove(now.getMartyr());
				   }
				    System.out.println("Martyr successfully removed.");

			   }
			
		});
		next.setOnAction(e -> {
			rScreen = new TextAreaStatistic();
			rScreen.getReportButtonScreen();
			
			martry.hide();
		});
		searchMartyrButton.setOnAction(e -> {
			Main.locationsList.getFirst().getLocation().getSt().search(nameField.getText());
			genderField.setVisible(true);
			ageField.setVisible(true);
			dateField.setVisible(true);
			maritalStatusTextField.setVisible(true);
			nameField.setText(Main.locationsList.getFirst().getLocation().getSt().getFirst().getMartyr().getName());
			genderField.setText(
					Main.locationsList.getFirst().getLocation().getSt().getFirst().getMartyr().getGender() + " ");
			ageField.setText(Main.locationsList.getFirst().getLocation().getSt().getFirst().getMartyr().getAge() + " ");
			dateField.setText(
					Main.locationsList.getFirst().getLocation().getSt().getFirst().getMartyr().getDateOfDeath() + "");
			maritalStatusTextField.setText(
					Main.locationsList.getFirst().getLocation().getSt().getFirst().getMartyr().getMaritalStatus() + "");
			Main.locationsList.getFirst().getLocation().getSt().printList();
			System.out.println("Doneeeeeeeeeee");

		});
		updateMartyrButton.setOnAction(e -> {
			r.getupdate();
		});
		addMartyrButton.setOnAction(e -> {
			r2.getAdd();
		});
		maritalStatusLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		martyrLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text
		nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		ageLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		genderLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));// type of The Text
		dateLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));
		searchMartyrButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		addMartyrButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		updateMartyrButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		deleteMartyrButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
		cancel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));

		martyrLabel.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides
		nameLabel.setPadding(new Insets(10, 15, 10, 10));
		ageLabel.setPadding(new Insets(10, 15, 10, 10));
		genderLabel.setPadding(new Insets(10, 15, 10, 10));
		dateLabel.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides

		imageView2.setFitWidth(60);
		imageView2.setFitHeight(60);
		imageView2.setFitHeight(60);

		searchMartyrButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		cancel.setStyle("-fx-background-color: white");// To determine the Color background for Button
		addMartyrButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		updateMartyrButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		deleteMartyrButton.setStyle("-fx-background-color: white");// To determine the Color background for Button

		g.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		g.setAlignment(Pos.CENTER);
		martyrs = new Scene(g, 900, 600);
		martry = new Stage();
		martry.setTitle("Martyrs Screen");// There is a title for the window
		martry.setScene(martyrs);
		martry.show();
		return martry;

	}
}