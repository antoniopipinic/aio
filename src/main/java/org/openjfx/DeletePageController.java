package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DeletePageController {
    @FXML
    private Text booktitle;

    @FXML
    protected void initialize() {           //initialize Methode wird immer aufgerufen wenn neue Scene aufgemacht wird
        booktitle.setText("Wollen Sie '" + Data.getDataString() + "' wirklich l\u00f6schen?");
    }

    @FXML
    private void cancel(){
        MainApp.easyScene.showScene("/main.fxml");
    }

    @FXML
    private void accept(ActionEvent event) {
        String sql = "DELETE FROM books WHERE title='" + Data.getDataString() + "';";
        DatenbankMG.performUpdate(sql);
        MainApp.easyScene.showScene("/main.fxml");
    }
}
