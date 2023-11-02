package view_controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShipGameGUI extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	
	private GridPane gridPane = new GridPane();
	private VBox vBox = new VBox();
	private Button startButton  = new Button("Start");
	private Label title = new Label("A Game That Allows You To Place Ships And Sink Those Same Ships But Not Yours The Other Guy's");
	private Label credits  = new Label("By: Name, Name, Name, Name");
	private Label blank = new Label(" ");
	
	
	
	public void start(Stage stage) throws Exception {
		stage.setTitle("Goobie Snooble");
		Scene scene = new Scene(gridPane, 500, 500);
		vBox.getChildren().add(credits);
		vBox.getChildren().add(blank);
		vBox.getChildren().add(startButton);
		vBox.getChildren().add(title);
		gridPane.add(vBox, 0,0);
		stage.setScene(scene);
	    stage.show();
		
	}




	
}
