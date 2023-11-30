// Kade Dean
package views_controller;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.*;
import model.Player.*;

public class BattleshipGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private ShipPlacementView shipPlacementView;
	private StartScreenView startView;
	private ShipSelectionView shipSelectionView;
	private DefaultGameView defaultGameView;
	private MultiplayerSetupView multiplayerSetupView;
	private GameEndView gameEndView;
	private InfoView infoView;

	private int boardSize;

	private static final Media BACKGROUND = new Media(new File("soundeffects/shanty.mp3").toURI().toString());
	private Sounds soundEffect = new Sounds();

	public BorderPane window;
	public static final int width = 700;
	public static final int height = 600;
    private ExecutorService serverThread; // Declare executor as a class variable


	public void start(Stage stage) {
        stage.setOnCloseRequest(event -> {
            if (serverThread != null && !serverThread.isShutdown()) {
            	System.out.println("Exiting!");
            	serverThread.shutdown();
            }
            Platform.exit(); // Exit the application when the window is closed
        });
		soundEffect.loop(BACKGROUND);
		stage.setTitle("Battleship");
		window = new BorderPane();
		Scene scene = new Scene(window, width, height);
		boardSize = 10;
		setupAllViews();

		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
	}

	/**
	 * Sets up all views needed for the program. Also handles the flow of the
	 * program by managing the button event handles for each page.
	 *
	 * If one wanted to add a new page to the program, this is where you do it.
	 */
	private void setupAllViews() {
		startView = new StartScreenView();
		defaultGameView = new DefaultGameView();
		shipSelectionView = new ShipSelectionView();
		multiplayerSetupView = new MultiplayerSetupView();
		gameEndView = new GameEndView();

		// shipPlacementView = new ShipPlacementView();
		infoView = new InfoView();

		setViewTo(startView);

		startView.startButton.setOnAction(mouseEvent -> {
			window.setCenter(shipSelectionView);
		});
		// startView.startButton.setOnAction(mouseEvent -> {
		// window.setCenter(shipPlacementView);
		// });
		startView.infoButton.setOnAction(mouseEvent -> {
			setViewTo(infoView);
		});
		startView.multiplayerButton.setOnAction(mouseEvent -> {
			window.setCenter(multiplayerSetupView);
		});

		shipSelectionView.backButton.setOnAction(mouseEvent -> {
			setViewTo(startView);
		});
		infoView.backButton.setOnAction(mouseEvent -> {
			setViewTo(startView);
		});
        // Handles multiplayer view buttons
        startView.multiplayerButton.setOnAction(mouseEvent -> {
        	serverThread = Executors.newSingleThreadExecutor(); // Initialize the executor
            multiplayerSetupView.serverThread = serverThread; // Assign the executor to the view
            setViewTo(multiplayerSetupView);
        });
		multiplayerSetupView.backButton.setOnAction(MouseEvent -> {
            if (serverThread != null && !serverThread.isShutdown()) {
            	System.out.println("Exiting!");
            	serverThread.shutdown();
            }
			setViewTo(startView);
		});

		shipSelectionView.easyButton.setOnAction(mouseEvent -> {
			defaultGameView.setPlayer1(shipSelectionView.getConstructedPlayer(boardSize));
			defaultGameView.setPlayer2(new Player(PlayerType.AI_EASY, boardSize));
			setViewTo(defaultGameView);
		});
		shipSelectionView.normalButton.setOnAction(mouseEvent -> {
			defaultGameView.setPlayer1(shipSelectionView.getConstructedPlayer(boardSize));
			defaultGameView.setPlayer2(new Player(PlayerType.AI_NORMAL, boardSize));
			setViewTo(defaultGameView);
		});
		shipSelectionView.hardButton.setOnAction(mouseEvent -> {
			defaultGameView.setPlayer1(shipSelectionView.getConstructedPlayer(boardSize));
			defaultGameView.setPlayer2(new Player(PlayerType.AI_HARD, boardSize));
			setViewTo(defaultGameView);
		});

		defaultGameView.winnerButton.setOnAction(mouseEvent -> {
			gameEndView.endText.setText("YOU WIN");
			setViewTo(gameEndView);
		});
		defaultGameView.loserButton.setOnAction(mouseEvent -> {
			gameEndView.endText.setText("YOU LOSE");
			setViewTo(gameEndView);
		});

		gameEndView.menuButton.setOnAction(mouseEvent -> {
			setViewTo(startView);
		});
		gameEndView.playAgainButton.setOnAction(mouseEvent -> {
			shipSelectionView.reset();
			setViewTo(shipSelectionView);
		});
	}

	public void setViewTo(Node newView) {
		window.setCenter(null);
		window.setCenter((Node) newView);
	}

}
