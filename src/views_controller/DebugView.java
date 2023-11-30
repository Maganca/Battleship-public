package views_controller;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class DebugView extends BorderPane {
	private ViewManager viewManager;

	private ScrollPane scrollPane;
	private TextArea debugTextArea;
	public Button backButton = new Button("Back");

	public DebugView(ViewManager viewManager) {
		this.viewManager = viewManager;

		debugTextArea = new TextArea();
		debugTextArea.setEditable(false); // Make it non-editable
		debugTextArea.setWrapText(true); // Allow text wrapping
		debugTextArea.setStyle("-fx-font-family: Arial; -fx-font-size: 14px; -fx-text-fill: black;");

		setCenter(debugTextArea);
		setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5), Insets.EMPTY)));
		setPadding(new Insets(20));
	}

	// Method to append text to the debug view
	public void appendText(String text) {
		debugTextArea.appendText(text + "\n");
	}
}
