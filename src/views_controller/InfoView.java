package views_controller;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class InfoView extends BorderPane {
	private VBox vBox = new VBox();
	private Label infoText = new Label("Normal battleship");
	public Button backButton = new Button("Back");
	private Image background = new Image(new File("Tiles/background.png").toURI().toString());

	public InfoView() {
		backButton.setOnAction(mouseEvent -> {
			this.setCenter(null);
			this.setCenter(new StartScreenView());
		});
		infoText.setStyle("-fx-font-size: 20; -fx-font-family: Verdana");
		vBox.getChildren().add(infoText);
		vBox.getChildren().add(backButton);
		vBox.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		vBox.setAlignment(Pos.CENTER);
		this.setCenter(vBox);
	}
}
