package view_controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import model.Ship;


public class ShipGameGUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private BorderPane borderPane = new BorderPane();
	private GridPane gridPane = new GridPane();
	private VBox vBox = new VBox();
	private Button startButton = new Button("Start");
	private Label title = new Label(
			"A Game That Allows You To Place Ships And Sink Those Same Ships But Not Yours The Other Guy's");
	private Label credits = new Label("By: Name, Name, Name, Name");
	private Label AILabel = new Label("CPU's Board");
	private Label playerLabel = new Label("Player's Board");

	private Button[][] playerGrid = new Button[10][10];
	private Button[][] AIGrid = new Button[10][10];
	private GridPane playerGridPane = new GridPane();
	private GridPane AIGridPane = new GridPane();

	public Board playerBoard = new Board(10);
	public Board AIBoard = new Board(10);

	public void start(Stage stage) throws Exception {
		stage.setTitle("Goobie Snooble");
		layoutGUI(stage);
		
		registerHandlers();

	}

	private void layoutGUI(Stage stage) {
		Scene scene = new Scene(vBox, 500, 700);
		gridPane.add(title, 0, 0);
		gridPane.add(startButton, 0, 1);
		gridPane.add(credits, 0, 2);
		vBox.getChildren().add(gridPane);
		gridPane.setAlignment(Pos.CENTER);
		title.setAlignment(Pos.CENTER);
		title.setWrapText(true);
		credits.setAlignment(Pos.CENTER);
		startButton.setAlignment(Pos.CENTER);

		stage.setScene(scene);
		stage.show();
	}
	private void setupBoard() {

		int[][] twoShipCoords  = new int[2][2];
		twoShipCoords[0][0] = 1;
		twoShipCoords[0][1] = 1;
		twoShipCoords[1][0] = 1;
		twoShipCoords[1][1] = 2;
		Ship twoShip = new Ship(2);
		playerBoard.addShipToBoard(twoShip, twoShipCoords);
		AIBoard.addShipToBoard(twoShip, twoShipCoords);

		int[][] threeShipACoords  = new int[3][2];
		threeShipACoords[0][0] = 2;
		threeShipACoords[0][1] = 1;
		threeShipACoords[1][0] = 2;
		threeShipACoords[1][1] = 2;
		threeShipACoords[2][0] = 2;
		threeShipACoords[2][1] = 3;

		Ship threeShipA = new Ship(3);
		playerBoard.addShipToBoard(threeShipA, threeShipACoords);
		AIBoard.addShipToBoard(threeShipA, twoShipCoords);

		

	}
	private void setupGrid() {
		for (int i = 0; i <10; i++) {
			for (int j = 0; j < 10;j++) {
				playerGrid[i][j] = new Button(playerBoard.toStringCoord(i, j));
				playerGrid[i][j].setMinSize(30, 10);
				

				AIGrid[i][j] = new Button(AIBoard.toStringCoord(i, j));
				AIGrid[i][j].setMinSize(30, 10);

				playerGrid[i][j].setOnAction(new PlayerButtonListener());

				AIGridPane.add(AIGrid[i][j], i, j);
				playerGridPane.add(playerGrid[i][j], i, j);
			}
		}


		// TODO Auto-generated method stub


		vBox.getChildren().clear();

		vBox.getChildren().add(AILabel);
		vBox.getChildren().add(AIGridPane);
		vBox.getChildren().add(playerLabel);
		vBox.getChildren().add(playerGridPane);
	}
	private void registerHandlers() {

		startButton.setOnAction(new startListener());

	}

	public class startListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			setupBoard();
			setupGrid();
		}
	}

	public class PlayerButtonListener implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			Button buttonClicked = (Button) arg0.getSource();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (playerGrid[i][j] == buttonClicked) {
						System.out.print("hi");
					}
				}
			}
		}
	}
	private void PlayGame() {


	}

}
