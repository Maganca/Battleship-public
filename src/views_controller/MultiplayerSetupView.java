package views_controller;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MultiplayerSetupView extends BorderPane {

	private VBox ourVBox = new VBox();
	public Button hostButton = new Button("Host");
	public Button joinButton = new Button("Join");
	private Label title = new Label("Start new Multiplayer Game");

	public MultiplayerSetupView() {
		initializePanel();
	}

	private void initializePanel() {
		ourVBox.getChildren().addAll(title, hostButton, joinButton);
		ourVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		ourVBox.setBorder(new Border(new BorderStroke(null, null, null, null, new Insets(10, 10, 10, 10))));
		this.setCenter(ourVBox);

	}

}
