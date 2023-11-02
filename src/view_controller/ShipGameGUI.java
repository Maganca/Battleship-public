package view_controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Board;

public class ShipGameGUI extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	private BorderPane borderPane = new BorderPane();
	private GridPane gridPane = new GridPane();
	private VBox vBox = new VBox();
	private Button startButton  = new Button("Start");
	private Label title = new Label("A Game That Allows You To Place Ships And Sink Those Same Ships But Not Yours The Other Guy's");
	private Label credits  = new Label("By: Name, Name, Name, Name");
	private Button[][] playerGrid = new Button[10][10]; 
	private	Button[][] AIGrid = new Button[10][10];
	private GridPane playerGridPane = new GridPane();
	private GridPane AIGridPane = new GridPane();
	public void start(Stage stage) throws Exception {
		stage.setTitle("Goobie Snooble");
		layoutGUI(stage);
		registerHandlers();
		
		
	}
	private void layoutGUI(Stage stage) {
		Scene scene = new Scene(borderPane, 500, 500);
		gridPane.add(title, 0, 0);
		gridPane.add(startButton, 0, 1);
		startButton.setAlignment(Pos.CENTER);
		gridPane.add(credits, 0, 2);
		borderPane.setCenter(gridPane); 
		gridPane.setAlignment(Pos.CENTER);
		title.setAlignment(Pos.CENTER);
		title.setWrapText(true);
		credits.setAlignment(Pos.CENTER);
		stage.setScene(scene);
	    stage.show();
	}
	private void registerHandlers() {
		startButton.setOnAction(e -> {
			for (int i = 0; i <10; i++) {
				for (int j = 0; j < 10;j++) {
					playerGrid[i][j] = new Button();
					playerGridPane.add(playerGrid[i][j], i, j);
					
					AIGrid[i][j] = new Button();
					AIGridPane.add(playerGrid[i][j], i, j);
				}
			}
			borderPane.setTop(playerGridPane);
			borderPane.setCenter(AIGridPane);
			
		});

	}
	
}
