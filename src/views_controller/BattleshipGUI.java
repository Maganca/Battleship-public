// Kade Dean
package views_controller;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import model.Player.*;

public class BattleshipGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private ShipPlacementView shipPlacementView;
	private StartScreenView startView;
	private ShipSelectionView shipSelectionView;
	private ShipGameView shipGameView;
	private MultiplayerSetupView multiplayerSetupView;
	private GameEndView gameEndView;
	private InfoView infoView;

	private static final Media BACKGROUND = new Media(new File("soundeffects/shanty.mp3").toURI().toString());
	private Sounds soundEffect = new Sounds();

	public BorderPane window;
	public static final int width = 700;
	public static final int height = 600;

	public void start(Stage stage) {
		soundEffect.loop(BACKGROUND);
		stage.setTitle("Battleship");
		window = new BorderPane();
		Scene scene = new Scene(window, width, height);

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
		shipGameView = new ShipGameView();
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
		multiplayerSetupView.backButton.setOnAction(mouseEvent -> {
			setViewTo(startView);
		});
		
		shipSelectionView.easyButton.setOnAction(mouseEvent -> {
			shipGameView.setPlayer1(shipSelectionView.getConstructedPlayer());
			shipGameView.setPlayer2(new Player(PlayerType.AI_EASY, 10));
			setViewTo(shipGameView);
		});
		shipSelectionView.normalButton.setOnAction(mouseEvent -> {
			shipGameView.setPlayer1(shipSelectionView.getConstructedPlayer());
			shipGameView.setPlayer2(new Player(PlayerType.AI_NORMAL, 10));
			setViewTo(shipGameView);
		});
		shipSelectionView.hardButton.setOnAction(mouseEvent -> {
			shipGameView.setPlayer1(shipSelectionView.getConstructedPlayer());
			shipGameView.setPlayer2(new Player(PlayerType.AI_HARD, 10));
			setViewTo(shipGameView);
		});
		
		shipGameView.winnerButton.setOnAction(mouseEvent -> {
			gameEndView.setWin(true);
			setViewTo(gameEndView);
		});
		shipGameView.loserButton.setOnAction(mouseEvent -> {
			gameEndView.setWin(false);
			setViewTo(gameEndView);
		});
		
		gameEndView.menuButton.setOnAction(mouseEvent -> {
			setViewTo(startView);
		});
		gameEndView.playAgainButton.setOnAction(mouseEvent -> {
			setViewTo(shipSelectionView);
		});
	}

	public void setViewTo(Node newView) {
		window.setCenter(null);
		window.setCenter((Node) newView);
	}

}
