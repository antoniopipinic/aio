package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import helper.components.WarningLabel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox isReadBox = new ChoiceBox();
    public final String LABEL_VERFUEGBAR="verfügbar";
    public final String LABEL_NICHT_VERFUEGBAR="nicht verfügbar";

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
                isReadBox.getItems().addAll(LABEL_NICHT_VERFUEGBAR,LABEL_VERFUEGBAR);
                isReadBox.setValue(queryResult.getBoolean(6)==true? LABEL_VERFUEGBAR : LABEL_NICHT_VERFUEGBAR);
            }
            titleTextField.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private void save(){
        if (titleTextField.getText().isBlank() ||authorTextField.getText().isBlank() || genreTextField.getText().isBlank() || ISBNTextField.getText().isBlank()) {
            errorLabel.showError();
        } else {
            int read = isReadBox.getValue().toString().equalsIgnoreCase(LABEL_VERFUEGBAR)? 1 : 0;
            String SQLfetch = "UPDATE books SET title='"+titleTextField.getText()+"',autor='"+authorTextField.getText()+"',genre='"+genreTextField.getText()+"',isbn='"+ISBNTextField.getText()+"',ausgeliehen='"+read+"'WHERE title='"+Data.getDataString()+ "';";
            DatenbankMG.performUpdate(SQLfetch);

            //Go back to main Page
            MainApp.easyScene.showScene("/main.fxml");
        }
    }

    @FXML
    private void cancel(){
        //Go back to main Page
        MainApp.easyScene.showScene("/main.fxml");
    }
}