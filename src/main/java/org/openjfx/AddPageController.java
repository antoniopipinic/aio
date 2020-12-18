package org.openjfx;

import helper.DatenbankMG;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class AddPageController {

    @FXML
    private TextField buchtitleTextField;
    @FXML
    private TextField autorTextField;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField isbnTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button cancleButton;
    @FXML
    private Text errorText;

    @FXML
    protected void initialize() {
        addButton.setOnAction(addButtonEvent);
        cancleButton.setOnAction(cancleButtonEvent);
    }

    EventHandler<ActionEvent> addButtonEvent = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {

            if (buchtitleTextField.getText().isBlank() || autorTextField.getText().isBlank() || genreTextField.getText().isBlank() || isbnTextField.getText().isBlank()) {
                errorText.setVisible(true);
            } else {
                String SQLfetch = "INSERT INTO books (title,autor,genre,isbn) VALUES ('" + buchtitleTextField.getText() + "','" + autorTextField.getText() + "','" + genreTextField.getText() + "','" + isbnTextField.getText() + "')";

                DatenbankMG.performUpdate(SQLfetch);


                //Go back to main Page
                Stage stage = (Stage) cancleButton.getScene().getWindow();
                Parent mainWindow = null;
                try {
                    mainWindow = FXMLLoader.load(getClass().getResource("/main.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setScene(new Scene(mainWindow));

            }
        }
    };

    EventHandler<ActionEvent> cancleButtonEvent = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            Stage stage = (Stage) cancleButton.getScene().getWindow();
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
