package views_controller;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
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

        scrollPane = new ScrollPane(debugTextArea);
        scrollPane.setFitToWidth(true); // Allow horizontal scrolling when needed
        scrollPane.setPrefSize(670, 400);

        setCenter(scrollPane);
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5), Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        setPadding(new Insets(20));
    }

    // Method to append text to the debug view
    public void appendText(String text) {
        debugTextArea.appendText(text + "\n");
    }
}
