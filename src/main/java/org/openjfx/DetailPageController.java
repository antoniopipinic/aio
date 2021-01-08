package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.sql.ResultSet;

public class DetailPageController {
    @FXML
    private Button backButton;
    @FXML
    private Text titleText;
    @FXML
    private Text autorText;
    @FXML
    private Text genreText;
    @FXML
    private Text isbnText;


    @FXML
    protected void initialize() {

        //Set SQL command
        String SQLfetch = "SELECT DISTINCT * FROM books WHERE title = '" + Data.getDataString() + "'";

        try {

            ResultSet queryResult = DatenbankMG.performQuery(SQLfetch);


            while (queryResult.next()) {
                autorText.setText(queryResult.getString(3));
                genreText.setText(queryResult.getString(4));
                isbnText.setText(queryResult.getString(5));
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        titleText.setText(Data.getDataString());
        //Closing DB connection


        //Adding Event for Click
        backButton.setOnAction(backButtonEvent);
    }

    //Handle Back Button Click
    EventHandler<ActionEvent> backButtonEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {

            MainApp.easyScene.showScene("/main.fxml");
        }
    };
}
