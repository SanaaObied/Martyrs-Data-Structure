package application;

import java.util.Date;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

import application.SortedCircularDoublyLinkedList.LocationNode;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StatisticsScreen extends Stage {
	SingleLLSort s;
	BorderPane bpane = new BorderPane();
	Button nextPage, summaryButton;
	FlowPane flowpane = new FlowPane();
	Stage sta;
	Scene Screen;
	SummaryReportRow summary;
	public static TableView<SummaryReportRow> tableView;

	StatisticsScreen() {

	}

	public Stage getStatisticsScreen() {
		summaryButton = new Button("Generate Summary Report");
		s = Main.locationsList.getFirst().getLocation().getSt();
		summary = new SummaryReportRow(Main.locationsList.getFirst().getLocation()); // create a Statistics object using
																						// the Location object

		TableColumn<SummaryReportRow, Integer> c0 = new TableColumn<>("Location");
		c0.setCellValueFactory(new PropertyValueFactory<>("Location2"));

		TableColumn<SummaryReportRow, Integer> c1 = new TableColumn<>("Age Above 40");
		c1.setCellValueFactory(new PropertyValueFactory<>("above40"));

		TableColumn<SummaryReportRow, Integer> c2 = new TableColumn<>("Numbers of  Male");
		c2.setCellValueFactory(new PropertyValueFactory<>("numMaleMartyrs"));
		TableColumn<SummaryReportRow, Integer> c9 = new TableColumn<>("Numbers of  Female");
		c9.setCellValueFactory(new PropertyValueFactory<>("numFemaleMartyrs"));
		TableColumn<SummaryReportRow, Double> c3 = new TableColumn<>("Age Between 30->70");
		c3.setCellValueFactory(new PropertyValueFactory<>("rangeBrtween37"));

		TableColumn<SummaryReportRow, Integer> c4 = new TableColumn<>("Date That Has The Maximum Number Of Martyrs");
		c4.setCellValueFactory(new PropertyValueFactory<>("dateThatHasTheMaximumNumberOfMartyrs"));
		TableColumn<SummaryReportRow, Integer> c5 = new TableColumn<>("Num Married Martyrs");
		c5.setCellValueFactory(new PropertyValueFactory<>("numMarriedMartyrs"));

		TableColumn<SummaryReportRow, Integer> c6 = new TableColumn<>("Num Single Martyrs");
		c6.setCellValueFactory(new PropertyValueFactory<>("numSingleMartyrs"));


		TableColumn<SummaryReportRow, Integer> c8 = new TableColumn<>("Age Below 50");
		c8.setCellValueFactory(new PropertyValueFactory<>("below50"));

		tableView = new TableView<>();
		tableView.getColumns().addAll(c0,c2,c9 ,c5,c6,c1,c3,c8,c4);
		ObservableList<SummaryReportRow> statsList = FXCollections.observableArrayList();
		LocationNode curr = Main.locationsList.getFirst();
		for (int i = 0; i < Main.locationsList.size(); i++) {
			SummaryReportRow stats = new SummaryReportRow(curr.getLocation());
			statsList.add(stats);
			curr = curr.getNext();
		}
		tableView.setItems(statsList);

		// Add the columns to the TableView
		c0.setPrefWidth(130);

		c2.setPrefWidth(170);
		c3.setPrefWidth(170);

		c5.setPrefWidth(170);
		c6.setPrefWidth(170);
		//c7.setPrefWidth(170);
		c9.setPrefWidth(190);

		BorderPane bpane = new BorderPane(tableView);
		bpane.setTop(summaryButton);

		HBox h = new HBox(10);
		Font fonts = Font.font(25);
		Color color = new Color(0.9, 0.95, 0.90, 1);
		summaryButton.setFont(fonts);

		Image image = new Image("nex.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(70);
		imageView.setFitHeight(70);
		summaryButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18));// type of The Text
		summaryButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		summaryButton.setMaxWidth(300);
		summaryButton.setMaxHeight(100);
		summaryButton.setAlignment(Pos.CENTER);

		// create a button and set its graphic to the image view

		Image image2 = new Image("pri.png");
		ImageView imageView2 = new ImageView(image2);
		imageView2.setFitWidth(70);
		imageView2.setFitHeight(70);

		nextPage = new Button();
		Image image3 = new Image("109-1090359_next-page-button.png");
		ImageView imageView3 = new ImageView(image3);
		nextPage.setGraphic(imageView3);
		imageView3.setFitWidth(70);
		imageView3.setFitHeight(70);
		nextPage.setOnAction(e -> {
			sta.close();
		});

		h.setPadding(new Insets(10, 10, 10, 10));

		h.getChildren().addAll(nextPage);
		h.setAlignment(Pos.CENTER);

		bpane.setBottom(h);

		Screen = new Scene(bpane, 1500, 500);
		sta = new Stage();
		sta.setTitle("Statistics Screen");
		sta.setScene(Screen);
		sta.show();
		return sta;
	}

}