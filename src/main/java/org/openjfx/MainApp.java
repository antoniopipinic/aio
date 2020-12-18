package org.openjfx;

import helper.DatenbankMG;
import helper.WindowMover;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatenbankMG.connectToDB();

        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root, 600, 400);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        //Set so window can be moved across screen
        WindowMover.loadResource(root);
        WindowMover.loadStage(primaryStage);

    }

}
