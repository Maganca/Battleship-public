package views_controller;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Board;
import model.Ship;
import model.ShipPlacement;

public class ShipPlacementView extends GridPane {

    private Board board;
    private int boardSize = 10;
    private Button shuffleButton;
    private Button startGameButton;
    private List<Ship> shipList;

    public ShipPlacementView() {
        initialize();
        setupBoard();
        setupButtons();
    }

    private void initialize() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(5);
        this.setVgap(5);
    }

    private void setupBoard() {
        board = new Board(boardSize);
        showPredictionCoordinates(5, 5, 3); // Example prediction, you can customize this
        this.getChildren().add(getBoardVisual());
    }

    private void showPredictionCoordinates(int row, int col, int size) {
        // Visualize ship placement predictions (light pink color)
        for (int i = 0; i < size; i++) {
            Rectangle cell = new Rectangle(30, 30);
            cell.setFill(Color.LIGHTPINK);
            this.add(cell, col + i, row);
        }
    }

    private Rectangle[][] getBoardVisual() {
        Rectangle[][] boardVisual = new Rectangle[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Rectangle cell = new Rectangle(30, 30);
                cell.setFill(Color.LIGHTGRAY);
                boardVisual[i][j] = cell;
            }
        }
        return boardVisual;
    }

    private void setupButtons() {
        shuffleButton = new Button("Shuffle Ships");
        shuffleButton.setOnAction(e -> shuffleShips());
        startGameButton = new Button("Start Game");
        startGameButton.setOnAction(e -> startGame());

        this.add(shuffleButton, 0, boardSize + 1);
        this.add(startGameButton, 1, boardSize + 1);
    }

    private void shuffleShips() {
        ShipPlacement.randomlyPlaceRemainingShips(board);
        updateBoardVisual();
    }

    private void startGame() {
    	// Create a new ShipGameView with the current board and any other necessary parameters
        ShipGameView shipGameView = new ShipGameView();
        // Create a new Scene with the ShipGameView
        Scene scene = new Scene(shipGameView, 800, 600);  // Adjust width and height as needed
        // Get the current window (Stage)
        Stage stage = (Stage) getScene().getWindow();
        // Set the scene to the new ShipGameView
        stage.setScene(scene);
        // Show the stage
        stage.show();
    }

    private void updateBoardVisual() {
        // Update the visual representation of the board
        // Reset the board
        this.getChildren().clear();
        this.getChildren().addAll(getBoardVisual());

        // Visualize placed ships (dark gray color)
        for (Ship ship : board.getShips()) {
            for (int[] location : ship.locations) {
                int row = location[0];
                int col = location[1];
                Rectangle cell = new Rectangle(30, 30);
                cell.setFill(Color.DARKGRAY);
                this.add(cell, col, row);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void launch(String[] args) {
        // Launch the JavaFX application
        Stage stage = new Stage();
        ShipPlacementView shipPlacementView = new ShipPlacementView();
        Scene scene = new Scene(shipPlacementView, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Ship Placement View");
        stage.show();
    }
}
