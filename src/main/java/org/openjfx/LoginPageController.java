package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import helper.PasswordSecurity;
import helper.components.WarningLabel;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

public class LoginPageController {

    @FXML
    private WarningLabel loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent event) throws NoSuchAlgorithmException {
        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
            validLogin();
        } else {
            loginMessageLabel.setText("Bitte Benutzername und Passwort eingeben!");
            loginMessageLabel.showError();
        }

    }


    private void validLogin() throws NoSuchAlgorithmException {


        String verifyLogin = "SELECT count(1) FROM users WHERE email = '" + usernameTextField.getText() + "' AND password = '" + PasswordSecurity.generateHash(passwordField.getText(), PasswordSecurity.getAlgorithm()) + "'";
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
    public void cancelButtonOnAction(ActionEvent event) {
        //close Stage
        MainApp.easyScene.off();
    }

    public void registerButtonOnAction(ActionEvent event) {
        MainApp.easyScene.showScene("/registerPage.fxml");
    }

    public void offClickedEvent(MouseEvent mouseEvent) {
        //close Stage
        MainApp.easyScene.off();
    }




    }

