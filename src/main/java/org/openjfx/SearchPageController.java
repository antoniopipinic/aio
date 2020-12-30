package org.openjfx;

import helper.WindowMover;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchPageController {
    @FXML
    private Button searchbutton;
    @FXML
    private Button cancelbutton;
    @FXML
    private Text searchText;
    @FXML
    private TextField searchTextField ;


    @FXML
    private void cancel() {
        Stage stage = (Stage) searchText.getScene().getWindow(); //aktuelle Bühne (hier titelaktuell...könnte auch autorneu etc sein)
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
