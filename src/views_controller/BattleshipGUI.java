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

	private static final Media BACKGROUND = new Media(new File("soundeffects/shanty.mp3").toURI().toString());
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

		// window.setTop(initMenuBar());

		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
	}

	/**
	 * Initializes the menu bar that is always on top of the window
	 * 
	 * @return
	 */
	private MenuBar initMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu options = new Menu("Options");
		Menu strategies = new Menu("Strategies");
		MenuItem randomMenu = new MenuItem("RandomAI");
		MenuItem intermediateMenu = new MenuItem("Intermediate");
		strategies.getItems().addAll(randomMenu, intermediateMenu);
		Menu views = new Menu("Views");
		MenuItem textArea = new MenuItem("TextArea"); // activeView = 0
		MenuItem button = new MenuItem("Button"); // activeView = 2
		MenuItem drawing = new MenuItem("Drawing"); // activeView = 1
		views.getItems().addAll(button, textArea, drawing);
		// TODO: Get menu buttons working
		// EVENT HANDLING FOR ALL MENU BUTTONS
		// Switches view to textArea without restarting the current game
//		// Starts a new game
//		startNewGame.setOnAction(mouseEvent -> {
//			startGameAndViews();
//		});

		MenuItem startNewGame = new MenuItem("New Game");

		options.getItems().addAll(startNewGame, strategies, views);
		menuBar.getMenus().addAll(options);
		return menuBar;

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
		setViewTo(startView);
		startView.startButton.setOnAction(mouseEvent -> {
			setViewTo(shipSelectionView);
		});
		startView.infoButton.setOnAction(mouseEvent -> {
			setViewTo(infoView);
		});
		infoView.backButton.setOnAction(mouseEvent -> {
			setViewTo(startView);

		});

		multiplayerSetupView = new MultiplayerSetupView();
		// startView.multiplayerButton.setOnAction(mouseEvent -> {
		// window.setCenter(multiplayerSetupView);
		// });

		shipSelectionView = new ShipSelectionView();
		shipSelectionView.easyButton.setOnAction(mouseEvent -> {
			shipGameView = new ShipGameView(shipSelectionView.getConstructedPlayer(),
					new Player(PlayerType.AI_EASY, 10));
			setViewTo(shipGameView);
		});
		shipSelectionView.normalButton.setOnAction(mouseEvent -> {
			shipGameView = new ShipGameView(shipSelectionView.getConstructedPlayer(),
					new Player(PlayerType.AI_NORMAL, 10));
			setViewTo(shipGameView);
		});
		shipSelectionView.hardButton.setOnAction(mouseEvent -> {
			shipGameView = new ShipGameView(shipSelectionView.getConstructedPlayer(),
					new Player(PlayerType.AI_HARD, 10));
			setViewTo(shipGameView);
		});
		

	}

	public void setViewTo(Node newView) {
		window.setCenter(null);
		window.setCenter((Node) newView);
	}

}