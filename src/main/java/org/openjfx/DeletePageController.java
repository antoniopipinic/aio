package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DeletePageController {
    @FXML
    private Button acceptbutton;
    @FXML
    private Text booktitle;
    @FXML
    private Button cancelbutton;
    @FXML
    private void save(){}

    @FXML
    protected void initialize() {           //initialize Methode wird immer aufgerufen wenn neue Scene aufgemacht wird
        booktitle.setText("Wollen Sie '" + Data.getDataString() + "' wirklich l\u00f6schen?");

    }
    @FXML
    private void cancel(){
        MainApp.easyScene.loadResource("/main.fxml");
        MainApp.easyScene.showScene();
    }

    public void accept(ActionEvent event) {

        String sql = "DELETE FROM books WHERE title='" + Data.getDataString() + "';";
        DatenbankMG.performUpdate(sql);

        MainApp.easyScene.loadResource("/main.fxml");
        MainApp.easyScene.showScene();
    }
}
