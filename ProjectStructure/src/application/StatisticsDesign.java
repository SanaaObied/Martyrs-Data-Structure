package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StatisticsDesign extends Stage {

	SingleLLSort s = Main.locationsList.getFirst().getLocation().getSt();
	BorderPane bpane = new BorderPane();
	Button next, previous, nextPage, summaryButton, saveButton, loadButton, showDataButton;
	FlowPane flowpane = new FlowPane();
	Stage sta;
	Scene Screen;
	report r;
	Statistics statistics;
	public static TableView<Statistics> tableView;

	StatisticsDesign() {

	}

	public Stage getStatisticsDesign() {
		s = Main.locationsList.getFirst().getLocation().getSt();
		statistics = new Statistics(Main.locationsList.getFirst().getLocation()); // create a Statistics object using
																					// the Location object
		summaryButton = new Button("Generate Summary Report");
		TableColumn<Statistics, Integer> c0 = new TableColumn<>("Location");
		c0.setCellValueFactory(new PropertyValueFactory<>("Location2"));

		TableColumn<Statistics, Integer> c1 = new TableColumn<>("Numbers Of Martyrs By Age");
		c1.setCellValueFactory(new PropertyValueFactory<>("numbersOfMartyrsByAge"));

		TableColumn<Statistics, Integer> c2 = new TableColumn<>("Number Of Martyrs By Gender");
		c2.setCellValueFactory(new PropertyValueFactory<>("numberOfMartyrsByGender"));
		TableColumn<Statistics, Double> c3 = new TableColumn<>("Average Age Of Martyrs");
		c3.setCellValueFactory(new PropertyValueFactory<>("averageAgeOfMartyrs"));

		TableColumn<Statistics, Integer> c4 = new TableColumn<>("Date That Has The Maximum Number Of Martyrs");
		c4.setCellValueFactory(new PropertyValueFactory<>("dateThatHasTheMaximumNumberOfMartyrs"));

		tableView = new TableView<>();
		// Add the columns to the TableView
		tableView.getColumns().addAll(c0, c1, c2, c3, c4);
		ObservableList<Statistics> statsList = FXCollections.observableArrayList();
		for (String loc : ((Location) Main.locationsList.getFirst().getLocation()).getNameList()) {
			Location loc2 = new Location(loc);
			Statistics stats = new Statistics(loc2);
			statsList.add(stats);
		}
		tableView.setItems(statsList);

		// Add the columns to the TableView
		c0.setPrefWidth(200);
		c1.setPrefWidth(200);
		c2.setPrefWidth(200);
		c3.setPrefWidth(200);
		c4.setPrefWidth(350);

		BorderPane bpane = new BorderPane(tableView);
		bpane.setTop(summaryButton);
		next = new Button("Next");
		next.setMaxWidth(170);

		previous = new Button("Previous");
		previous.setMaxWidth(170);
		// bpane.setCenterShape(false);
		HBox h = new HBox(10);
		Font fonts = Font.font(25);
		Color color = new Color(0.9, 0.95, 0.90, 1);
		summaryButton.setFont(fonts);
		next.setFont(fonts);
		previous.setFont(fonts);
		Image image = new Image("nex.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(70);
		imageView.setFitHeight(70);
		summaryButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text
		summaryButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		summaryButton.setMaxWidth(300);
		summaryButton.setMaxHeight(100);
		summaryButton.setAlignment(Pos.CENTER);

		next = new Button();
		next.setGraphic(imageView);
		Image image2 = new Image("pri.png");
		ImageView imageView2 = new ImageView(image2);
		imageView2.setFitWidth(70);
		imageView2.setFitHeight(70);

		// create a button and set its graphic to the image view
		previous = new Button();
		previous.setGraphic(imageView2);

		populateTableView(Main.locationsList.getFirst().getLocation().getName(), tableView);
		next.setOnAction(e -> {
			Main.locationsList.forward();
			ObservableList<Statistics> newStatsList = FXCollections.observableArrayList();
			for (String loc : ((Location) Main.locationsList.getFirst().getLocation()).getNameList()) {
				Location loc2 = new Location(loc);
				Statistics stats = new Statistics(loc2);
				statsList.add(stats);
			}
			tableView.setItems(statsList);
			tableView.setItems(newStatsList);
		});
		previous.setOnAction(e -> {
			Main.locationsList.backwardButtonActionPerformed();
			ObservableList<Statistics> newStatsList = FXCollections.observableArrayList();
			for (String loc : ((Location) Main.locationsList.getFirst().getLocation()).getNameList()) {
				Location loc2 = new Location(loc);
				Statistics stats = new Statistics(loc2);
				statsList.add(stats);
			}

			tableView.setItems(newStatsList);
		});
		nextPage = new Button();
		Image image3 = new Image("109-1090359_next-page-button.png");
		ImageView imageView3 = new ImageView(image3);
		nextPage.setGraphic(imageView3);
		imageView3.setFitWidth(70);
		imageView3.setFitHeight(70);

		nextPage.setOnAction(e -> {
			sta.close();
			r = new report();
			r.getReport();
		});
		showDataButton = new Button("Show Data");
		next.setMaxWidth(230);
		previous.setMaxWidth(230);
		h.setPadding(new Insets(10, 10, 10, 10));
		showDataButton.setOnAction(e -> {

			tableView.setItems(statsList);

		});
		h.getChildren().addAll(showDataButton, previous, next, nextPage);
		h.setAlignment(Pos.CENTER);
		next.setStyle("-fx-background-color: white");// To determine the Color background for every button
		previous.setStyle("-fx-background-color: white");

		bpane.setBottom(h);

		Screen = new Scene(bpane, 1200, 600);
		sta = new Stage();
		sta.setTitle("Statistics Screen");
		sta.setScene(Screen);
		sta.show();
		return sta;
	}

	private void populateTableView(String string, TableView<Statistics> tableView) {
		ObservableList<Statistics> statsList = FXCollections.observableArrayList();
		for (String loc : ((Location) Main.locationsList.getFirst().getLocation()).getNameList()) {
			Location loc2 = new Location(loc);
			Statistics stats = new Statistics(loc2);
			statsList.add(stats);
		}
		tableView.setItems(statsList);

	}

}