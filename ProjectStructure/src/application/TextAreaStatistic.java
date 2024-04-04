package application;

import java.awt.TextArea;

import application.SortedCircularDoublyLinkedList.LocationNode;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextAreaStatistic {
	private TextArea summary;
	private Button next = new Button("Next");
	private Button prev = new Button("Previous");
	private Button nextPage;
	private BorderPane g = new BorderPane();
	private Stage repStage;
	private report r;
	private int currentLocationIndex = 0;
	
	public TextAreaStatistic() {
		g.setStyle("-fx-background-color:#D3D3D3"); // Set the background color
		Text title = new Text("Summary Report");

		summary = new TextArea();
		nextPage = new Button();
		ImageView imageView = new ImageView("109-1090359_next-page-button.png");
		nextPage.setGraphic(imageView);
		imageView.setFitWidth(60);
		imageView.setFitHeight(60);
		nextPage.setOnAction(e -> {
			repStage.close();
			r = new report();
			r.getReport();
		});
		ImageView imageView2 = new ImageView("nex.png");
		// Create a button and set its graphic to the image view
		next = new Button();
		next.setGraphic(imageView2);
		imageView2.setFitWidth(60);
		imageView2.setFitHeight(60);
		next.setOnAction(e -> {
			 forward();
			 updateTextAreaWithSummaryReport();
			
		}

		);

		ImageView imageView3 = new ImageView("pri.png");
		// Create a button and set its graphic to the image view
		prev = new Button();
		prev.setGraphic(imageView3);
		imageView3.setFitWidth(60);
		imageView3.setFitHeight(60);
		prev.setOnAction(e -> {
			 backward();
			 updateTextAreaWithSummaryReport();

		});
		/*Main.locationsList.getFirst().getLocation().generateSummaryReport2();
		summary.setText(Location.sb.toString());*/
		/*if (!LocationScreen.locationField.getText().isEmpty()) {
			LocationNode currentNode = Main.locationsList.searchLocationNode3(LocationScreen.locationField.getText());
			if (currentNode != null) {
				currentLocationIndex = getNodeIndex(currentNode);
				updateTextAreaWithSummaryReport();
			}
		}*/
		HBox h = new HBox(10);
		h.getChildren().addAll(prev, next, nextPage);

		g.setTop(title);
		initializeSummaryText();
		
		/*StringProperty summaryTextProperty = new SimpleStringProperty();
		summaryTextProperty.addListener((observable, oldValue, newValue) -> {
			summary.setText(newValue);
		});*/
		//updateTextAreaWithSummaryReport();
		Text summaryText = new Text();
		//summaryText.textProperty().bind(summaryTextProperty);

		Group textGroup = new Group(summaryText);
		summaryText.setText(summary.getText());
		//summaryText.setText(Location.sb.toString());
		ScrollPane scrollPane = new ScrollPane(textGroup);
		scrollPane.setPrefHeight(10);
		scrollPane.setPrefViewportWidth(10);
		g.setCenter(scrollPane);
		g.setBottom(h);
	}

	private void updateTextAreaWithSummaryReport() {
		  if (currentLocationIndex >= 0 && currentLocationIndex < Main.locationsList.size()) {
		        Location currentLocation = Main.locationsList.getLocation(currentLocationIndex);
		        if (currentLocation != null) {
		            String summaryReport = currentLocation.generateSummaryReport2();
		            summary.setText(summaryReport);
		        }
		    }
	}

	public void forward() {
		if (!LocationScreen.locationField.getText().isEmpty()) {
			LocationNode currentNode = Main.locationsList.searchLocationNode3(LocationScreen.locationField.getText());
			if (currentNode != null && currentNode.getNext() != null) {
				LocationNode nextNode = currentNode.getNext();
				Main.locationsList.forward();
				LocationScreen.locationField.setText(nextNode.getLocation().getName());
				currentLocationIndex = getNodeIndex(nextNode);
				updateTextAreaWithSummaryReport();
			}
		}
	}

	private int getNodeIndex(LocationNode node) {
		int index = 0;
		LocationNode current = Main.locationsList.getFirst();
		while (current != null) {
			if (current == node) {
				return index;
			}
			current = current.getNext();
			index++;
		}
		return -1; // Node not found
	}

	public void backward() {
		if (!LocationScreen.locationField.getText().isEmpty()) {
			LocationNode currentNode = Main.locationsList.searchLocationNode3(LocationScreen.locationField.getText());
			if (currentNode != null && currentNode.getPrev() != null) {
				LocationNode prevNode = currentNode.getPrev();
				Main.locationsList.backwardButtonActionPerformed();
				LocationScreen.locationField.setText(prevNode.getLocation().getName());
				currentLocationIndex = getNodeIndex(prevNode);
				updateTextAreaWithSummaryReport();
			}
		}
	}
	 public  void initializeSummaryText() {
	        if (!LocationScreen.locationField.getText().isEmpty()) {
	            LocationNode currentNode = Main.locationsList.searchLocationNode3(LocationScreen.locationField.getText());
	            if (currentNode != null) {
	                currentLocationIndex = getNodeIndex(currentNode);
	                updateTextAreaWithSummaryReport();
	            }
	        }
	    }
	public Stage getReportButtonScreen() {
		Scene reportScreen = new Scene(g, 600, 500);
		repStage = new Stage();
		repStage.setTitle("Summary Report");
		repStage.setScene(reportScreen);
		repStage.show();
		return repStage;
	}
}