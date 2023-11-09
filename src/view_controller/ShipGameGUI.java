package view_controller;

import java.io.File;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Board;
import model.Board.ShotResult;
import model.Ship;

/**
 * Class Ship Game, where the Game logic and GUI are present.
 */
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

	private static final Media SINK_NOISE = new Media(
			new File("soundeffects/explosion-6055.mp3").toURI().toString());
	private static final Media HIT_NOISE = new Media(
			new File("soundeffects/muffled-distant-explosion-7104.mp3").toURI()
					.toString());
	private static final Media MISS_NOISE = new Media(
			new File("soundeffects/splash-by-blaukreuz-6261.mp3").toURI()
					.toString());
	private static final Media START_NOISE = new Media(
			new File("soundeffects/the-notification-email-143029.mp3").toURI()
					.toString());
	private static final Media ERROR_NOISE = new Media(
			new File("soundeffects/error-126627.mp3").toURI().toString());
	private MediaPlayer soundPlayer;

	/**
	 * 1 = Sink noise 2 = Hit noise 3 = Miss noise 4 = Start noise 5 = Error
	 * noise
	 * 
	 * @param soundNum
	 */
	private void playSound(int soundNum) {
		switch (soundNum) {
		case 1:
			soundPlayer = new MediaPlayer(SINK_NOISE);
			soundPlayer.play();
			return;
		case 2:
			soundPlayer = new MediaPlayer(HIT_NOISE);
			soundPlayer.play();
			return;
		case 3:
			soundPlayer = new MediaPlayer(MISS_NOISE);
			soundPlayer.play();
			return;
		case 4:
			soundPlayer = new MediaPlayer(START_NOISE);
			soundPlayer.play();
			return;
		case 5:
			soundPlayer = new MediaPlayer(ERROR_NOISE);
			soundPlayer.play();
			return;
		default:
			return;
		}
	}

	/**
	 * Start
	 */
	public void start(Stage stage) throws Exception {
		stage.setTitle("Goobie Snooble");
		layoutGUI(stage);

		registerHandlers();

	}

	/**
	 * Sets up the initial Title Layout of the thing
	 * 
	 * @param stage
	 */
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
		Ship twoShip = new Ship(new int[] { 0, 1 }, 0, 2);
		AIBoard.addShipToBoard(twoShip);

		Ship threeShip = new Ship(new int[] { 1, 1 }, 0, 3);
		AIBoard.addShipToBoard(threeShip);

		Ship threeShipA = new Ship(new int[] { 1, 1 }, 0, 3);
		playerBoard.addShipToBoard(threeShipA);

	}

	private void setupGrid() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				playerGrid[i][j] = new Button(playerBoard.toStringCoord(i, j));
				playerGrid[i][j].setMinSize(30, 10);

				AIGrid[i][j] = new Button("_");
				AIGrid[i][j].setMinSize(30, 10);

				AIGrid[i][j].setOnAction(new PlayerButtonListener());

				AIGridPane.add(AIGrid[i][j], i, j);
				playerGridPane.add(playerGrid[i][j], i, j);
			}
		}
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

	public class PlayerButtonListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {

			Button buttonClicked = (Button) arg0.getSource();
			Random random = new Random();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (AIGrid[i][j] == buttonClicked) {

						int valid = 0;
						int AIx = random.nextInt(10);
						int AIy = random.nextInt(10);
						ShotResult playerOutcome = AIBoard.shotAttempt(i, j);
						while (AIGrid[AIx][AIy].getText().equals("-")) {

							AIx = random.nextInt(10);
							AIy = random.nextInt(10);
						}
						ShotResult AIOutcome = playerBoard.shotAttempt(AIx,
								AIy);

						switch (playerOutcome) {
						case GAME_WON:
							playSound(4);
							System.out.print("Player Won");
							break;
						case HIT:
							playSound(2);
							System.out.print("Hit!");
							valid++;
							break;
						case INVALID:
							playSound(5);
							System.out.print("Can't do that!");
							break;
						case MISS:
							playSound(3);
							System.out.print("Miss!");
							valid++;
							break;
						case REPEAT_ATTEMPT:
							playSound(5);
							System.out.print("Try Again\n");
							break;
						case SUNK:
							playSound(1);
							System.out.print("You sunk my ship!");
							valid++;
							break;
						default:
							break;

						}
						if (valid > 0) {
							System.out.print(valid);
							System.out.print("\n AI's turn! \n");
							switch (AIOutcome) {
							case GAME_WON:
								System.out.print("AI Won\n");
								break;
							case HIT:
								System.out.print("Hit!\n");
								break;
							case INVALID:
								System.out.print("Can't do that!\n");
								break;
							case MISS:
								System.out.print("Miss!\n");
								break;
							case REPEAT_ATTEMPT:
								System.out.print("Try Again\n");
								break;
							case SUNK:
								System.out.print("You sunk my ship!\n");
								break;
							default:
								break;

							}
							playerGrid[AIx][AIy].setText(
									playerBoard.toStringCoord(AIx, AIy));
						}
						AIGrid[i][j].setText(AIBoard.toStringCoord(i, j));

					}
				}
			}
		}
	}
}
