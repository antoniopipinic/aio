package org.openjfx;

import helper.WindowMover;
import javafx.event.ActionEvent;
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
    private Text keywordText;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField isbnTextField;


    @FXML
    private void cancel() {
        Stage stage = (Stage) keywordText.getScene().getWindow(); //aktuelle Bühne (hier titelaktuell...könnte auch autorneu etc sein)
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

    public void search(ActionEvent event) {
        String keyword = searchTextField.getText();
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String genre = genreTextField.getText();
        String isbn = isbnTextField.getText();





    }

}
