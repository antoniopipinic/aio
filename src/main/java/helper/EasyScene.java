package helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class EasyScene {
    private Stage stage;
    private Parent window;
    private Scene scene;
    private boolean transparent = true;
    private boolean moveable = true;

    public EasyScene(Scene currentScene, String resourceName) throws IOException {
        stage = (Stage) currentScene.getWindow();
        window = FXMLLoader.load(getClass().getResource("resourceName"));
        scene = new Scene(window);
        if (transparent){
            scene.setFill(Color.TRANSPARENT);
        }
        if (moveable){
            WindowMover.loadResource(window);
            WindowMover.loadStage(stage);
        }
    }

    public EasyScene(Scene currentScene, String resourceName, boolean transparent) throws IOException {
        this.transparent = transparent;
        new EasyScene(currentScene,resourceName);
    }
}
