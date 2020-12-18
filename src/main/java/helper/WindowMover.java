package helper;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WindowMover {
    private static double xOffset, yOffset;
    private static Parent root;
    private static Stage stage;

    public static void loadResource(Parent parent){
        if (parent != null){
            root = parent;
        }
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
    }

    public static void loadStage(Stage primaryStage){
        if (primaryStage != null){
            stage = primaryStage;
        }
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
    }
}
