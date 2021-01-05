package helper;

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

import java.io.IOException;

public class EasyScene {
    private Stage stage;
    private Parent window;
    private Scene scene;

    private double xOffset = 0, yOffset = 0;
    private double newX = 0, newY = 0;
    private double midX = 0, midY = 0;

    public void loadPrimaryStage(Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        midX = screenBounds.getWidth() / 2;
        midY = screenBounds.getHeight() / 2;
        stage = primaryStage;
        stage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);


    }

    public void loadResource(String resourceName) {
        try {
            window = FXMLLoader.load(getClass().getResource(resourceName));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    public void showScene() {

        stage.setScene(scene);
        stage.show();
        stage.setX(midX - stage.getWidth() / 2);
        stage.setY(midY - stage.getHeight() / 2);
        System.out.println("stage:" + stage.getX());
        System.out.println("stagewithd:" + stage.getWidth());
        System.out.println("scene:" + scene.getWidth());
        System.out.println("window:" + window.getLayoutX());
    }
}
