package helper;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class EasyScene {
    private Stage stage;
    private Parent window;
    private Scene scene;
    private String resourceName;

    private double xOffset = 0, yOffset = 0;
    private double newX = 0, newY = 0;
    private double midX = 0, midY = 0;
    private final FadeTransition ftIn = new FadeTransition(Duration.millis(500));
    private final FadeTransition ftOut = new FadeTransition(Duration.millis(300));

    public void loadPrimaryStage(Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        midX = screenBounds.getWidth() / 2;
        midY = screenBounds.getHeight() / 2;
        stage = primaryStage;
        stage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
    }

    public void showScene(String resourceName) {
        this.resourceName = resourceName;
        ftOut.setNode(window);
        ftOut.setFromValue(1.0);
        ftOut.setToValue(0.0);
        ftOut.setOnFinished(fadeOutEndEvent);
        ftOut.play();
    }

    private final EventHandler<ActionEvent> fadeOutEndEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                window = FXMLLoader.load(getClass().getResource(resourceName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ftIn.setNode(window);
            ftIn.setFromValue(0.0);
            ftIn.setToValue(1.0);
            scene = new Scene(window);
            scene.setFill(Color.TRANSPARENT);

            window.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    newX = event.getScreenX() - xOffset;
                    newY = event.getScreenY() - yOffset;
                    midX = newX + stage.getWidth() / 2;
                    midY = newY + stage.getHeight() / 2;
                    stage.setX(newX);
                    stage.setY(newY);
                }
            });

            window.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            showScene();
        }
    };

    private void showScene() {
        ftIn.play();
        stage.setScene(scene);
        stage.show();
        stage.setX(midX - stage.getWidth() / 2);
        stage.setY(midY - stage.getHeight() / 2);
    }

    public void off(){
        ftOut.setNode(window);
        ftOut.setOnFinished(offEvent);
        ftOut.play();
    }

    private final EventHandler<ActionEvent> offEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            stage.close();
        }

        ;
    };
}