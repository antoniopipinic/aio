package org.openjfx;

import helper.WindowMover;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPageController {
    @FXML
    private TextField currenttitle;
    @FXML
    private TextField currentauthor;
    @FXML
    private TextField currentgenre;
    @FXML
    private TextField titlenew;
    @FXML
    private TextField authornew;
    @FXML
    private TextField genrenew;
    @FXML
    private void save(){}

    @FXML
    private void cancel(){
        Stage stage = (Stage) titlenew.getScene().getWindow(); //aktuelle Bühne (hier titelaktuell...könnte auch autorneu etc sein)
        Parent mainWindow = null;
        try {
            mainWindow = FXMLLoader.load(getClass().getResource("/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(mainWindow));

        WindowMover.loadResource(mainWindow);
        WindowMover.loadStage(stage);
    }
}
