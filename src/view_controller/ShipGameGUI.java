package view_controller;

import model.*;
import model.Board.*;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

// Attempting to make the windows more organized.

/**
 * Class Ship Game, where the Game logic and GUI are present.
 */
public class ShipGameGUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private BorderPane borderPane = new BorderPane();
	private VBox vBox = new VBox();
	private Button startButton = new Button("Start");
	private Button easyButton = new Button("Start Easy Game");
	private Button hardButton = new Button("Start Hard Game");
	private Label title = new Label(
			"A Game That Allows You To Place Ships And Sink Those Same Ships But Not Yours The Other Guy's");

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

	private Label credits = new Label("By: Kade, Chi, Danilo, and Ethan");

	private Image Ocean_null = new Image(new File("Tiles/Ocean1.png").toURI().toString());
	private Image Ocean_miss = new Image(new File("Tiles/Ocean2.png").toURI().toString());
	private Image Ship_miss = new Image(new File("Tiles/Ship1.png").toURI().toString());
	private Image Ship_hit = new Image(new File("Tiles/ShipHit1.png").toURI().toString());

	private AIPlayer currentAI = new AIPlayer();
	private ImageView[][] playerGrid = new ImageView[10][10];
	private ImageView[][] AIGrid = new ImageView[10][10];
	private GridPane playerGridPane = new GridPane();
	private GridPane AIGridPane = new GridPane();
	public Board playerBoard = new Board(10);
	public Board AIBoard = new Board(10);

	private static final Media SINK_NOISE = new Media(new File("soundeffects/explosion-6055.mp3").toURI().toString());
	private static final Media HIT_NOISE = new Media(
			new File("soundeffects/muffled-distant-explosion-7104.mp3").toURI().toString());
	private static final Media MISS_NOISE = new Media(
			new File("soundeffects/splash-by-blaukreuz-6261.mp3").toURI().toString());
	private static final Media GOOD_NOISE = new Media(
			new File("soundeffects/the-notification-email-143029.mp3").toURI().toString());
	private static final Media ERROR_NOISE = new Media(new File("soundeffects/error-126627.mp3").toURI().toString());
	private MediaPlayer soundPlayer;

	/**
	 * Start
	 */
	public void start(Stage stage) throws Exception {
		stage.setTitle("Definitely not BattleShip");
		layoutGUI(stage);
		startButton.setOnAction(new Listener());
		easyButton.setOnAction(new Listener());
		hardButton.setOnAction(new Listener());
	}

	/**
	 * Sets up the initial Title Layout of the thing
	 * 
	 * @param stage
	 */
	private void layoutGUI(Stage stage) {
		Scene scene = new Scene(vBox, 420, 600);
		title.setWrapText(true);
		vBox.getChildren().addAll(title, credits, startButton);
		vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		vBox.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				BorderWidths.EMPTY, new Insets(10, 10, 10, 10))));
		stage.setScene(scene);
		stage.show();
	}

	private void setupCoords() {
		vBox.getChildren().clear();
		Label explanation1 = new Label("Add your ships: ");
		Label explanation2 = new Label("Verticality takes 0 for horizontal or 1 for vertical");
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

		vBox.getChildren().addAll(explanation1, explanation2, pLabel, patrol, sLabel, submarine, dLabel, destroyer,
				bLabel, battleship, cLabel, carrier, new Label("\n"), buttons);
	}

	private void setupBoard() {
		currentAI.placeShips(AIBoard);

		playerBoard.addShipToBoard(new Ship(Integer.parseInt(twoXCoord.getText()),
				Integer.parseInt(twoYCoord.getText()), Integer.parseInt(twoVert.getText()), 2));

		playerBoard.addShipToBoard(new Ship(Integer.parseInt(threeAXCoord.getText()),
				Integer.parseInt(threeAYCoord.getText()), Integer.parseInt(threeAVert.getText()), 3));

		playerBoard.addShipToBoard(new Ship(Integer.parseInt(threeBXCoord.getText()),
				Integer.parseInt(threeBYCoord.getText()), Integer.parseInt(threeBVert.getText()), 3));

		playerBoard.addShipToBoard(new Ship(Integer.parseInt(fourXCoord.getText()),
				Integer.parseInt(fourYCoord.getText()), Integer.parseInt(fourVert.getText()), 4));

		playerBoard.addShipToBoard(new Ship(Integer.parseInt(fiveXCoord.getText()),
				Integer.parseInt(fiveYCoord.getText()), Integer.parseInt(fiveVert.getText()), 5));
	}

	private Image imageChoice(String stat) {
		if (stat.equals("H")) {
			return Ship_hit;
		} else if (stat.equals("M")) {
			return Ocean_miss;
		} else if (stat.equals("S")) {
			return Ship_miss;
		} else if (stat.equals("_")) {
			return Ocean_null;
		} else {
			return null;
		}
	}

	private void setupGrid() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				playerGrid[i][j] = new ImageView(imageChoice(playerBoard.toStringCoord(i, j)));
				playerGrid[i][j].setFitWidth(20);
				playerGrid[i][j].setFitHeight(20);
				AIGrid[i][j] = new ImageView(Ocean_null);
				AIGrid[i][j].setFitWidth(20);
				AIGrid[i][j].setFitHeight(20);
				AIGrid[i][j].setOnMouseClicked(new ShotListener());

				AIGridPane.add(AIGrid[i][j], i, j);
				playerGridPane.add(playerGrid[i][j], i, j);
			}
		}
		AIGridPane.setHgap(2);
		AIGridPane.setVgap(2);
		playerGridPane.setHgap(2);
		playerGridPane.setVgap(2);
		vBox.getChildren().clear();
		vBox.getChildren().add(new Label("My Board: "));
		vBox.getChildren().add(AIGridPane);
		vBox.getChildren().add(new Label("\nYour Board: "));
		vBox.getChildren().add(playerGridPane);
	}

	public class Listener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			Labeled item = (Labeled) arg0.getSource();
			if (item.getText() == "Start")
				setupCoords();
			if (item.getText() == "Start Easy Game") {
				currentAI.setStrategy(new RandomAI());
				setupBoard();
				setupGrid();
			}
			if (item.getText() == "Start Hard Game") {
				currentAI.setStrategy(new HardAI());
				setupBoard();
				setupGrid();
			}
		}
	}

	public class ShotListener implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			ImageView ImageClicked = (ImageView) arg0.getSource();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (AIGrid[i][j] == ImageClicked) {
						int valid = 0;
						ShotResult playerOutcome = AIBoard.shotAttempt(i, j);
						switch (playerOutcome) {
						case GAME_WON:
							soundPlayer = new MediaPlayer(GOOD_NOISE);
							soundPlayer.play();
							System.out.print("You Won");
							break;
						case HIT:
							soundPlayer = new MediaPlayer(HIT_NOISE);
							soundPlayer.play();
							System.out.print("You hit my ship!");
							valid++;
							break;
						case MISS:
							soundPlayer = new MediaPlayer(MISS_NOISE);
							soundPlayer.play();
							System.out.print("You missed");
							valid++;
							break;
						case REPEAT_ATTEMPT:
							soundPlayer = new MediaPlayer(ERROR_NOISE);
							soundPlayer.play();
							System.out.print("Try Again\n");
							break;
						case SUNK:
							soundPlayer = new MediaPlayer(SINK_NOISE);
							soundPlayer.play();
							System.out.print("You sunk my ship!");
							valid++;
							break;
						default:
							break;
						}
						if (valid > 0) {
							ShotResult AIOutcome = currentAI.shoot(playerBoard);
							System.out.print("\nMy turn: ");
							switch (AIOutcome) {
							case GAME_WON:
								System.out.print("I won!\n");
								break;
							case HIT:
								System.out.print("I hit your ship!\n");
								break;
							case MISS:
								System.out.print("I missed\n");
								break;
							case SUNK:
								System.out.print("I sunk your ship!\n");
								break;
							default:
								break;
							}
							for (int k = 0; k < 10; k++) {
								for (int l = 0; l < 10; l++) {
									playerGrid[k][l].setImage(imageChoice(playerBoard.toStringCoord(k, l)));
								}
							}
						}
						AIGrid[i][j].setImage(imageChoice(AIBoard.toStringCoord(i, j)));
					}
				}
			}
		}
	}
}
