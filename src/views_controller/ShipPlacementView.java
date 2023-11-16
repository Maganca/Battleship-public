package views_controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Board;
import model.Ship;

public class ShipPlacementView extends GridPane {

    private Board board;
    private int boardSize = 10;
    private Button shuffleButton;
    private Button startGameButton;

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
        // Add code here to show light pink coordinates for ship placement prediction
        // You may use rectangles or other visual elements to represent the coordinates
        // Set the color to light pink or any color you prefer
        // Example:
        showPredictionCoordinates(5, 5, 3);
        this.getChildren().addAll(getBoardVisual());
    }

    private void showPredictionCoordinates(int row, int col, int size) {
        // Add code here to show light pink coordinates for ship placement prediction
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
        // Add code here to shuffle ships randomly on the board
        model.ShipPlacement.randomlyPlaceRemainingShips(board);
        // Update the visual representation of the board
        updateBoardVisual();
    }

    private void startGame() {
    }

    private void updateBoardVisual() {
        // Add code here to update the visual representation of the board
        // You may change the color of rectangles to represent ship positions
        // Example:
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
