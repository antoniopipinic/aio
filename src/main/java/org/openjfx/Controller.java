package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.util.ResourceBundle;

import java.net.URL;

public class Controller implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void loginButtonOnAction(ActionEvent event){
        loginMessageLabel.setText("Invalid login. Please try again.");

    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
