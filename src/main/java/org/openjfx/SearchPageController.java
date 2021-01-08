package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
        MainApp.easyScene.showScene("/main.fxml");

    }

    public void search(ActionEvent event) {
        String keyword = searchTextField.getText();
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String genre = genreTextField.getText();
        String isbn = isbnTextField.getText();





    }

}
