package views_controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.ClientHandler;
import model.ServerHandler;

public class MultiplayerSetupView extends BorderPane {
	private ViewManager viewManager;

	private String DEV_PORT = "1006";
	private VBox ourVBox = new VBox();
	public Button backButton = new Button("Back");
	public Button hostButton = new Button("Host New Game");
	public Button joinButton = new Button("Join");
	private Label titleLabel = new Label("Start a New Multiplayer Game");
	private Label orLabel = new Label("or");
	private Label hostnameLabel = new Label("Connect to Game:");
	private Label portLabel = new Label("Enter port:");
	private static Label statusText = new Label("");
	private TextField hostname = new TextField("localhost");
	private TextField port1 = new TextField(DEV_PORT);
	private TextField port2 = new TextField(DEV_PORT);
	private Image background = new Image(new File("Tiles/background.png").toURI().toString());
	public ServerHandler serverHandler;
	// Using ExecutorService to run the ServerHandler in a separate thread
	public ExecutorService serverThread;

	public MultiplayerSetupView(ViewManager viewManager) {
		this.viewManager = viewManager;
		initializePanel();
		handleMultiplayer();

		backButton.setOnAction(MouseEvent -> {
			if (serverThread != null && !serverThread.isShutdown()) {
				System.out.println("Exiting!");
				serverThread.shutdown();
			}
			disableInputs(false);
			statusText.setText("");
			viewManager.changeView(viewManager.getStartView());
		});
	}

	public ExecutorService getServerThread() {
		return serverThread;
	}

	private void initializePanel() {
		titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		ourVBox.getChildren().addAll(titleLabel, port1, hostButton, orLabel, hostnameLabel, hostname, port2, joinButton,
				statusText, backButton);
		ourVBox.setSpacing(10);
		ourVBox.setAlignment(Pos.CENTER);
		ourVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5), Insets.EMPTY)));
		ourVBox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));

		hostname.setPromptText("Enter IP address");
		port1.setPromptText("Enter port number");
		port2.setPromptText("Enter port number");

		backButton.setStyle("-fx-font-size: 10px; -fx-min-width: 40px; -fx-min-height: 20px;");
		hostButton.setStyle("-fx-font-size: 17px; -fx-min-width: 60px; -fx-min-height: 30px;");
		joinButton.setStyle("-fx-font-size: 17px; -fx-min-width: 60px; -fx-min-height: 30px;");
		hostnameLabel.setStyle("-fx-font-size: 20px; -fx-min-width: 60px; -fx-min-height: 30px;");
		portLabel.setStyle("-fx-font-size: 20px; -fx-min-width: 60px; -fx-min-height: 30px;");
		hostname.setMaxWidth(150);
		port1.setMaxWidth(150);
		port2.setMaxWidth(150);
		ourVBox.setMaxWidth(400);
		ourVBox.setMaxHeight(400);

		this.setCenter(ourVBox);
		this.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		this.setPadding(new Insets(20));
	}

	private void joinAsClient(int port, String hostnameString) {
		try {
			ClientHandler clientHandler = new ClientHandler(hostnameString, port, viewManager);
			viewManager.changeView(viewManager.getDebugView());
		} catch (Exception e) {
			statusText.setText("Unable to Join. Check your details and try again.");
			disableInputs(false);
		}
	}

	private void startServerHandler(int port) {
		try {
			serverHandler = new ServerHandler(port);
		} catch (IOException e) {
			statusText.setText("Unable to start server. Try a different port.");
			disableInputs(false);
		}
	}

	private void disableInputs(boolean i) {
		hostButton.setDisable(i);
		joinButton.setDisable(i);
		port1.setDisable(i);
		port2.setDisable(i);
		hostname.setDisable(i);
	}

	private void handleMultiplayer() {
		// Handles the host button
		hostButton.setOnAction(mouseEvent -> {
			String port = port1.getText();
			if (!port.isEmpty()) {
				try {
					int portNumber = Integer.parseInt(port);
					disableInputs(true);
					statusText.setText("Server Started! Waiting for user to connect.");
					serverThread = Executors.newSingleThreadExecutor(); // Initialize the executor
					serverThread.submit(() -> startServerHandler(portNumber));
					joinAsClient(portNumber, "localhost");
				} catch (NumberFormatException e) {
					statusText.setText("Invalid port number.");
				}
			}
		});
		// Handles the join button
		joinButton.setOnAction(mouseEvent -> {
			String port = port2.getText();
			String hostnameString = hostname.getText();
			if (!port.isEmpty() && !hostnameString.isEmpty()) {
				int portNumber = Integer.parseInt(port);
				joinButton.setDisable(true);
				joinAsClient(portNumber, hostnameString);
			}
		});
	}
}
