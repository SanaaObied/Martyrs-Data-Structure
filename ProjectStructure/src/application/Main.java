package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import application.SortedCircularDoublyLinkedList.LocationNode;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	// Buttons
	Button recbtn = new Button("Export Location");
	Button reptbtn = new Button("Statistics Report");
	Button LoadFile = new Button("Load File");
	report r;
	Stage s = new Stage();
	// List to store locations
	public static SortedCircularDoublyLinkedList locationsList;
	LocationScreen LocationScene;
	StatisticsScreen sta;

	@Override
	public void start(Stage primaryStage) {
		try {

			locationsList = new SortedCircularDoublyLinkedList();
			// Create the main window
			Scene scene = new Scene(getMainPage(), 700, 650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			// Add event handlers to buttons
			reptbtn.addEventHandler(ActionEvent.ACTION, new myEvent());
			recbtn.addEventHandler(ActionEvent.ACTION, new myEvent());
			LoadFile.addEventHandler(ActionEvent.ACTION, new myEvent());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to create the main page layout
	private VBox getMainPage() {
		HBox h = new HBox(10);
		VBox spane = new VBox(15);
		Label title = new Label("Project [1]");// The Main Screen title

		Font f = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 35);// type of The Text Main Screen
																						// title
		title.setFont(f);
		title.setPadding(new Insets(15, 15, 15, 15));
		title.setAlignment(Pos.TOP_CENTER);// to put title in Top of Center
		ImageView image2 = new ImageView(new Image("martyr.png"));

		Font fonts = Font.font(25);
		Color color = new Color(0.75, 0.125, 0.50, 1);

		recbtn.setFont(fonts);
		reptbtn.setFont(fonts);
		LoadFile.setFont(fonts);

		recbtn.setTextFill(color);
		reptbtn.setTextFill(color);
		LoadFile.setTextFill(color);

		reptbtn.setMaxWidth(230);
		LoadFile.setMaxWidth(200);
		recbtn.setMaxWidth(230);

		image2.setFitWidth(400);
		image2.setFitHeight(400);

		recbtn.setDisable(true);
		reptbtn.setDisable(true);

		h.setAlignment(Pos.CENTER);
		recbtn.setStyle("-fx-background-color: white");// To determine the Color background for every button
		reptbtn.setStyle("-fx-background-color: white");// To determine the Color background for every button
		LoadFile.setStyle("-fx-background-color: white");// To determine the Color background for every button
		spane.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		h.getChildren().addAll(LoadFile, recbtn, reptbtn);
		spane.setAlignment(Pos.CENTER);
		spane.getChildren().addAll(title, image2, h);

		return spane;
	}

	// Method to handle the "Load File" button action
	public void LoadFile() {
		// Open file chooser dialog
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Martyrs File");
		File file = chooser.showOpenDialog(s);
		if (file != null) {
			String name = "";
			int age = 0;
			String district = "";
			String maritalStatusString="";
			LocalDate dateOfDeath = null;
			Gender gender = null;
			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNextLine()) {
					// Read each line of the file
					String line = scanner.nextLine();
					Scanner lineScanner = new Scanner(line);
					lineScanner.useDelimiter(",");
					if (lineScanner.hasNext()) {
						name = lineScanner.next();
					}
					if (lineScanner.hasNextInt()) {
						age = lineScanner.nextInt();

					}
					if (lineScanner.hasNext()) {
						district = lineScanner.next();

					}
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
					if (lineScanner.hasNext()) {
						dateOfDeath = LocalDate.parse(lineScanner.next(), formatter);

					}
					if (lineScanner.hasNext()) {
						char code = lineScanner.next().charAt(0);
						gender = Gender.fromCode(code);

					}
					if (lineScanner.hasNext()) {
					maritalStatusString = lineScanner.next().toUpperCase();}
						// Search for the location node
						LocationNode locationNode = locationsList.searchLocationNode3(district);
						if (locationNode == null) {
							// Create a new location if it doesn't exist
							Location location = new Location(district);
							locationsList.insertLocationNode(location);
							locationNode = locationsList.searchLocationNode3(district);
						}
						// Create a new martyr with the extracted information and insert it into the
						// location's sorted list
						Martyr martyr = new Martyr(name, age, dateOfDeath, gender,
								MaritalStatus.valueOf(maritalStatusString), locationNode.getLocation().getName());
						locationNode.getLocation().getSt().insert(martyr);
					
					locationsList.print();
				}
			} catch (FileNotFoundException e) {
				// Display an error message if the file is not found
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("File Not Found");
				alert.setHeaderText(null);
				alert.setContentText("The selected file was not found.");
				alert.showAndWait();
			} catch (IOException e) {
				// Display an error message if there is an error reading the file
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Reading File");
				alert.setHeaderText(null);
				alert.setContentText("An error occurred while reading the file.");
				alert.showAndWait();
			}
		}
	}

	class myEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (((Button) event.getSource()).getText().equals("Export Location")) {
				LocationScene = new LocationScreen();
				LocationScene.getLocationScreen();
				LocationScene.setLocationField(locationsList.getFirst().getLocation().getName());
			} else if (((Button) event.getSource()).getText().equals("Statistics Report")) {
				sta = new StatisticsScreen();

				sta.getStatisticsScreen();
				StatisticsScreen.tableView.refresh();

			} else if (((Button) event.getSource()).getText().equals("Load File")) {
				LoadFile();

				recbtn.setDisable(false);
				reptbtn.setDisable(false);
			}
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
