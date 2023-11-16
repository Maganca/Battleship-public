package views_controller;


import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ShipWinView extends BorderPane{
	private VBox vBox = new VBox();
	private Label winText = new Label("YROUE WINNER");
	private Label loseText = new Label("YROUE LOSED");
	public Button playAgainButton = new Button("Play Again?1!");
	private Image background = new Image(new File("Tiles/background.png").toURI().toString());
	
	public ShipWinView() {
		initializePanel();
	}

	private void initializePanel() {
		StartScreenView shipWinView = new StartScreenView();
		playAgainButton.setOnAction(mouseEvent -> {
			this.setCenter(null);
			this.setCenter(shipWinView);
		});
		vBox.getChildren().add(winText);
		vBox.getChildren().add(playAgainButton);
		vBox.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		vBox.setAlignment(Pos.CENTER);
		this.setCenter(vBox);
	}
}
