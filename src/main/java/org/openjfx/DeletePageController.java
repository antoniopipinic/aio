package org.openjfx;

import helper.WindowMover;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DeletePageController {
    @FXML
    private Button acceptbutton;
    @FXML
    private Text booktitle;
    @FXML
    private Button cancelnbutton;
    @FXML
    private void save(){}

    @FXML
    private void cancel(){
        Stage stage = (Stage) booktitle.getScene().getWindow(); //aktuelle Bühne (hier titelaktuell...könnte auch autorneu etc sein)
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

    public void accept(ActionEvent actionEvent) {
    }
}
