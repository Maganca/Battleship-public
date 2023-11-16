package views_controller;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class StartScreenView extends BorderPane {

	private VBox ourVBox = new VBox();
	public Button startButton = new Button("Start");
	public Button multiplayerButton = new Button("Multiplayer");
	private Image title = new Image(
			new File("Tiles/background.gif").toURI().toString());
	private Image background = new Image(
			new File("Tiles/background.png").toURI().toString());
	private Label credits = new Label("By: Kade, Chi, Danilo, and Ethan");
	
	private ImageView titleView =  new ImageView(title);
	
	public StartScreenView() {
		initializePanel();
	}

	private void initializePanel() {
		titleView.setScaleX(0.5);
		titleView.setScaleY(0.5);
		
		ourVBox.getChildren().addAll(titleView, credits, startButton, multiplayerButton);
		ourVBox.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		
		ourVBox.setAlignment(Pos.CENTER);
		this.setCenter(ourVBox);
	}
}
