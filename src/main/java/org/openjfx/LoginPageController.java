package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import helper.components.WarningLabel;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

import java.sql.ResultSet;

public class LoginPageController {

    @FXML
    private WarningLabel loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void loginButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
            validLogin();
        } else {
            loginMessageLabel.setText("Bitte Benutzername und Passwort eingeben!");
            loginMessageLabel.showError();
        }
    }

    private void validLogin() {
        String verifyLogin = "SELECT count(1) FROM users WHERE email = '" + usernameTextField.getText() + "' AND password = '" + passwordField.getText() + "'";
        Data.setUsername(usernameTextField.getText());
        try {
            //Database management for login
            ResultSet queryResult = DatenbankMG.performQuery(verifyLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    //Go to main Page
                    MainApp.easyScene.showScene("/main.fxml");
                } else {
                    loginMessageLabel.setText("Falsche Benutzerdaten. Bitte erneut versuchen.");
                    loginMessageLabel.showError();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private void cancelButtonOnAction(ActionEvent event) {
        //close Stage
        MainApp.easyScene.off();
    }

    @FXML
    private void registerButtonOnAction(ActionEvent event) {
        MainApp.easyScene.showScene("/registerPage.fxml");
    }

    @FXML
    private void offClickedEvent(MouseEvent mouseEvent) {
        //close Stage
        MainApp.easyScene.off();
    }
}