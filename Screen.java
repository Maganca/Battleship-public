package views_controller;
import javafx.scene.Node;
public interface Screen {
    Node content();
    
    String name();
    
    void onShow();

    default void resize(double width, double height) {
        // do nothing
    }
    
}