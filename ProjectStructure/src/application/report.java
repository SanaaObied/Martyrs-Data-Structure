package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class report extends Stage{
	private Text exportfile = new Text(" Click Export to Use a file chooser to select the folder to save the file in :");
	private Button export = new Button("Export");
	private Button back;
	private GridPane g = new GridPane();
	Scene reportScreen ;
	Stage repSage;


	public Stage getReport() {
	        g.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
			g.setPadding(new Insets(15, 15, 15, 15));
			exportfile.setFont(Font.font("Verdana", FontWeight.BLACK, FontPosture.REGULAR,12));
			 back=new Button("Main Page");
			 Font fonts=Font.font(25);
				Color color =new Color(0.75,0.125,0.50,1);
				back.setFont(fonts);
				export.setFont(fonts);
				back.setOnAction(e->{
					repSage.close();
				});
			g.add(exportfile, 0, 2);
			
        HBox h=new HBox(10);
        h.getChildren().addAll(export,back);
			g.add(h, 0, 4);
			h.setAlignment(Pos.CENTER);
			export.setMaxWidth(130);
			export.setMaxHeight(50);
			back.setMaxWidth(170);
			back.setMaxHeight(50);
			g.setHgap(30);
			g.setVgap(30);
			back.setStyle("-fx-background-color: white");
			export.setStyle("-fx-background-color: white");
			export.setOnAction(e->{
				Main.locationsList.saveToFile();
			});
	         g.setAlignment(Pos.CENTER);
			g.setPadding(new Insets(15, 15, 15, 15));
		    reportScreen = new Scene(g, 600, 300);
			repSage = new Stage();
			repSage.setTitle("Save Screen");//the title of the window.
			repSage.setScene(reportScreen);
			repSage.show();
			return repSage;
	}
			
}
	