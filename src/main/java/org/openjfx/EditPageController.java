package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import helper.WindowMover;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class EditPageController {
    @FXML
    private Text currenttitle;
    @FXML
    private Text currentauthor;
    @FXML
    private Text currentgenre;
    @FXML
    private Text currentISBN;
    @FXML
    private TextField titlenew;
    @FXML
    private TextField authornew;
    @FXML
    private TextField genrenew;
    @FXML
    private TextField ISBNnew;
    @FXML
    private Label errorText;
    @FXML
    private void save(){
        if (titlenew.getText().isBlank() ||authornew.getText().isBlank() || genrenew.getText().isBlank() || ISBNnew.getText().isBlank()) {
            errorText.setVisible(true);
        } else {
            String SQLfetch = "UPDATE books SET title='"+titlenew.getText()+"',autor='"+authornew.getText()+"',genre='"+genrenew.getText()+"',isbn='"+ISBNnew.getText()+"'WHERE title='"+Data.getDataString()+ "';";
            DatenbankMG.performUpdate(SQLfetch);


            //Go back to main Page
            Stage stage = (Stage) currenttitle.getScene().getWindow();
            Parent mainWindow = null;
            try {
                mainWindow = FXMLLoader.load(getClass().getResource("/main.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(mainWindow));

        }
    }

    @FXML
    protected void initialize() {           //initialize Methode wird immer aufgerufen wenn neue Scene aufgemacht wird
        currenttitle.setText(Data.getDataString());
        String sqlString = "SELECT * FROM books WHERE title='"+Data.getDataString()+"';";
        //Getting all Books from DB
        try {
            ResultSet queryResult = DatenbankMG.performQuery(sqlString);

            while (queryResult.next()) {
                currentauthor.setText(queryResult.getString(3));
                currentgenre.setText(queryResult.getString(4));
                currentISBN.setText(queryResult.getString(5));
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private void cancel(){
        Stage stage = (Stage) titlenew.getScene().getWindow(); //aktuelle Bühne (hier titelaktuell...könnte auch autorneu etc sein)
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
