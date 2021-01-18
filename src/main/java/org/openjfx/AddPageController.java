package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import helper.components.WarningLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


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
    private Button cancelButton;
    @FXML
    private WarningLabel errorLabel;
    @FXML
    protected void initialize() {
        addButton.setOnAction(addButtonEvent);
        cancelButton.setOnAction(cancelButtonEvent);
    }

    EventHandler<ActionEvent> addButtonEvent = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {

            if (buchtitleTextField.getText().isBlank() || autorTextField.getText().isBlank() || genreTextField.getText().isBlank() || isbnTextField.getText().isBlank()) {
                errorLabel.showError();
            } else {
                String SQLfetch = "INSERT INTO books (title,autor,genre,isbn,fk_user_id) VALUES ('" + buchtitleTextField.getText() + "','" + autorTextField.getText() + "','" + genreTextField.getText() + "','" + isbnTextField.getText() + "','" + Data.getId() +"')";

                DatenbankMG.performUpdate(SQLfetch);


                //Go back to main Page
                MainApp.easyScene.showScene("/main.fxml");

            }
        }
    };

    EventHandler<ActionEvent> cancelButtonEvent = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            MainApp.easyScene.showScene("/main.fxml");
        }

    };
}
