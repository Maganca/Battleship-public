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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.*;
import model.Player.*;

public class BattleshipGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private StartScreenView startView;
	private InfoView infoView;
	private ShipSelectionView shipSelectionView;
	private ShipGameView shipGameView;
	private MultiplayerSetupView multiplayerSetupView;
	private ShipWinView shipWinView;

	private static final Media BACKGROUND = new Media(
			new File("soundeffects/shanty.mp3").toURI().toString());
	private Sounds music = new Sounds();

	public BorderPane window;
	public static final int width = 700;
	public static final int height = 600;

	public void start(Stage stage) {
		music.loop(BACKGROUND);
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
		infoView = new InfoView();
		shipSelectionView = new ShipSelectionView();
		multiplayerSetupView = new MultiplayerSetupView();
		
		// Handles start view buttons
		setViewTo(startView); // Sets window to start view on launch
		startView.startButton.setOnAction(mouseEvent -> {
			setViewTo(shipSelectionView);
		});
		startView.infoButton.setOnAction(mouseEvent -> {
			setViewTo(infoView);
		});
		
		// Handles info view button
		infoView.backButton.setOnAction(mouseEvent -> {
			setViewTo(startView);
		});

		// Handles multiplayer view buttons
		startView.multiplayerButton.setOnAction(mouseEvent -> {
			setViewTo(multiplayerSetupView);
		});
		multiplayerSetupView.backButton.setOnAction(MouseEvent -> {
			setViewTo(startView);
		});

		// Handles ship placement/selection stuff
		shipSelectionView.easyButton.setOnAction(mouseEvent -> {
			if (shipSelectionView.getConstructedPlayer() != null) {
				shipGameView = new ShipGameView(
						shipSelectionView.getConstructedPlayer(),
						new Player(PlayerType.AI_EASY, 10));
				setViewTo(shipGameView);
			}
		});
		shipSelectionView.normalButton.setOnAction(mouseEvent -> {
			if (shipSelectionView.getConstructedPlayer() != null) {
				shipGameView = new ShipGameView(
						shipSelectionView.getConstructedPlayer(),
						new Player(PlayerType.AI_NORMAL, 10));
				setViewTo(shipGameView);
			}
		});
		shipSelectionView.hardButton.setOnAction(mouseEvent -> {
			if (shipSelectionView.getConstructedPlayer() != null) {
				shipGameView = new ShipGameView(
						shipSelectionView.getConstructedPlayer(),
						new Player(PlayerType.AI_HARD, 10));
				setViewTo(shipGameView);
			}
		});

	}

	/**
	 * Clears current view and sets view to the desired new view
	 * @param newView new view to switch to
	 */
	public void setViewTo(Node newView) {
		window.setCenter(null);
		window.setCenter((Node) newView);
	}

}