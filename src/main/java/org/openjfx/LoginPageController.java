package org.openjfx;

import helper.Data;
import helper.DatenbankMG;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.ResultSet;

public class LoginPageController {

    @FXML // FXML - Bezeichung für graphischen Output, @FXML definiert das diese Objekt im entsprechende fxml File ist
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
            validLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password!");
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
                    loginMessageLabel.setText("You're logged in successfully.");
                    //Go to main Page
                    MainApp.easyScene.loadResource("/main.fxml");
                    MainApp.easyScene.showScene();
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtonOnAction(ActionEvent event) {
        MainApp.easyScene.loadResource("/registerPage.fxml");
        MainApp.easyScene.showScene();
    }
}
