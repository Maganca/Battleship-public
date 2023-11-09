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
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Board;
import model.Board.ShotResult;
import model.Ship;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	private Button selectionButton = new Button("Start Game");
	private Label title = new Label(
			"A Game That Allows You To Place Ships And Sink Those Same Ships But Not Yours The Other Guy's");
	
	
	private Label twoShip = new Label("2-ship X Coord, Y Coord, Verticality (1 for vertical, 0 for horizontal)");
	private TextArea twoXCoord = new TextArea();
	private TextArea twoYCoord = new TextArea();
	private TextArea twoVert = new TextArea();
	
	private Label threeAShip = new Label("3A-ship X Coord, Y Coord, Verticality (1 for vertical, 0 for horizontal)");
	private TextArea threeAXCoord = new TextArea();
	private TextArea threeAYCoord = new TextArea();
	private TextArea threeAVert = new TextArea();
	
	private Label threeBShip = new Label("3B-ship X Coord, Y Coord, Verticality (1 for vertical, 0 for horizontal)");
	private TextArea threeBXCoord = new TextArea();
	private TextArea threeBYCoord = new TextArea();
	private TextArea threeBVert = new TextArea();
	
	private Label fourShip = new Label("4-ship X Coord, Y Coord, Verticality (1 for vertical, 0 for horizontal)");
	private TextArea fourXCoord = new TextArea();
	private TextArea fourYCoord = new TextArea();
	private TextArea fourVert = new TextArea();
	
	private Label fiveShip = new Label("5-ship X Coord, Y Coord, Verticality (1 for vertical, 0 for horizontal)");
	private TextArea fiveXCoord = new TextArea();
	private TextArea fiveYCoord = new TextArea();
	private TextArea fiveVert = new TextArea();
	
	

	private Label credits = new Label("By: Kade, Chi, Danilo, and Ethan");
	private Label AILabel = new Label("CPU's Board");
	private Label playerLabel = new Label("Player's Board");
	
	private Image Ocean_null= new Image("file:///C:/Users/ethan/Documents/gitrepos/team-project-the-dawn-of-the-first-day/Tiles/Ocean1.png");
	private Image Ocean_miss= new Image("file:///C:/Users/ethan/Documents/gitrepos/team-project-the-dawn-of-the-first-day/Tiles/Ocean2.png");
	private Image Ship_miss= new Image("file:///C:/Users/ethan/Documents/gitrepos/team-project-the-dawn-of-the-first-day/Tiles/Ship1.png");
	private Image Ship_hit = new Image("file:///C:/Users/ethan/Documents/gitrepos/team-project-the-dawn-of-the-first-day/Tiles/ShipHit1.png");

	
	
	private ImageView[][] playerGrid = new ImageView[10][10];
	private ImageView[][] AIGrid = new ImageView[10][10];
	private GridPane playerGridPane = new GridPane();
	private GridPane AIGridPane = new GridPane();
	private GridPane twoCoordsGridPane = new GridPane();
	private GridPane threeACoordsGridPane = new GridPane();
	private GridPane threeBCoordsGridPane = new GridPane();
	private GridPane fourCoordsGridPane = new GridPane();
	private GridPane fiveCoordsGridPane = new GridPane();
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
	private void setupCoords() {
		vBox.getChildren().clear();
		
		twoCoordsGridPane.add(twoShip, 0,0);
		twoCoordsGridPane.add(twoXCoord, 0,1);
		twoCoordsGridPane.add(twoYCoord, 1,1);
		twoCoordsGridPane.add(twoVert, 2,1);
		
		threeACoordsGridPane.add(threeAShip, 0,0);
		threeACoordsGridPane.add(threeAXCoord, 0,1);
		threeACoordsGridPane.add(threeAYCoord, 1,1);
		threeACoordsGridPane.add(threeAVert, 2,1);
		
		threeBCoordsGridPane.add(threeBShip, 0,0);
		threeBCoordsGridPane.add(threeBXCoord, 0,1);
		threeBCoordsGridPane.add(threeBYCoord, 1,1);
		threeBCoordsGridPane.add(threeBVert, 2,1);
		
		fourCoordsGridPane.add(fourShip, 0,0);
		fourCoordsGridPane.add(fourXCoord, 0,1);
		fourCoordsGridPane.add(fourYCoord, 1,1);
		fourCoordsGridPane.add(fourVert, 2,1);
		
		fiveCoordsGridPane.add(fiveShip, 0,0);
		fiveCoordsGridPane.add(fiveXCoord, 0,1);
		fiveCoordsGridPane.add(fiveYCoord, 1,1);
		fiveCoordsGridPane.add(fiveVert, 2,1);
		
		
		vBox.getChildren().add(twoCoordsGridPane);
		vBox.getChildren().add(threeACoordsGridPane);
		vBox.getChildren().add(threeBCoordsGridPane);
		vBox.getChildren().add(fourCoordsGridPane);
		vBox.getChildren().add(fiveCoordsGridPane);
		vBox.getChildren().add(selectionButton);
	}
	private void AIPlaceShip(int shipSize) {
		Random random = new Random();
		int validInt = 0;
		while(validInt == 0) {
			int[] coord = new int[] {random.nextInt(10),random.nextInt(10)} ;
			Ship twoShip = new Ship(coord, random.nextInt(2), shipSize);
			if (AIBoard.addShipToBoard(twoShip)) {
				validInt++;
			}
				
		}
	}
	private void setupBoard() {
		
		
		AIPlaceShip(2);
		AIPlaceShip(3);
		AIPlaceShip(3);
		AIPlaceShip(4);
		AIPlaceShip(5);
		

		Ship twoShip = new Ship(new int[] { Integer.parseInt(twoXCoord.getText()),Integer.parseInt(twoYCoord.getText()) },Integer.parseInt(twoVert.getText()), 2);
		playerBoard.addShipToBoard(twoShip);

		Ship threeAShip = new Ship(new int[] { Integer.parseInt(threeAXCoord.getText()),Integer.parseInt(threeAYCoord.getText()) },Integer.parseInt(threeAVert.getText()), 3);
		playerBoard.addShipToBoard(threeAShip);
		
		Ship threeBShip = new Ship(new int[] { Integer.parseInt(threeBXCoord.getText()),Integer.parseInt(threeBYCoord.getText()) },Integer.parseInt(threeBVert.getText()), 3);
		playerBoard.addShipToBoard(threeBShip);
		
		Ship fourShip = new Ship(new int[] { Integer.parseInt(fourXCoord.getText()),Integer.parseInt(fourYCoord.getText()) },Integer.parseInt(fourVert.getText()), 4);
		playerBoard.addShipToBoard(fourShip);
		
		Ship fiveShip = new Ship(new int[] { Integer.parseInt(fiveXCoord.getText()),Integer.parseInt(fiveYCoord.getText()) },Integer.parseInt(fiveVert.getText()), 5);
		playerBoard.addShipToBoard(fiveShip);

	}
	private Image imageChoice(String stat) {
		if(stat.equals("H")) {
			return Ship_hit;
		}
		else if(stat.equals("M")) {
			return Ocean_miss;
		}
		else if(stat.equals("S")) {
			return Ship_miss;
		}
		else if(stat.equals("_")) {
			return Ocean_null;
		}
		else {
			return null;
		}
	}
	private String imageChoice(Image image) {
		if(image.equals(Ship_hit)) {
			return "H";
		}
		else if(image.equals(Ocean_miss)) {
			return "M";
		}
		else if(image.equals(Ship_miss)) {
			return "S";
		}
		else if(image.equals(Ocean_null)) {
			return "_";
		}
		else {
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
				AIGrid[i][j].setOnMouseClicked(new PlayerButtonListener());

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
		selectionButton.setOnAction(new placementListener());
	}

	public class startListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			setupCoords();

		}
	}
	public class placementListener implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			setupBoard();
			setupGrid();

		}
	}

	public class PlayerButtonListener implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			ImageView ImageClicked = (ImageView) arg0.getSource();
			Random random = new Random();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (AIGrid[i][j] == ImageClicked) {

						int valid = 0;
						int AIx = random.nextInt(10);
						int AIy = random.nextInt(10);
						ShotResult playerOutcome = AIBoard.shotAttempt(i, j);
						while (playerBoard.toStringCoord(AIx, AIy).equals("M")  || playerBoard.toStringCoord(AIx, AIy).equals("H")) {
							AIx = random.nextInt(10);
							AIy = random.nextInt(10);
						}
						ShotResult AIOutcome = playerBoard.shotAttempt(AIx, AIy);
					
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
