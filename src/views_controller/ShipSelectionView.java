package views_controller;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Player;
import model.Ship;
import model.Player.PlayerType;

public class ShipSelectionView extends BorderPane {
	private int GAME_SIZE = 10;
	private Player player = new Player(PlayerType.HUMAN, GAME_SIZE);

	private VBox ourVBox = new VBox();

	public Button easyButton = new Button("Start Easy Game");
	public Button hardButton = new Button("Start Hard Game");

	private TextField twoXCoord = new TextField();
	private TextField twoYCoord = new TextField();
	private TextField twoVert = new TextField();

	private TextField threeAXCoord = new TextField();
	private TextField threeAYCoord = new TextField();
	private TextField threeAVert = new TextField();

	private TextField threeBXCoord = new TextField();
	private TextField threeBYCoord = new TextField();
	private TextField threeBVert = new TextField();

	private TextField fourXCoord = new TextField();
	private TextField fourYCoord = new TextField();
	private TextField fourVert = new TextField();

	private TextField fiveXCoord = new TextField();
	private TextField fiveYCoord = new TextField();
	private TextField fiveVert = new TextField();

	public ShipSelectionView() {
		initializePanel();
	}

	private void initializePanel() {
		ourVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,
				CornerRadii.EMPTY, Insets.EMPTY)));
		ourVBox.setBorder(new Border(new BorderStroke(Color.BLUE,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.EMPTY,
				new Insets(10, 10, 10, 10))));
		Label explanation1 = new Label("Add your ships: ");
		Label explanation2 = new Label(
				"Verticality takes 0 for horizontal or 1 for vertical");
		Label pLabel = new Label("\nPatrol Boat (length of 2): ");
		Label sLabel = new Label("\nSubmarine (length of 3): ");
		Label dLabel = new Label("\nDestroyer (length of 3): ");
		Label bLabel = new Label("\nBattleship (length of 4): ");
		Label cLabel = new Label("\nCarrier (length of 5): ");
		explanation1.setStyle("-fx-font-size: 20; -fx-font-family: Verdana");
		explanation2.setStyle("-fx-font-size: 15; -fx-font-family: Verdana");
		pLabel.setStyle("-fx-font-size: 15; -fx-font-family: Verdana");
		sLabel.setStyle("-fx-font-size: 15; -fx-font-family: Verdana");
		dLabel.setStyle("-fx-font-size: 15; -fx-font-family: Verdana");
		bLabel.setStyle("-fx-font-size: 15; -fx-font-family: Verdana");
		cLabel.setStyle("-fx-font-size: 15; -fx-font-family: Verdana");

		GridPane patrol = new GridPane();
		twoXCoord.setMaxWidth(50);
		twoYCoord.setMaxWidth(50);
		twoVert.setMaxWidth(50);
		patrol.add(new Label("\tx coord: "), 1, 0);
		patrol.add(new Label("\ty coord: "), 3, 0);
		patrol.add(new Label("\tverticality: "), 5, 0);
		patrol.add(twoXCoord, 2, 0);
		patrol.add(twoYCoord, 4, 0);
		patrol.add(twoVert, 6, 0);

		GridPane submarine = new GridPane();
		threeAXCoord.setMaxWidth(50);
		threeAYCoord.setMaxWidth(50);
		threeAVert.setMaxWidth(50);
		submarine.add(new Label("\tx coord: "), 1, 0);
		submarine.add(new Label("\ty coord: "), 3, 0);
		submarine.add(new Label("\tverticality: "), 5, 0);
		submarine.add(threeAXCoord, 2, 0);
		submarine.add(threeAYCoord, 4, 0);
		submarine.add(threeAVert, 6, 0);

		GridPane destroyer = new GridPane();
		threeBXCoord.setMaxWidth(50);
		threeBYCoord.setMaxWidth(50);
		threeBVert.setMaxWidth(50);
		destroyer.add(new Label("\tx coord: "), 1, 0);
		destroyer.add(new Label("\ty coord: "), 3, 0);
		destroyer.add(new Label("\tverticality: "), 5, 0);
		destroyer.add(threeBXCoord, 2, 0);
		destroyer.add(threeBYCoord, 4, 0);
		destroyer.add(threeBVert, 6, 0);

		GridPane battleship = new GridPane();
		fourXCoord.setMaxWidth(50);
		fourYCoord.setMaxWidth(50);
		fourVert.setMaxWidth(50);
		battleship.add(new Label("\tx coord: "), 1, 0);
		battleship.add(new Label("\ty coord: "), 3, 0);
		battleship.add(new Label("\tverticality: "), 5, 0);
		battleship.add(fourXCoord, 2, 0);
		battleship.add(fourYCoord, 4, 0);
		battleship.add(fourVert, 6, 0);

		GridPane carrier = new GridPane();
		fiveXCoord.setMaxWidth(50);
		fiveYCoord.setMaxWidth(50);
		fiveVert.setMaxWidth(50);
		carrier.add(new Label("\tx coord: "), 1, 0);
		carrier.add(new Label("\ty coord: "), 3, 0);
		carrier.add(new Label("\tverticality: "), 5, 0);
		carrier.add(fiveXCoord, 2, 0);
		carrier.add(fiveYCoord, 4, 0);
		carrier.add(fiveVert, 6, 0);

		GridPane buttons = new GridPane();
		buttons.add(easyButton, 2, 0);
		buttons.add(hardButton, 10, 0);
		buttons.setHgap(10);

		ourVBox.getChildren().addAll(explanation1, explanation2, pLabel, patrol,
				sLabel, submarine, dLabel, destroyer, bLabel, battleship,
				cLabel, carrier, new Label("\n"), buttons);
		this.setCenter(ourVBox);
	}

	public Player getConstructedPlayer() {
		player.getBoard()
				.addShipToBoard(new Ship(Integer.parseInt(twoXCoord.getText()),
						Integer.parseInt(twoYCoord.getText()),
						Integer.parseInt(twoVert.getText()), 2));

		player.getBoard().addShipToBoard(
				new Ship(Integer.parseInt(threeAXCoord.getText()),
						Integer.parseInt(threeAYCoord.getText()),
						Integer.parseInt(threeAVert.getText()), 3));

		player.getBoard().addShipToBoard(
				new Ship(Integer.parseInt(threeBXCoord.getText()),
						Integer.parseInt(threeBYCoord.getText()),
						Integer.parseInt(threeBVert.getText()), 3));

		player.getBoard()
				.addShipToBoard(new Ship(Integer.parseInt(fourXCoord.getText()),
						Integer.parseInt(fourYCoord.getText()),
						Integer.parseInt(fourVert.getText()), 4));

		player.getBoard()
				.addShipToBoard(new Ship(Integer.parseInt(fiveXCoord.getText()),
						Integer.parseInt(fiveYCoord.getText()),
						Integer.parseInt(fiveVert.getText()), 5));
		return player;
	}
}
