package views_controller;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Board;

public class ShipWinView extends BorderPane{
	private VBox vBox = new VBox();
	private Label winText = new Label("YOU WINNER");
	private Label loseText = new Label("YOU LOSE");
	public Button playAgainButton = new Button("Play Again?");
	private Image background = new Image(new File("Tiles/background.png").toURI().toString());
	private Button startOverButton = new Button("Start Over");
	private GridPane resultsGrid = new GridPane();
	
	public ShipWinView(Board Board) {
		initializePanel(   );
		setupResultsScreen(Board);
	}

	private void initializePanel() {
		StartScreenView shipWinView = new StartScreenView();
		playAgainButton.setOnAction(mouseEvent -> {
			this.setCenter(null);
			this.setCenter(shipWinView);
		});
        startOverButton = new Button("Start Over");
		startOverButton.setOnAction(e -> startOver());
		
		vBox.getChildren().add(winText);
		vBox.getChildren().add(playAgainButton);
		vBox.getChildren().add(startOverButton);
		vBox.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		vBox.setAlignment(Pos.CENTER);
		this.setCenter(vBox);
	}
	
	private void setupResultsScreen(Board playerBoard) {
		// Create a listener for the game results
        playerBoard.gameResultProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showResults(newValue, playerBoard);
            }
        });
    }

    private void showResults(Board.GameResult result, Board playerBoard) {
    	int[] hitsAndMisses = playerBoard.calculateTotalHitsAndMisses(playerBoard);
        String hitsText = "Total Hits: " + hitsAndMisses[0];
        String missesText = "Total Misses: " + hitsAndMisses[1];

        resultsGrid.setAlignment(Pos.CENTER);
        resultsGrid.setHgap(10);
        resultsGrid.setVgap(10);

        Label hitsLabel = new Label(hitsText);
        Label missesLabel = new Label(missesText);

        resultsGrid.add(hitsLabel, 0, 0);
        resultsGrid.add(missesLabel, 0, 1);

        this.setCenter(resultsGrid);
    }
	
	
    private void startOver() {
        // Restart the game
        ShipPlacementView shipPlacementView = new ShipPlacementView();
        Scene scene = new Scene(shipPlacementView, 400, 400);
        Stage stage = (Stage) getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ship Placement View");
        stage.show();
    }
}
