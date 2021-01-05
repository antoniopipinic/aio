package org.openjfx;

import helper.DatenbankMG;
import helper.EasyScene;
import javafx.application.*;
import javafx.stage.*;

public class MainApp extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public static EasyScene easyScene = new EasyScene();

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatenbankMG.connectToDB();


        easyScene.loadPrimaryStage(primaryStage);
        easyScene.loadResource("/login.fxml");
        easyScene.showScene();
        // primaryStage.show();
    }

}
