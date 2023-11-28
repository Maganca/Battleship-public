package views_controller;

import java.io.File;

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

public class MultiplayerSetupView extends BorderPane {

    private VBox ourVBox = new VBox();
    public Button backButton = new Button("Back");
    public Button hostButton = new Button("Host New Game");
    public Button joinButton = new Button("Join");
    private Label titleLabel = new Label("Start a New Multiplayer Game");
    private Label orLabel = new Label("or");
    private Label hostnameLabel = new Label("Connect to Game:");
    private Label portLabel = new Label("Enter port:");
    private Label statusText = new Label("");
    private TextField hostname = new TextField();
    private TextField port = new TextField();
    private Image background = new Image(new File("Tiles/background.png").toURI().toString());

    public MultiplayerSetupView() {
        initializePanel();
        handleMultiplayer();
    }

    private void initializePanel() {
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        ourVBox.getChildren().addAll(titleLabel, hostButton, orLabel, hostnameLabel, hostname, port, joinButton, statusText, backButton);
        ourVBox.setSpacing(10);
        ourVBox.setAlignment(Pos.CENTER);
        ourVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5), Insets.EMPTY)));
        ourVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));

        hostname.setPromptText("Enter IP address");
        port.setPromptText("Enter port number");

        backButton.setStyle("-fx-font-size: 10px; -fx-min-width: 40px; -fx-min-height: 20px;");
        hostButton.setStyle("-fx-font-size: 17px; -fx-min-width: 60px; -fx-min-height: 30px;");
        joinButton.setStyle("-fx-font-size: 17px; -fx-min-width: 60px; -fx-min-height: 30px;");
        hostnameLabel.setStyle("-fx-font-size: 20px; -fx-min-width: 60px; -fx-min-height: 30px;");
        portLabel.setStyle("-fx-font-size: 20px; -fx-min-width: 60px; -fx-min-height: 30px;");
        hostname.setMaxWidth(150);
        port.setMaxWidth(150);

        this.setCenter(ourVBox);
        this.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
        this.setPadding(new Insets(20));
    }
    
    private void handleMultiplayer() {
    	hostButton.setOnAction(mouseEvent -> {
    		
    	});
    }
}
