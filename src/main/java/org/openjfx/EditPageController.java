package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import helper.components.WarningLabel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;

public class EditPageController {

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField ISBNTextField;
    @FXML
    private WarningLabel errorLabel;
    @FXML
    private void save(){
        if (titleTextField.getText().isBlank() ||authorTextField.getText().isBlank() || genreTextField.getText().isBlank() || ISBNTextField.getText().isBlank()) {
            errorLabel.showError();
        } else {
            String SQLfetch = "UPDATE books SET title='"+titleTextField.getText()+"',autor='"+authorTextField.getText()+"',genre='"+genreTextField.getText()+"',isbn='"+ISBNTextField.getText()+"'WHERE title='"+Data.getDataString()+ "';";
            DatenbankMG.performUpdate(SQLfetch);


            //Go back to main Page
            MainApp.easyScene.showScene("/main.fxml");

        }
    }

    @FXML
    protected void initialize() {
        //initialize Methode wird immer aufgerufen wenn neue Scene aufgemacht wird
        String sqlString = "SELECT * FROM books WHERE title='"+Data.getDataString()+"';";
        //Getting all Books from DB
        try {
            ResultSet queryResult = DatenbankMG.performQuery(sqlString);

            while (queryResult.next()) {
                titleTextField.setText(queryResult.getString(2));
                authorTextField.setText(queryResult.getString(3));
                genreTextField.setText(queryResult.getString(4));
                ISBNTextField.setText(queryResult.getString(5));
            }
            titleTextField.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private void cancel(){
        //Go back to main Page
        MainApp.easyScene.showScene("/main.fxml");
    }
}
