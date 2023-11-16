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

public class StartScreenView extends BorderPane {

	private VBox ourVBox = new VBox();
	public Button startButton = new Button("Start");
	public Button multiplayerButton = new Button("Multiplayer");
	private Label title = new Label(
			"A Game That Allows You To Place Ships And Sink Those Same Ships But Not Yours The Other Guy's");
	private Label credits = new Label("By: Kade, Chi, Danilo, and Ethan");

	public StartScreenView() {
		initializePanel();
	}

	private void initializePanel() {
		title.setWrapText(true);
		ourVBox.getChildren().addAll(title, credits, startButton, multiplayerButton);
		ourVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		ourVBox.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				BorderWidths.EMPTY, new Insets(10, 10, 10, 10))));
		this.setCenter(ourVBox);
	}
}
