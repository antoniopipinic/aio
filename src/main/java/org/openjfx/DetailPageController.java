package org.openjfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

            Stage stage = (Stage) backButton.getScene().getWindow();
            Parent mainWindow = null;
            try {
                mainWindow = FXMLLoader.load(getClass().getResource("/main.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(mainWindow));
        }
    };
}
