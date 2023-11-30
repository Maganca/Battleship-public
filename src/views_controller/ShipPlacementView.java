package views_controller;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.*;
import model.Player.PlayerType;

import java.util.Arrays;
import java.util.List;

public class ShipPlacementView extends BorderPane {
    private Player player;
    private BoardView boardView;
    private int selectedShipSize;
    Button startButton;

    public ShipPlacementView() {
        initializePanel();
    }

    private void initializePanel() {
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));

        // Customize board size
        Label boardSizeLabel = new Label("Board Size:");
        Slider boardSizeSlider = new Slider(5, 15, 10);
        boardSizeSlider.setShowTickMarks(true);
        boardSizeSlider.setShowTickLabels(true);

        // Number of ships and their lengths
        Label shipsInfoLabel = new Label("Number of Ships and Lengths:");
        ListView<String> shipsInfoListView = new ListView<>();
        shipsInfoListView.setPrefHeight(100);

        // Board view
        boardView = new BoardView();
        boardView.setCellSize(30);

        // Buttons for placing and shuffling ships
        Button placeShipsButton = new Button("Place Ships");
        Button shuffleShipsButton = new Button("Shuffle Ships");

        // Close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            // Add logic to handle closing the window
        });

        // Binding to update board size label
        Label boardSizeDisplayLabel = new Label();
        boardSizeDisplayLabel.textProperty().bind(Bindings.format("Board Size: %.0f", boardSizeSlider.valueProperty()));

        // Binding to update ships info
        shipsInfoListView.itemsProperty().bind((ObservableValue<? extends ObservableList<String>>) Bindings.createObjectBinding(() -> {
            int boardSize = (int) boardSizeSlider.getValue();
            Board tempBoard = new Board(boardSize);
            ShipPlacement.randomlyPlaceRemainingShips(tempBoard);
            return tempBoard.getShipList();
        }, boardSizeSlider.valueProperty()));

        // Event handler for placing ships
        placeShipsButton.setOnAction(event -> placeShips());

        // Event handler for shuffling ships
        shuffleShipsButton.setOnAction(event -> shuffleShips());
        
        // Start button
        startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            // Add logic to handle starting the game after placing ships
            if (player.getBoard().allShipsPlaced()) {
                // Check if all ships are placed
                ShipGameView shipGameView = new ShipGameView(player, new Player(PlayerType.HUMAN, 10));
                BattleshipGUI gui = new BattleshipGUI();
                gui.setViewTo(shipGameView);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ship Placement");
                alert.setHeaderText(null);
                alert.setContentText("Please place all the ships on the board before starting the game.");
                alert.showAndWait();
            }
        });

        // Add components to the layout
        vBox.getChildren().addAll(
                boardSizeLabel, boardSizeSlider, boardSizeDisplayLabel,
                shipsInfoLabel, shipsInfoListView,
                placeShipsButton, shuffleShipsButton, closeButton
        );
        setLeft(vBox);
        setCenter(boardView);
    }

    private void placeShips() {
        if (selectedShipSize > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ship Placement");
            alert.setHeaderText(null);

            ShipPlacementView.BoardView.CellStatus status = boardView.getCellStatus();
            if (status == ShipPlacementView.BoardView.CellStatus.EMPTY) {
                // Place the ship
                player.getBoard().addShipToBoard(new Ship(status.getRow(), status.getCol(), status.isVertical() ? 1 : 0, selectedShipSize));
                boardView.resetCellStatus();
            } else {
                alert.setContentText("Invalid placement. The selected cell is already occupied.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ship Placement");
            alert.setHeaderText(null);
            alert.setContentText("Please select a ship from the ship lengths list.");
            alert.showAndWait();
        }
    }

    private void shuffleShips() {
        ShipPlacement.shuffleShips(player.getBoard());
        boardView.resetCellStatus();
    }
    private class BoardView extends GridPane {
        private double cellSize;
        private CellStatus cellStatus;

        public void setCellSize(double size) {
            this.cellSize = size;
            updateBoard();
        }

        public CellStatus getCellStatus() {
            return cellStatus;
        }

        public void resetCellStatus() {
            this.cellStatus = null;
        }

        private void updateBoard() {
            getChildren().clear();
            int size = player.getBoard().getSize();

            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    Rectangle cell = new Rectangle(cellSize, cellSize);
                    cell.setFill(Color.LIGHTGRAY);
                    cell.setStroke(Color.BLACK);

                    // Add event handler for cell click to handle ship placement/change
                    cell.setOnMouseClicked(event -> handleCellClick(row,col));
                    add(cell, col, row);
                }
            }
        }

        private void handleCellClick(int row, int col) {
            // Add logic to handle ship placement/change on cell click
            if (selectedShipSize > 0) {
                if (cellStatus == null || !cellStatus.isOccupied()) {
                    this.cellStatus = new CellStatus(row, col, selectedShipSize);
                    drawShipPreview();
                } else {
                    resetCellStatus();
                    drawShipPreview();
                }
            }
        }

        private void drawShipPreview() {
            getChildren().forEach(node -> {
                Rectangle cell = (Rectangle) node;
                CellStatus status = getCellStatus();
                if (status != null && status.getRow() <= GridPane.getRowIndex(node) && GridPane.getRowIndex(node) < status.getRow() + status.getSize()
                        && status.getCol() == GridPane.getColumnIndex(node) && !status.isOccupied()) {
                    cell.setFill(Color.GREEN);
                } else {
                    cell.setFill(Color.LIGHTGRAY);
                }
            });
        }

        private class CellStatus {
            public static final CellStatus EMPTY = null;
			private final int row;
            private final int col;
            private final int size;

            public CellStatus(int row, int col, int size) {
                this.row = row;
                this.col = col;
                this.size = size;
            }

            public boolean isVertical() {
				return false;
			}

			public int getRow() {
                return row;
            }

            public int getCol() {
                return col;
            }

            public int getSize() {
                return size;
            }
            public boolean isOccupied() {
                return size > 0;
            }
        }
    }

}
